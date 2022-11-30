package com.example.weeknine.services;

import com.example.weeknine.dtos.LoginDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {
     ResponseEntity<RegistrationDto> registerNewUser(RegistrationDto registrationDto);

     ResponseEntity<User> loginUser(LoginDto loginDto);

     ResponseEntity<RegistrationDto> editUser(long id, RegistrationDto registrationDto);
}
