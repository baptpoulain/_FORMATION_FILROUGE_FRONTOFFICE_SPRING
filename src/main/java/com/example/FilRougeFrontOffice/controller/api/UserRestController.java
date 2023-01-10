package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.controller.dto.UserPasswordDto;
import com.example.FilRougeFrontOffice.message.ResponseMessage;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
import com.example.FilRougeFrontOffice.service.FilesStorageService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable("id") int id, @RequestParam("userFirstname") String userFirstname, @RequestParam("userName") String userName, @RequestParam("picture") Optional<String> picture, @RequestParam("city") String city, @RequestParam("file") Optional<MultipartFile> files) {
        String message = "";
        UserDto userDto= new UserDto();
        if(picture.isPresent()){
            userDto.setUserName(userName);
            userDto.setUserFirstname(userFirstname);
            userDto.setUserPicture(picture.get());
            userDto.setUserCity(city);

        }else{
            userDto.setUserName(userName);
            userDto.setUserFirstname(userFirstname);
            userDto.setUserPicture("");
            userDto.setUserCity(city);
        }

        int userLoginId = (int) defaultProperties.get("userLoginId");


        try {
            if (userLoginId == id) {
                UUID uuid = UUID.randomUUID();

                Optional<UsersEntity> userData = userService.findById(id);
                if (userData.isPresent()) {

                    String oldFileName = userData.get().getUserPicture();
                    if (files.isPresent()) {

                        List.of(files.get()).stream().forEach(file -> {

                            String fileType = file.getContentType();
                            String mimeType = fileType.replaceFirst("image/", "");

                            storageService.save(file, uuid, mimeType);

                            userDto.setUserPicture(uuid + "." + mimeType);
                            userService.updUser(userData.get(), userDto);

                            if( oldFileName != null && oldFileName.contains("default")){
                                storageService.delete(oldFileName);
                            }

                        });

                        message = "User update successfully";
                        return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.OK);
                    } else {
                        userService.updUser(userData.get(), userDto);
                        message = "User update successfully";
                        return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.OK);
                    }
                } else {
                    message = "Fail to update user";
                    return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.NOT_FOUND);
                }
            } else {
                message = "Fail to update user";
                return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            message = "Fail to update user";
            return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.FORBIDDEN);
        }

    }


    @PutMapping("/users/password/{id}")
    public ResponseEntity<ResponseMessage> updatePasswordFromUser(@PathVariable("id") int id, @RequestBody UserPasswordDto pswDto){
        String message = "";
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            Boolean retour  = userService.pwsIsGood(pswDto.getUserOldPassword(), userData.get().getUserPassword());
            if(retour){
                userService.saveUserWithNewPsw(userData.get(), pswDto.getUserNewPassword());
                message = "Password updated ! ";
                return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.OK);
            }
            else{
                message = "Fail to update password ! ";
                return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.CONFLICT);
            }
        }
        else{
            message = "Fail to update password ! ";
            return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("id") int id){
        String message = "";
        Optional<UsersEntity> userData = userService.findById(id);
        if(userData.isPresent()){
            userService.deleteUser(userData.get());
            message = "User deleted successfully ";
            return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.OK);
        }else{
            message = "Fail to delete the user ";
            return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.NOT_FOUND);
        }
    }






}
