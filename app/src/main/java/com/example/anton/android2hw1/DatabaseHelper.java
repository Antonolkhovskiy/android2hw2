package com.example.anton.android2hw1;

/**
 * Created by Anton on 17.03.2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "Celebreties";
    static final String PERSONS_TABLE_NAME = "persons";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + PERSONS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " first_name TEXT NOT NULL, " +
                    " biography TEXT NOT NULL, " +
                    " last_name TEXT NOT NULL);";

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  PERSONS_TABLE_NAME);
        onCreate(db);
    }
}
