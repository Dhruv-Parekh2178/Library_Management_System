package com.LMS.library.dtos;

import lombok.Data;

import java.util.List;
@Data
public class BookRequestDTO {
    private Long id;
    private String name;
    private List<Long> authorIds;
    private List<Long> categoryIds;
    private Long publisherId;
    private List<Long> userIds;
}
