package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "author_name" , columnDefinition = "TEXT DEFAULT ''")
    @JsonProperty("author_name")
//    @NotNull(message = "Author name can't be null")
    @Size(min = 2 , max = 30, message = "Author name is between lenght 2 to 30.")
    private String Name;

    @Min(value = 18,message = "Author minimum age should be 18 years.")
    @Column(name = "age" ,columnDefinition = "INTEGER DEFAULT 0")
    @JsonProperty("age")
    private int age;

    @Column(name = "is_deleted", nullable = false)
    @JsonProperty("is_deleted")
    private boolean deleted = false;

    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties("authors")
    private List<Book> books;


}
