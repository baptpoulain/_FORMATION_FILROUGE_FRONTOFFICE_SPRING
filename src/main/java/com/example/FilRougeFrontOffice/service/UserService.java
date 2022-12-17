package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.RolesEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDto AddUser(UserDto userDto){
        UsersEntity usersEntity1 = new UsersEntity(
                userDto.getUserName(),
                userDto.getUserFirstname(),
                userDto.getUserPassword(),
                userDto.getUserEmail(),
                userDto.getUserPicture(),
                (byte) 0,
                1,
                userDto.getUserCity(),
                new ArrayList<InteractEntity>(),
                new ArrayList<PlanningsEntity>(),
                new RolesEntity("user"));

        userRepository.save(usersEntity1);
        return UserDto.from(usersEntity1);
    }


    public List<UserDto> fetchUser() {
        List<UsersEntity> userList = new ArrayList<>();
        userRepository.findAll().forEach(u -> userList.add(u));
        return userList.stream()
                .map(u -> UserDto.from(u))
                .collect(Collectors.toList());
    }
}
