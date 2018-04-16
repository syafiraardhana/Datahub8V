package com.example.syafirawardhana.appdatahub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {
    LinearLayout lyHome;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        sessionId = intent.getStringExtra("sessionid");
        lyHome =findViewById(R.id.lyHome);
        lyHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });
    }

    private void home() {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtra("sessionid",sessionId);
        startActivity(intent);
    }

}
