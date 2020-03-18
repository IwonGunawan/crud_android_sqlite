package com.crud.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.sqlite.utils.DBDataSource;
import com.crud.sqlite.utils.DBHelper;
import com.crud.sqlite.utils.Items;

import java.text.NumberFormat;


public class DetailActivity extends AppCompatActivity {

    private DBDataSource dbDataSource;
    private Items items;

    // set variable
    private long itemsId;
    private String itemsName;
    private String itemsBrand;
    private String itemsPrice;

    // initial
    private TextView items_name;
    private TextView items_brand;
    private TextView items_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Data");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // initial
        items_name   = (TextView) findViewById(R.id.items_name);
        items_brand  = (TextView) findViewById(R.id.items_brand);
        items_price  = (TextView) findViewById(R.id.items_price);

        // get intent
        Intent intent = getIntent();
        itemsId     = intent.getLongExtra("id", 0);

        // open connection
        dbDataSource = new DBDataSource(this);
        dbDataSource.open();
    }

    public void onResume() {
        super.onResume();
        detail();
    }

    public void detail() {
        if (itemsId > 0) {
            // get detail
            items = dbDataSource.getItems(itemsId);
            itemsName   = items.getItems_name();
            itemsBrand  = items.getItems_brand();
            itemsPrice  = numberFormat(items.getItems_price());

            items_name.setText(itemsName);
            items_brand.setText(itemsBrand);
            items_price.setText(itemsPrice);
        }
        else {
            Toast.makeText(this, "Data not Found", Toast.LENGTH_LONG).show();
        }
    }

    public void delBox() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure delete this data ?");

        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbDataSource.delete(itemsId);
                        dbDataSource.close();
                        finish();

                        Toast.makeText(DetailActivity.this, "Data Deleted!", Toast.LENGTH_LONG).show();
                    }
                });
        alert.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (itemsId > 0) {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_edit) {
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("id", itemsId);
                intent.putExtra("name", itemsName);
                intent.putExtra("brand", itemsBrand);
                intent.putExtra("price", itemsPrice);

                startActivity(intent);
            }
            else if (id == R.id.action_delete) {
                delBox();
            }

        }
        else {
            Toast.makeText(this, "Error Get Data, try again!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DetailActivity.class);
    }

    public String numberFormat(String s) {
        int number      = Integer.parseInt(s);
        String result   = "Rp. " + NumberFormat.getInstance().format(number);

        return result;
    }


}
