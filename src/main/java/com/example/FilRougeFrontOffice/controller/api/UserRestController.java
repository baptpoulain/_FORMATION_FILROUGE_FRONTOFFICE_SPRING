package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.controller.dto.UserPasswordDto;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
import com.example.FilRougeFrontOffice.service.FilesStorageService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.FilRougeFrontOffice.controller.api.AuthController.defaultProperties;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    FilesStorageService storageService;

    @Autowired
    JwtUtils jwtUtils;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> fetchUsers() {
        List<UserDto> userList = userService.fetchUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
        Optional<UsersEntity> userData = userService.findById(id);
        if (userData.isPresent()) {
            UserDto userDataToSend = UserDto.from(userData.get());
            return ResponseEntity.status(HttpStatus.OK).body(userDataToSend);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable("id") int id, @RequestParam("userFirstname") String userFirstname, @RequestParam("userName") String userName, @RequestParam("picture") String picture, @RequestParam("city") String city, @RequestParam("file") Optional<MultipartFile> files) {

        UserDto userDto = new UserDto(userName, userFirstname, picture, city);

        int userLoginId = (int) defaultProperties.get("userLoginId");


        try {
            if (userLoginId == id) {
                UUID uuid = UUID.randomUUID();

                Optional<UsersEntity> userData = userService.findById(id);
                if (userData.isPresent()) {

                    String oldFileName = userData.get().getUserPicture();
                    if (files.isPresent()) {

                        Arrays.asList(files.get()).stream().forEach(file -> {

                            String fileType = file.getContentType();
                            String mimeType = fileType.replaceFirst("image/", "");

                            storageService.save(file, uuid, mimeType);

                            userDto.setUserPicture(uuid + "." + mimeType);
                            userService.updUser(userData.get(), userDto);


                           /* if( oldFileName != null && oldFileName.length() != 12 ){
                                storageService.delete(oldFileName);
                            }*/

                            if( oldFileName != null && oldFileName.contains("default")){
                                storageService.delete(oldFileName);
                            }

                        });
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        userService.updUser(userData.get(), userDto);
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
