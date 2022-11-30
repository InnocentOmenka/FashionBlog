package com.example.weeknine.services.serviceImpl;

import com.example.weeknine.dtos.LoginDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.exceptions.InvalidRequestException;
import com.example.weeknine.exceptions.UserRegistrationException;
import com.example.weeknine.models.User;
import com.example.weeknine.repositories.UserRepository;
import com.example.weeknine.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final ModelMapper mapper;


    @Override
    public ResponseEntity<RegistrationDto> registerNewUser(RegistrationDto registrationDto) {
        Optional<User> selectedUser = userRepository.findUserByEmailOrUsername(registrationDto.getEmail(), registrationDto.getUsername());
        if(selectedUser.isPresent())
            throw new UserRegistrationException("User already exist");
        User user = mapToEntity(registrationDto);
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(mapToDto(newUser), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> loginUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
        } else {
            throw new InvalidRequestException("Invalid email/password");

        }
    }

    @Override
    public ResponseEntity<RegistrationDto> editUser(long id, RegistrationDto registrationDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setRole(registrationDto.getRole());
            user.get().setUsername(registrationDto.getUsername());
            user.get().setPassword(registrationDto.getPassword());
            user.get().setEmail(registrationDto.getEmail());

            User editUserDetails = userRepository.save(user.get());
            RegistrationDto editDetails = mapToDto(editUserDetails);
            return new ResponseEntity<>(editDetails, HttpStatus.OK);
          } else {
            throw new InvalidRequestException("User not found");
        }
    }

    public RegistrationDto mapToDto(User user){
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
        return registrationDto;
    }


    public User mapToEntity(RegistrationDto registrationDto){
        User user = mapper.map(registrationDto, User.class);
        return user;
    }
    }

