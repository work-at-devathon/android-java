package com.example.crypsis.send;


public class RegisterUser {
    String name;
    String email;
    String countryCode;
    int phoneNumber;
    String password;

    public RegisterUser(String fullName,String email,String countryCode,int phoneNumber,String password) {
        this.name = fullName;
        this.email=email;
        this.countryCode=countryCode;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }
}
