//package com.example.FilRougeFrontOffice.controller.api;
//
//import com.example.FilRougeFrontOffice.controller.dto.SigninRequest;
//import com.example.FilRougeFrontOffice.controller.dto.SignupRequest;
//import com.example.FilRougeFrontOffice.controller.dto.UserDto;
//import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
//import com.example.FilRougeFrontOffice.repository.entity.exception.UserAlreadyExistException;
//import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
//import com.example.FilRougeFrontOffice.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import static com.example.FilRougeFrontOffice.controller.dto.UserDto.from;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//@WebMvcTest(AuthController.class)
//class AuthControllerTest {
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private JwtUtils jwtUtils;
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void signup() throws UserAlreadyExistException {
//        SignupRequest newUserDto = new SignupRequest(
//                "Poulain",
//                "Baptiste",
//                "123",
//                "bapt.poulain@outlook.com",
//                "none",
//                (byte) 0,
//                1,
//                "Lyon"
//                );
//
//        when(userService.signup(newUserDto)).then("New user added");
//        when
//
//
//    }
//}