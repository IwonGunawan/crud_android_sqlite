package com.crud.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.sqlite.utils.DBDataSource;
import com.crud.sqlite.utils.Items;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText itemsName;
    private EditText itemsBrand;
    private EditText itemsPrice;
    private Button btnCreate;

    // data source
    private DBDataSource dbDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Create New");
        setSupportActionBar(toolbar);

        // inisiasi
        itemsName       = (EditText) findViewById(R.id.itemsName);
        itemsBrand      = (EditText) findViewById(R.id.itemsBrand);
        itemsPrice      = (EditText) findViewById(R.id.itemsPrice);
        btnCreate       = (Button) findViewById(R.id.btnCreate);

        dbDataSource = new DBDataSource(this); // inisiasi class DBDataSource.java
        dbDataSource.open(); // connectiong db

        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCreate) {
            save();
        }
    }

    public void save() {
        // get text
        String name   = itemsName.getText().toString();
        String brand  = itemsBrand.getText().toString();
        String price  = itemsPrice.getText().toString();

        if (!isEmpty(name) && !isEmpty(brand) && !isEmpty(price)) {
            // inisiasi barang baru masih kosong
            Items items = null;
            items = dbDataSource.create(name, brand, price);

            if (!isEmpty(items.getItems_name())) {
                Toast.makeText(this, items.getItems_name() + " Success added!", Toast.LENGTH_LONG).show();

                itemsName.setText("");
                itemsBrand.setText("");
                itemsPrice.setText("");
                finish();

                // Log.d("INFO", "save: " + items);
                // Log.d("INFO", "save: " + items.getItems_name());
            }

        }
        else
        {
            Toast.makeText(this, "Data Can't be Empty!", Toast.LENGTH_LONG).show();
        }

    }

    public boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }
}
