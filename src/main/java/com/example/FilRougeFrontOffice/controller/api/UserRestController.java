package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.controller.dto.UserPasswordDto;
import com.example.FilRougeFrontOffice.message.ResponseMessage;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.FilesStorageService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> fetchUsers() {
        List<UserDto> userList = userService.fetchUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id){
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            UserDto userDataToSend = UserDto.from(userData.get());
            return ResponseEntity.status(HttpStatus.OK).body(userDataToSend);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable("id") int id, @RequestBody UserDto dto){
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            userService.updUser(userData.get(), dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/password/{id}")
    public ResponseEntity<UsersEntity> updatePasswordFromUser(@PathVariable("id") int id, @RequestBody UserPasswordDto pswDto){
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            Boolean retour  = userService.pwsIsGood(pswDto.getUserOldPassword(), userData.get().getUserPassword());
            if(retour){
                userService.saveUserWithNewPsw(userData.get(), pswDto.getUserNewPassword());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>((HttpStatus.CONFLICT));
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<UsersEntity> deleteUser(@PathVariable("id") int id){
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            userService.deleteUser(userData.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
