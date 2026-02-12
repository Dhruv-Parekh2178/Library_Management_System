package com.LMS.library.dtos;

import com.LMS.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO implements Serializable {

    private Long id;
    private String name;
    private List<Book> books;
}
