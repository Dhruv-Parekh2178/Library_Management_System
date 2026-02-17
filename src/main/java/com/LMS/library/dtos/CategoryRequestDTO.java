package com.LMS.library.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CategoryRequestDTO {
    private Long id;
    private String name;
    private List<Long> bookIds;
}
