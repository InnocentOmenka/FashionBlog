package com.example.weeknine.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationDto {
    private String username;
    private String email;
    private String role;
    private String password;
}
