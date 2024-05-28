package com.example.bookstoreapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;


import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addKitap(String kitapAdi) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_KITAP_ADI, kitapAdi);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }
    public List<String> getAllKitaplar() {
        List<String> kitaplar = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                new String[]{DatabaseHelper.COLUMN_KITAP_ADI},
                null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String kitapAdi = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_KITAP_ADI));
                kitaplar.add(kitapAdi);
            }
            cursor.close();
        }

        return kitaplar;
    }
}
