package com.LMS.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_user")
public class MasterUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("master_user_id")
    private Long id;
    @Column(unique = true)
    @NotNull(message = "user name can't be null")
    @Size(min = 2 , max = 30, message = "user name is between lenght 2 to 30.")
    private String name;
    @Column(name = "password")
    @JsonProperty("password")
    @NotNull(message = "Password can't be null")
    @Size(min = 8, message ="Password minimum length is 8.")
    private String password;

    @Column(name = "role")
    @NotNull(message = "user role can't be null")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}


