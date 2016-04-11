package com.example.ganghoyong.login_register2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ganghoyong on 2016. 4. 4..
 * logout / login
 *
 */
public class UerLocalStroe {

    public static final  String SP_NAME = "userDatails";
    SharedPreferences userLocalDatabase;

    public UerLocalStroe(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)
    {
        // 예제 에서는 이름 . 나이 , 유저 이름 , 패스워드를 받음 ...
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("mail", user.mail);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public  User getLoggedInUser()
    {
        String mail = userLocalDatabase.getString("mail", "");
        String password = userLocalDatabase.getString("password", "");
        String name = userLocalDatabase.getString("name", "");
        String phoneNumber = userLocalDatabase.getString("phoneNumber" , "");
        String birthDate = userLocalDatabase.getString("birthDate" , "" );


        User storeUser = new User(mail,password,name,phoneNumber,birthDate);

        return  storeUser;

    }

    public  void setUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn()
    {
        if(userLocalDatabase.getBoolean("loggedId", false) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public  void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
