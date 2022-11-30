package com.example.weeknine.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginDto {
    private String email;
    private String password;
}
