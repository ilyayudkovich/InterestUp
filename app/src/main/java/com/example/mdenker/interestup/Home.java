package com.example.mdenker.interestup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add back button
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onProfileClick(View view) {
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

    public void onClickGoToEventPage(View view) {
        Intent i = new Intent(this, CreateEventPage.class);
        startActivity(i);
    }

    public void OnClickGoToSearchPage(View view) {
        Intent i = new Intent(this, Search.class);
        startActivity(i);
    }

}
