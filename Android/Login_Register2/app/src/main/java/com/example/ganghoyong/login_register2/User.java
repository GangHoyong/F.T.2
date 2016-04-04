package com.example.ganghoyong.login_register2;

/**
 * Created by ganghoyong on 2016. 4. 4..
 * DB연동을 위한 페이지 User 이녀석은 DB table name
 *
 */


public class User {

    String name, mail, password, phoneNumber, birthDate;
    public User ( String mail, String password)
    {
        this.mail = mail;
        this.password = password;
    }

    public  User(String name, String mail, String password, String phoneNumber, String birthDate)
    {
        this.mail = mail;
        this.password = password;
        this.name = "";
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}
