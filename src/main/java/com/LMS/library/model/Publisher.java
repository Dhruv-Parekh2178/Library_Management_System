package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publisher_name")
    @JsonProperty("publisher_name")
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference("publisher-book")
    private List<Book> books;

}
