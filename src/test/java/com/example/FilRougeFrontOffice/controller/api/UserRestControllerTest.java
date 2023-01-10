package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.repository.UserRepository;

import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
import com.example.FilRougeFrontOffice.service.FilesStorageService;
import com.example.FilRougeFrontOffice.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)


class UserRestControllerTest {
    @Autowired
private MockMvc mockMvc;

@MockBean
    UserRepository userRepository;
@MockBean
    UserService userService;

@MockBean
    FilesStorageService filesStorageService;

@MockBean
    JwtUtils jwtUtils;

@Test
@WithMockUser(roles = "USER")
void when_fetchUsers_then_returnAllUsers() throws Exception {
    //given
    List<UserDto> users = new ArrayList<>(
            Arrays.asList(new UserDto("jp", "couhe", "15643.png","Tours"),
                    new UserDto("marc", "blabla", "1548.png", "Rouen"))
    );

    //when
    when(userService.fetchUser()).thenReturn(users);

    mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(users.size()))
            .andDo(print());
}
}

//    @Test
//    void when_getUserById__then__returnUserAnd200() throws Exception {
//        //given
//        int idUser = 1;
//        UsersEntity user = new UsersEntity(
//                                "Poulain",
//                                "Baptiste",
//                                "none",
//                                "Rouen");
//        user.setUserId(idUser);
//
//        //when
//        when(userService.findById(idUser)).thenReturn(Optional.of(user));
//        UserDto userDataToSend = UserDto.from(user);
//        //then
//        mockMvc.perform(get("/api/users/{id}", idUser))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(idUser))
//                .andExpect(jsonPath("$.userName").value(userDataToSend.getUserName()))
//                .andExpect(jsonPath("$.userFirstname").value(userDataToSend.getUserFirstname()))
//                .andExpect(jsonPath("$.userPicture").value(userDataToSend.getUserPicture()))
//                .andExpect(jsonPath("$.userCity").value(userDataToSend.getUserCity()))
//                .andExpect(jsonPath("$.planningsByUserId").value(userDataToSend.getPlanningsByUserId().isEmpty()))
//                .andDo(print());
//    }

