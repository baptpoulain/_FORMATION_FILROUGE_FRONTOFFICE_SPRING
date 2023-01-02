package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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




}
