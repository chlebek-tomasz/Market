package com.chlebek.project.dto;

import com.chlebek.project.model.user.Address;
import com.chlebek.project.validation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserRegistrationDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    @NotNull
    @Size(min = 8, max = 32)
    private String passwordMatches;
    @NotNull
    @Size(max = 40)
    private String firstName;
    @NotNull
    @Size(max = 40)
    private String lastName;
    private Address address;
    @NotNull
    private String phoneNumber;
    private String joinedDate;

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

    public String getPasswordMatches() {
        return passwordMatches;
    }

    public void setPasswordMatches(String passwordMatches) {
        this.passwordMatches = passwordMatches;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }
}
