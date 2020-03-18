package com.crud.sqlite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.NumberFormat;

public class DBHelper extends SQLiteOpenHelper {

    /*
    Deklarasi konstanta-konstanta yang di gunakan pada database
    seperti nama table, nama kolom, nama db dan versi dari database
     */
    public static final String TABLE_NAME       = "inventory";
    public static final String COLUMN_ID        = "items_id";
    public static final String COLUMN_NAME      = "items_name";
    public static final String COLUMN_BRAND     = "items_brand";
    public static final String COLUMN_PRICE     = "items_price";

    private static final String db_name         = "inventory.db";
    private static final int db_version         = 1;

    /* Create Table */
    private static final String db_create       = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " varchar(50) not null, "
            + COLUMN_BRAND + " varchar(50) not null, "
            + COLUMN_PRICE + " varchar(50) not null"
            + ")";


    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        // auto generated
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // dijalankan apabila mengupgrade database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.w(DBHelper.class.getName(), "upgrading database from version " + oldVersion + " To " + newVersion + " , which will destroy all old data");
    }

}
