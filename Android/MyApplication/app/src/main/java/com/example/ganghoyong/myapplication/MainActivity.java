package com.example.ganghoyong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    // login 페이지

    private TextView Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register = (TextView)findViewById(R.id.btnLinkToRegisterScreen);
        Register.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v==Register)
        {
            Intent intent1 = new Intent(MainActivity.this, register.class);
            startActivity(intent1);
        }

    }
}
