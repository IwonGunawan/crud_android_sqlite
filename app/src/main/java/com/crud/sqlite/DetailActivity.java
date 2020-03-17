package com.crud.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class DetailActivity extends AppCompatActivity {

    private long itemsId;
    private String itemsName;
    private String itemsBrand;
    private String itemsPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Data");
        setSupportActionBar(toolbar);

        // initial
        TextView items_name   = (TextView) findViewById(R.id.items_name);
        TextView items_brand  = (TextView) findViewById(R.id.items_brand);
        TextView items_price  = (TextView) findViewById(R.id.items_price);

        // get intent
        Intent intent = getIntent();
        itemsId     = intent.getLongExtra("id", 0);
        itemsName   = intent.getStringExtra("name");
        itemsBrand  = intent.getStringExtra("brand");
        itemsPrice  = intent.getStringExtra("price");

        // set value
        if (itemsId > 0) {
            items_name.setText(itemsName);
            items_brand.setText(itemsBrand);
            items_price.setText(itemsPrice);
        }
        else {
            Toast.makeText(this, "Data not Found", Toast.LENGTH_LONG).show();
        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DetailActivity.class);
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

    public void delBox() {

    }

    public void delete() {

    }
}
