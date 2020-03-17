package com.crud.sqlite;

import android.content.Intent;
import android.os.Bundle;

import com.crud.sqlite.utils.DBDataSource;
import com.crud.sqlite.utils.Items;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBDataSource dbDataSource; // initial controller

    // recycleView
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Items> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Listing Data");
        setSupportActionBar(toolbar);

        // initial page
        rvView  = (RecyclerView) findViewById(R.id.rvListing);
        layoutManager = new LinearLayoutManager(this);
        rvView.setHasFixedSize(true);
        rvView.setLayoutManager(layoutManager);

        dbDataSource = new DBDataSource(this);
        dbDataSource.open();


        // Button Create
        FloatingActionButton fab = findViewById(R.id.fabCreate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });
    }

    public void listData() {
        // get all item
        listItem  = dbDataSource.listData();

        /*
        initial adapater dan items dalam bentuk arrayList, dan
        men-set adapater ke dalam recycleView
         */
        adapter = new MainActivityAdapter(listItem, MainActivity.this);
        rvView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        listData();
    }

}
