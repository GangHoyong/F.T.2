package com.example.ganghoyong.myapplication;

/**
 * Created by ganghoyong on 2016. 4. 16..
 */
public class DB {


    // URL register
    public static final String REGISTER_URL = "http://192.168.25.4:8888/register.php";
    // URL Login
    public static final String LOGIN_URL = "http://192.168.25.4:8888/login.php";

    // 응답이 성공적으로 로그인 서비이면 ...
    public static final String LOGIN_SUCCESS = "success";
    // key
    public static final String SHARED_PREF_NAME = "myloginapp";

    // logout 사용자 이름을 보여주기 위한 TEST용
    public static final String EMAIL_SHARED_PREF = "username";
    public static final String LOGIN_SHARED_PREF = "login";

    // Login and register
    public static final String KEY_NAME = "name";
    // 공통부분 Login id key and password key
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS = "password";


}
