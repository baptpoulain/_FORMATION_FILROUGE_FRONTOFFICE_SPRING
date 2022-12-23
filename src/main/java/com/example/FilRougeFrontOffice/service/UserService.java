package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.controller.dto.SignupRequest;
import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.exception.UserAlreadyExistException;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import com.example.FilRougeFrontOffice.repository.entity.RolesEntity;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void signup(SignupRequest userDto) throws UserAlreadyExistException {
        Optional<UsersEntity> userOptional = userRepository.findByuserEmail(userDto.getUserEmail());

        if(userOptional.isPresent()){
            throw new UserAlreadyExistException((userDto.getUserName()));
        }else{
            UsersEntity newUser = new UsersEntity(
                    userDto.getUserName(),
                    userDto.getUserFirstname(),
                    encoder.encode(userDto.getUserPassword()),
                    userDto.getUserEmail(),
                    userDto.getUserPicture(),
                    (byte) 0,
                    1,
                    userDto.getUserCity());

            userRepository.save(newUser);
        }
    }


    public List<UserDto> fetchUser() {
        List<UsersEntity> userList = new ArrayList<>();
        userRepository.findAll().forEach(u -> userList.add(u));
        return userList.stream()
                .map(u -> UserDto.from(u))
                .collect(Collectors.toList());
    }
}
