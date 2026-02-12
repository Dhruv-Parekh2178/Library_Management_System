
package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@ToString(exclude = "books")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Where(clause = "is_deleted = false")
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", columnDefinition = "TEXT DEFAULT ''")
    @Size(min = 2, max = 30, message = "Author name length must be 2 to 30.")
    private String name;

    @Min(value = 18, message = "Author minimum age should be 18 years.")
    @Column(name = "age", columnDefinition = "INTEGER DEFAULT 0")
    private Integer age;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    @ManyToMany(mappedBy = "authors",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("authors")
    private List<Book> books;

    @Transient
    private String bookIdsJson;
}


