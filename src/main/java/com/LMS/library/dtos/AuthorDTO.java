package com.LMS.library.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private List<BookSimpleDTO> books;
}
