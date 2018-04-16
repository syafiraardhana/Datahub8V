package com.example.syafirawardhana.appdatahub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    LinearLayout lyJobList,lyReport,lyProfile;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lyJobList = findViewById(R.id.lyJobList);
//        lyReport = findViewById(R.id.lyReport);
//        lyProfile = findViewById(R.id.lyProfile);

        Intent intent = getIntent();
        sessionId = intent.getStringExtra("sessionid");
        Log.d("TAG", "ceksessionid 1 " + sessionId);

        lyJobList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobList();
            }
        });
//
//        lyProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                profile();
//            }
//        });

    }

//    private void profile() {
//        Intent intent = new Intent(this,ProfileActivity.class);
//        intent.putExtra("sessionid", sessionId);
//        startActivity(intent);
//    }
//
    private void jobList(){
        Intent intent = new Intent(this,JobListActivity.class);
        intent.putExtra("sessionid", sessionId);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id  = menuItem.getItemId();
        if(id==R.id.id_profile){
            Intent intent = new Intent(this,SettingsActivity.class);
            intent.putExtra("sessionid", sessionId);
            startActivity(intent);
        }
        if(id==R.id.id_logout){
            finish();
            System.exit(0);
        }
        return true;
    }
}
