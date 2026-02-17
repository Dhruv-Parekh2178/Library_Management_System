package com.LMS.library.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    private Long id;
    private String name;
    private Integer age;
    private List<Long> bookIds;
}
