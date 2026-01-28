package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    @JsonProperty("book_name")
    @NotNull(message = "User name can't be null")
    @Size(min = 2 , max = 30, message = "User name is between lenght 2 to 30.")
    private String name;

    @ManyToMany
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn (name = "authors_id")}
    )
    @JsonBackReference("author-book")
    private List<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    @JsonBackReference("category-book")
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonBackReference("publisher-book")
    private Publisher publisher;

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference("book-user")
    private List<User> users;
}
