package com.LMS.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSimpleDTO implements Serializable {

    private Long id;
    private String name;
    private List<UserSimpleDTO> users;
}
