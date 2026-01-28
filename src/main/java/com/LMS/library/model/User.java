package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "library_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "users_books",
            joinColumns = {@JoinColumn (name= "user_id")},
            inverseJoinColumns = {@JoinColumn (name= "book_id")}
    )
    @JsonBackReference("book-user")
    private List<Book> books;
}
