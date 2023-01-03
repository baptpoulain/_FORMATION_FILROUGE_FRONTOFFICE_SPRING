package com.example.FilRougeFrontOffice.service;

import com.example.FilRougeFrontOffice.controller.dto.SignupRequest;
import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.exception.UserAlreadyExistException;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistException((userDto.getUserName()));
        } else {
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

    public Optional<UsersEntity> findById(int id) {
        return userRepository.findById(id);
    }
    public void updUser(UsersEntity userData, UserDto dto) {
        userData.setUserName(dto.getUserName());
        userData.setUserFirstname(dto.getUserFirstname());
        userData.setUserCity(dto.getUserCity());
        userData.setUserPicture(dto.getUserPicture());
        userRepository.save(userData);
    }


    public Boolean pwsIsGood(String userPassword, String userOlsPassword) {
        return encoder.matches(userPassword, userOlsPassword);
    }

    public void saveUserWithNewPsw(UsersEntity usersEntity, String userNewPassword) {
        usersEntity.setUserPassword(encoder.encode(userNewPassword));
        userRepository.save(usersEntity);
    }

    public void deleteUser(UsersEntity userData) {
        userRepository.deleteById(userData.getUserId());
    }
}

