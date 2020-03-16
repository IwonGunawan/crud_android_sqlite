package com.crud.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        Log.d("TAG", name);
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DetailActivity.class);
    }
}
