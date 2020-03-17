package com.crud.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.sqlite.utils.DBDataSource;
import com.crud.sqlite.utils.Items;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private DBDataSource dbDataSource;
    private Items items;

    private long itemsId;
    private String itemsName;
    private String itemsBrand;
    private String itemsPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Data");
        setSupportActionBar(toolbar);

        // get Intent
        Bundle intent = this.getIntent().getExtras();
        itemsId       = intent.getLong("id");
        itemsName     = intent.getString("name");
        itemsBrand    = intent.getString("brand");
        itemsPrice    = intent.getString("price");

        // initial
        TextView items_name     = findViewById(R.id.items_name);
        TextView items_brand    = findViewById(R.id.items_brand);
        TextView items_price    = findViewById(R.id.items_price);
        Button btnUpdate        = findViewById(R.id.btn_update);

        // set value
        items_name.setText(itemsName);
        items_brand.setText(itemsBrand);
        items_price.setText(itemsPrice);

        // open connection
        dbDataSource = new DBDataSource(this);
        dbDataSource.open();

        // action
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btn_update)){
            update();
        }
    }

    public void update() {

        TextView items_name     = findViewById(R.id.items_name);
        TextView items_brand    = findViewById(R.id.items_brand);
        TextView items_price    = findViewById(R.id.items_price);

        items = new Items();
        items.setItems_id(itemsId);
        items.setItems_name(items_name.getText().toString());
        items.setItems_brand(items_brand.getText().toString());
        items.setItems_price(items_price.getText().toString());

        dbDataSource.update(items);
        dbDataSource.close();

        Toast.makeText(this, "Success update data", Toast.LENGTH_LONG).show();
        finish();
    }
}
