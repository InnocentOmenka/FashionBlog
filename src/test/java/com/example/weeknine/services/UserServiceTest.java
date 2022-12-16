package com.example.weeknine.services;

import com.example.weeknine.controllers.UserController;
import com.example.weeknine.dtos.LoginDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.services.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserServiceTest {

    @BeforeEach
    void setUp() {
    }
}

//
//import com.example.weeknine.controllers.UserController;
//        import com.example.weeknine.dtos.LoginDto;
//        import com.example.weeknine.dtos.RegistrationDto;
//        import com.example.weeknine.services.serviceImpl.UserServiceImpl;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import org.junit.jupiter.api.Test;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.boot.test.mock.mockito.MockBean;
//        import org.springframework.test.web.servlet.MockMvc;
//        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
////@SpringBootTest
//class UserControllerTest{
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @MockBean
//    private UserServiceImpl userService;
//
//    @Test
//    void register() throws Exception{
//
//        RegistrationDto newUser = RegistrationDto.builder()
//                .username("victor").email("victor@gmail.com").role("admin").password("2222").build();
//
//        String requestBody = mapper.writeValueAsString(newUser);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/register", 42L)
//                .contentType("application/json").content(requestBody)).andExpect((status().isCreated()));
//    }
//
//    @Test
//    void login() throws Exception{
//
//        LoginDto loginDto = new LoginDto();
//        loginDto.setEmail("victor@gmail.com");
//        loginDto.setPassword("2222");
//
//        String requestBody = mapper.writeValueAsString(loginDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("users/login", 42L)
//                        .contentType("application/json").content(requestBody))
//                .andExpect(status().isCreated());
//    }
//}