package com.fad.FoodAndDrinks.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@Data
@NoArgsConstructor
public class User {
    public User(String email, String pass) {
        this.email = email;
        this.password = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(hidden = true)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Schema(hidden = true)
    private Role role = Role.ROLE_USER;
}
