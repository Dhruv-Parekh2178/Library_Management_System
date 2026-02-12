package com.LMS.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {

    private Long id;
    private String name;
    private List<AuthorDTO> authors;
    private List<CategoryDTO> categories;
    private PublisherDTO publisher;
    private List<UserSimpleDTO> users;

}
