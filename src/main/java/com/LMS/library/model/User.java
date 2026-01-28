package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "library_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    @NotNull(message = "User name can't be null")
    @Size(min = 2 , max = 30, message = "User name is between lenght 2 to 30.")
    private String name;
    @Min(value = 7,message = "User minimum age should be 7 years.")
    private int age;
    @ManyToMany
    @JoinTable(name = "users_books",
            joinColumns = {@JoinColumn (name= "user_id")},
            inverseJoinColumns = {@JoinColumn (name= "book_id")}
    )
    @JsonBackReference("book-user")
    private List<Book> books;
}
