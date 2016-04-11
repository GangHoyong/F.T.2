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
                //로그인 부분
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(email,password); // 값 Null Null

                // user Email, password -> authenticate 함수로 전달
                authenticate(user);

                /*
                Intent intent2 = new Intent(this,logout.class);
                startActivity(intent2);
                */
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
                    // 아이디랑 비번이 다를경우 ... error Message 함수 호출
                    showErrorMessage();
                }
                else
                {
                    // 만약 아이디랑 비번이 같은 경우 logUserIn 함수를 호출
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

        // 로그인후 다음 엑티비티로 전환 !!
        startActivity(new Intent(this, logout.class));
    }

}
