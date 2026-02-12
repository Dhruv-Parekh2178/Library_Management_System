package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Where(clause = "is_deleted = false")
@Table(name = "book")
@ToString(exclude = {"authors", "categories", "users"})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", columnDefinition = "TEXT DEFAULT ''")
    @Size(min = 2, max = 30)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnoreProperties("books")
    private List<Category> categories;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("books")
    private Publisher publisher;


    @ManyToMany(mappedBy = "books",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("books")
    private List<User> users;

    @Transient
    private String authorIdsJson;

    @Transient
    private String categoryIdsJson;

    @Transient
    private String userIdsJson;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}