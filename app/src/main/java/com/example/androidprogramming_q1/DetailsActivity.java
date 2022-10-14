package com.example.androidprogramming_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.androidprogramming_q1.models.User;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView first_name;
    private TextView last_name;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();

        avatar = findViewById(R.id.image_view);
        first_name = findViewById(R.id.first_name_text_view);
        last_name = findViewById(R.id.last_name_text_view);
        email = findViewById(R.id.email);

        String jsonMyObject = null;
        Bundle userBundle = getIntent().getExtras();
        if(userBundle != null) {
            jsonMyObject = userBundle.getString("onClickedUser");
        }
        User user = new Gson().fromJson(jsonMyObject, User.class);

        Glide.with(this)
                .load(user.getAvatar())
                .circleCrop()
                .into(avatar);

        first_name.setText(user.getFirstname());
        last_name.setText(user.getLastname());
        email.setText(user.getEmail());

    }
}