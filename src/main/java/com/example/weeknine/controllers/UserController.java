package com.example.weeknine.controllers;

import com.example.weeknine.dtos.LoginDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.models.User;
import com.example.weeknine.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationDto> registerNewUser(@RequestBody RegistrationDto registrationDto){
        return userService.registerNewUser(registrationDto);
    }

    @PostMapping("/login")
    public ResponseEntity<User> userLogin(@Valid @RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RegistrationDto> editUser(@Valid @PathVariable long id, @RequestBody RegistrationDto registrationDto){
        return userService.editUser(id, registrationDto);
    }
}
