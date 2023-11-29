package com.banking.application.dto;


public class SignUpDto {
    public SignUpDto() {
    }

    private String first_name;
    private String email;
    private String last_name;
    private String phone_number;
    private String address;
    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

}
