package com.example.ganghoyong.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements View.OnClickListener {

    Button bt_register;
    EditText et_name, et_email, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 초기화 부분 EditText and Button
        bt_register = (Button)findViewById(R.id.btnRegister);

        et_name = (EditText)findViewById(R.id.name);
        et_email = (EditText)findViewById(R.id.email);
        et_password = (EditText)findViewById(R.id.password);

        // Event Button
        bt_register.setOnClickListener(this);
    }

    private void User()
    {
        final String name = et_name.getText().toString().trim();
        final String email = et_email.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, DB.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(register.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(DB.KEY_NAME,name);
                params.put(DB.KEY_EMAIL,email);
                params.put(DB.KEY_PASS, password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Intent intent2 = new Intent(register.this, MainActivity.class);
        startActivity(intent2);

    }

    @Override
    public void onClick(View v) {

        if(v==bt_register)
        {
            User();
        }
    }
}
