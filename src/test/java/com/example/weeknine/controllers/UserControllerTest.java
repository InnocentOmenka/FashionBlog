package com.example.weeknine.controllers;//package com.example.weeknine.controllers;
//
//import com.example.weeknine.dtos.LoginDto;
//import com.example.weeknine.dtos.RegistrationDto;
//import com.example.weeknine.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
////@SpringBootTest
//class UserControllerTest {
//
//    @Autowired
//    private UserController userController;
//
//    @MockBean
//    private UserService userService;
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void testCreateUser() throws Exception {
////        RegistrationDto registrationDto = new RegistrationDto();
////        registrationDto.setUsername("Jane");
////        registrationDto.setEmail("jane.doe@example.org");
////        registrationDto.setRole("buyer");
////        registrationDto.setPassword("1234");
////        when(userService.registerNewUser((RegistrationDto) any())).thenReturn(registrationDto);
//
//        RegistrationDto registrationDto1 = new RegistrationDto();
//        registrationDto1.setEmail("jane.doe@example.org");
//        registrationDto1.setUsername("Jane");
//        registrationDto1.setRole("iloveyou");
//        registrationDto1.setPassword("4105551212");
//        String content = (new ObjectMapper()).writeValueAsString(registrationDto1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(status().isAccepted())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"firstname\":\"Jane\",\"lastname\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\",\"password"
//                                        + "\":\"iloveyou\"}"));
//    }
//
//    @Test
//    void testUserLogin() throws Exception {
////        when(userService.loginUser((LoginDto) any())).thenReturn("User login");
//
//        LoginDto loginDto = new LoginDto();
//        loginDto.setEmail("jane.doe@example.org");
//        loginDto.setPassword("iloveyou");
//        String content = (new ObjectMapper()).writeValueAsString(loginDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
//                .andExpect(MockMvcResultMatchers.content().string("User Login"));
//    }
//}


import com.example.weeknine.controllers.UserController;
import com.example.weeknine.dtos.LoginDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.services.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
//@SpringBootTest
class UserControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void register() throws Exception{


        RegistrationDto newUser = RegistrationDto.builder()
                .username("victor").email("victor@gmail.com").role("admin").password("2222").build();

        String requestBody = mapper.writeValueAsString(newUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register", 42L)
                .contentType("application/json").content(requestBody)).andExpect((status().isCreated()));
    }

    @Test
    void login() throws Exception{

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("victor@gmail.com");
        loginDto.setPassword("2222");

        String requestBody = mapper.writeValueAsString(loginDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login", 42L)
                .contentType("application/json").content(requestBody))
                .andExpect(status().isCreated());
    }
}