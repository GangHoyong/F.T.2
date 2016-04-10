package com.example.ganghoyong.login_register2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // login class
    Button bLogin;  // 버튼 생성자
    EditText etEmail, etPassword;
    TextView tvRegisterLink;

    UerLocalStroe userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        tvRegisterLink.setOnClickListener(this);

        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        userLocalStore = new UerLocalStroe(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bLogin:
            {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                // 로그인 부분
                User user = new User(email,password); // 값 Null Null

                authenticate(user);
                break;
            }
            case R.id.tvRegisterLink:
            {
                Intent intent1 = new Intent(this, Register.class);
                startActivity(intent1);
            }
        }
    }
    private void authenticate(User user)
    {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser == null)
                {
                    showErrorMessage();
                }
                else
                {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private  void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("ok", null);
        dialogBuilder.show();
    }

    private  void logUserIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, logout.class));
    }

}
