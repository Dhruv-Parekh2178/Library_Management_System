package com.LMS.library.dtos;

import com.LMS.library.model.Author;
import com.LMS.library.model.Category;
import com.LMS.library.model.User;
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
    private List<Author> authors;
    private List<Category> categories;
    private PublisherDTO publisher;
    private List<User> users;
}
