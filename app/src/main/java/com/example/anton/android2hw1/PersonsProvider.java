package com.example.anton.android2hw1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Anton on 17.03.2018.
 */

public class PersonsProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.anton.android2hw1";
    static final String URL = "content://" + PROVIDER_NAME + "/persons";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String BIOGRAPHY = "biography";

    private static HashMap<String, String> PERSONS_PROJECTION_MAP;
    private final String PERSONS_TABLE_NAME = "persons";

    static final int PERSONS = 1;
    static final int PERSON_ID = 2;

    private SQLiteDatabase db;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "persons", PERSONS);
        uriMatcher.addURI(PROVIDER_NAME, "persons/#", PERSON_ID);
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PERSONS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case PERSONS:
                qb.setProjectionMap(PERSONS_PROJECTION_MAP);
                break;

            case PERSON_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = FIRST_NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case PERSONS:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case PERSON_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * Add a new student record
         */
        long rowID = db.insert(	PERSONS_TABLE_NAME, "", contentValues);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
