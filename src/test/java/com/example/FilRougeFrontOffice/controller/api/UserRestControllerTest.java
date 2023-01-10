package com.example.FilRougeFrontOffice.controller.api;

import com.example.FilRougeFrontOffice.controller.dto.UserDto;
import com.example.FilRougeFrontOffice.repository.UserRepository;
import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
import com.example.FilRougeFrontOffice.service.FilesStorageService;
import com.example.FilRougeFrontOffice.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
