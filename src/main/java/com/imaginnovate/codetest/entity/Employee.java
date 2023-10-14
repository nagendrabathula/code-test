package com.imaginnovate.codetest.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TBL_EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull(message = "firstName shouldn't be null")
    @NotBlank
    private String firstName;

    @NotNull(message = "lastname shouldn't be null")
    @NotBlank
    private String lastName;

    @Email(message = "invalid email address")
    private String email;

    
    @ElementCollection
    @NotEmpty
    private List<@NotBlank String> phoneNumbers;

    @NotNull
    private Date doj;

    @NotNull(message = "salary shouldn't be null")
    @Positive
    private double salary;

    
}
