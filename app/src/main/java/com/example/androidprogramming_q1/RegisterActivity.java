package com.example.androidprogramming_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    private EditText usernameText;
    private EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        registerBtn = findViewById(R.id.register_btn);
        usernameText = findViewById(R.id.username_field);
        passwordText = findViewById(R.id.password_field);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if(username.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_LONG).show();
                } else if(password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}