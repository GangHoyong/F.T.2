package com.example.ganghoyong.login_register2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText etName, etEmail, etPassword, etPhoneNumber, etBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etBirthDate = (EditText) findViewById(R.id.etBirthDate);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bRegister:
            {
                // 회원가입 정보 ~~
                String name = etName.getText().toString();
                String mail = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                String birthDate = etBirthDate.getText().toString();

                User registerData = new User(name, mail, password, phoneNumber, birthDate);
                break;
            }
        }
    }
}
