package com.example.demo_validate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "{user.firstname.required}")
    @Size(min = 5, max = 45, message = "{user.firstname.size}")
    private String firstName;

    @NotBlank(message = "{user.lastname.required}")
    @Size(min = 5, max = 45, message = "{user.lastname.size}")
    private String lastName;

    @NotBlank(message = "{user.phone.required}")
    @Pattern(regexp = "^(0[0-9]{9})$", message = "{user.phone.invalid}")
    private String phoneNumber;

    @Min(value = 18, message = "{user.age.min}")
    private String age;

    @NotBlank(message = "{user.email.required}")
    @Email(message = "{user.email.invalid}")
    private String email;
}
