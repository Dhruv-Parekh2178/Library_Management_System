package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Where(clause = "is_deleted = false")
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", columnDefinition = "TEXT DEFAULT ''")
    @Size(min = 2, max = 30)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;


    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("categories")
    private List<Book> books;

    @Transient
    private String bookIdsJson;
}