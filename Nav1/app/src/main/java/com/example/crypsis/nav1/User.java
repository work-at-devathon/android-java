package com.example.crypsis.nav1;


import java.io.Serializable;

public class User implements Serializable {
    String firstname;
    String lastname;
    int phone;
    User(String f,String l,int p)
    {
        this.firstname=f;
        this.lastname=l;
        this.phone=p;
    }
    String getFirstName()
    {
        return this.firstname;
    }
    String getLastName()
    {
        return this.lastname;
    }
    int getPhone()
    {
        return this.phone;
    }
}
