package com.jayzonsolutions.lunchboxrider;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = findViewById(R.id.btnMap);
        Button btnMapActivuty = findViewById(R.id.btnMapActivity);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Create a Uri from an intent string. Use the result to create an Intent.
            //        Uri gmmIntentUri = Uri.parse("geo:24.841998,67.081242?z=20");
                Uri gmmIntentUri = Uri.parse("google.navigation:q=24.841998,67.081242");
// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
// Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            }
        });

        btnMapActivuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(MainActivity.this, AcknowledgedRequestActivity.class);
                startActivity(map);
            }
        });
    }
}
