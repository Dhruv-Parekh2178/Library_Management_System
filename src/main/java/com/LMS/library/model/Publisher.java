package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publisher_name", columnDefinition = "TEXT DEFAULT ''")
    @JsonProperty("publisher_name")
//    @NotNull(message = "Publisher name can't be null")
    @Size(min = 2 , max = 30, message = "Publisher name is between lenght 2 to 30.")
    private String name;
    @Column(name = "is_deleted", nullable = false)
    @JsonProperty("is_deleted")
    private boolean deleted = false;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnoreProperties("publisher")
    private List<Book> books;

}