package com.LMS.library.model;

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
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publisher_name")
    @JsonProperty("publisher_name")
    @NotNull(message = "User name can't be null")
    @Size(min = 2 , max = 30, message = "User name is between lenght 2 to 30.")
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference("publisher-book")
    private List<Book> books;

}
