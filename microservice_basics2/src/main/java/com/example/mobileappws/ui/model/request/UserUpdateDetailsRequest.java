package com.example.mobileappws.ui.model.request;

import javax.validation.constraints.NotBlank;

public class UserUpdateDetailsRequest {
    @NotBlank(message = "First name cannot be null")
    private String firstName;

    @NotBlank(message = "Last name cannot be null")
    private String lastName;

    public UserUpdateDetailsRequest() {
    }

    public UserUpdateDetailsRequest(@NotBlank(message = "First name cannot be null") String firstName, @NotBlank(message = "Last name cannot be null") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
