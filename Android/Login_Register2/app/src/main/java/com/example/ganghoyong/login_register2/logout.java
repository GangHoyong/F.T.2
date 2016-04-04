package com.example.ganghoyong.login_register2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class logout extends AppCompatActivity implements View.OnClickListener{


    // 로그아웃 Test Class
    UerLocalStroe userLocalStore;

    Button bLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        // Logout 관련 내용
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        userLocalStore = new UerLocalStroe(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bLogout:
            userLocalStore.clearUserData(); // 유저 정보 삭제
            userLocalStore.setUserLoggedIn(false); // 로그아웃
            startActivity(new Intent(this, MainActivity.class)); // login Activity 로 이동
            break;

        }
    }
}
