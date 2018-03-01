package com.example.mdenker.interestup;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by mdenker on 2/28/18.
 */

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //add back button
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //add edit button on right
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            // ends the activity
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void OnEditClick(View view) {
        //Intent i = new Intent(this, Profile.class); // figure out this
        //startActivity(i);
    }

}
