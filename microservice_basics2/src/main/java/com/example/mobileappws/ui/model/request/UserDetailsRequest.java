package com.example.mobileappws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDetailsRequest {
    @NotBlank(message = "First name cannot be null")
    private String firstName;

    @NotBlank(message = "Last name cannot be null")
    private String lastName;

    @NotBlank(message = "Email cannot be null")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password length must be minimum 8 and maximum 16.")
    private String password;

    public UserDetailsRequest() {
    }

    public UserDetailsRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetailsRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
