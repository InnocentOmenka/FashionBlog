package com.example.weeknine.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CommentDto {
    private String commentContent;

    @NotNull
    private Long id;

}
