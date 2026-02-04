package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Where(clause = "is_deleted = false")
@Table(name = "library_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", columnDefinition = "TEXT DEFAULT ''")
    @JsonProperty("user_name")
    @Size(min = 2 , max = 30, message = "User name is between lenght 2 to 30.")
    private String name;

    @Min(value = 7,message = "User minimum age should be 7 years.")
    @Column(name = "age" ,columnDefinition = "INTEGER DEFAULT 0")
    @JsonProperty("age")
    private Integer age;

    @Column(name = "is_deleted", nullable = false)
    @JsonProperty("is_deleted")
    private boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "users_books",
            joinColumns = {@JoinColumn (name= "user_id")},
            inverseJoinColumns = {@JoinColumn (name= "book_id")}
    )
    @JsonIgnoreProperties("user")
    private List<Book> books = new ArrayList<>();


    @Transient
    private String bookIdsJson;
}