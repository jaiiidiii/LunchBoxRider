package com.jayzonsolutions.lunchboxrider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import customfonts.MyEditText;

import customfonts.MyTextView;

public class signin extends AppCompatActivity {


    ImageView sback;
    MyTextView login;

    MyEditText userEmail;
    MyEditText userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sback = findViewById(R.id.sinb);
        login = findViewById(R.id.sin);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this,MainActivity.class);
                startActivity(intent);
            }
        });
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}



