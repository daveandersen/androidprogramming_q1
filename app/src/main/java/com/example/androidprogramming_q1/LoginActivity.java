package com.example.androidprogramming_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView registerBtn;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();


        username = findViewById(R.id.username_field);
        password = findViewById(R.id.password_field);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void makeToast(String text) {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                Boolean userLogged = false;
                Boolean passwordLogged = false;

                Log.d("usernameTag",usernameText);
                if(usernameText.equals("")) {
                    makeToast("Please enter Username");
                } else {
                    if(usernameText.equals("master")) {
                        userLogged = true;
                    }
                }

                if(passwordText.equals("")) {
                    makeToast("Please enter Password");
                } else {
                    if(passwordText.equals("master")) {
                        passwordLogged = true;
                    }
                }

                // Will navigate to List Activity
                if(userLogged == true && passwordLogged == true) {
                    Intent intent = new Intent(v.getContext(), ListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", usernameText);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    makeToast("Wrong username or password");
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}