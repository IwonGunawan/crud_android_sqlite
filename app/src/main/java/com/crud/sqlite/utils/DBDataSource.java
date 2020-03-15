package com.crud.sqlite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBDataSource {

    // inisiasi SQlite Database
    private SQLiteDatabase database;


    // inisiasi class DBHelper
    private DBHelper dbHelper;


    // ambil semua nama column
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_BRAND,
            DBHelper.COLUMN_PRICE
    };


    // DBHelper di instansiasi pada consturctor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    // membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }




    /* CREATE DATA */
    public Items create(String itemsName, String itemsBrand, String itemsPrice) {
        /*
        membuat sebuah content_values, yang berfungsi
        untuk memasangkan data dengan nama-nama kolom pada database
         */
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, itemsName);
        values.put(DBHelper.COLUMN_BRAND, itemsBrand);
        values.put(DBHelper.COLUMN_PRICE, itemsPrice);


        /* eksekusi sql insert, return INSERT_ID */
        long insertId   = database.insert(DBHelper.TABLE_NAME, null, values);

        /* setelah data masuk, memanggil sql select menggunakan cursor
        untuk melihat apakah data sudah masuk dengan menyesuaikan ID = insertID
         */
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns,
                DBHelper.COLUMN_ID + " = " + insertId,
                null,
                null,
                null,
                null
        );

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama ke dalam objek barang
        Items newItem = cursorToBarang(cursor);

        // close cursor
        cursor.close();

        return newItem;
    }

    public Items cursorToBarang(Cursor cursor) {

        // buat objek barang baru
        Items items = new Items();

        items.setItems_id(cursor.getLong(0));
        items.setItems_name(cursor.getString(1));
        items.setItems_brand(cursor.getString(2));
        items.setItems_price(cursor.getString(3));

        return items;
    }

    /* LISTING DATA */
    public ArrayList<Items> listing() {
        ArrayList<Items> listItem   = new ArrayList<Items>();

        // select all sql query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null);
        // pindah ke data paling pertama
        cursor.moveToFirst();

        // jika masih ada data, masukkan data barang ke daftar barang
        while (!cursor.isAfterLast()) {
            Items items = cursorToBarang(cursor);
            listItem.add(items);

            cursor.moveToNext();
        }

        // close cursor
        cursor.close();
        return listItem;
    }




}
