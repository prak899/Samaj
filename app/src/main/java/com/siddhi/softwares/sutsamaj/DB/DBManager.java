package com.siddhi.softwares.sutsamaj.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //FOR MEMNERS
    public void insert(String name, String age, String relation, String education, String castin, String employment, String gender) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.AGE, age);

        contentValue.put(DatabaseHelper.RELATION, relation);
        contentValue.put(DatabaseHelper.EDUCATION, education);
        contentValue.put(DatabaseHelper.CAST, castin);
        contentValue.put(DatabaseHelper.EMPLOYMENT, employment);
        contentValue.put(DatabaseHelper.GENDER, gender);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    //FOR GENERAL
    public void G_insert(String catagory, String name, String fathername, String age, String mobile, String cast, String gotra, String castin, String employment, String education, Bitmap photo) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.G_CATAGORY, catagory);
        contentValue.put(DatabaseHelper.G_NAME, name);
        contentValue.put(DatabaseHelper.G_FATHERNAME, fathername);
        contentValue.put(DatabaseHelper.G_AGE, age);
        contentValue.put(DatabaseHelper.G_MOBILE, mobile);
        contentValue.put(DatabaseHelper.G_CAST, cast);
        contentValue.put(DatabaseHelper.G_GOTRA, gotra);
        contentValue.put(DatabaseHelper.G_CAST_IN_CERTIFICATE, castin);
        contentValue.put(DatabaseHelper.G_EMPLOYMENT, employment);
        contentValue.put(DatabaseHelper.G_EDUCATION, education);
        contentValue.put(DatabaseHelper.G_PHOTO, String.valueOf(photo));

        database.insert(DatabaseHelper.TABLE_NAME1, null, contentValue);
    }

    //FOR Permanent_ADDRESS
    public void P_A_insert(String address, String pin, String state, String district, String tehesil, String village) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.P_ADDRESS, address);
        contentValue.put(DatabaseHelper.P_PIN, pin);
        contentValue.put(DatabaseHelper.P_STATE, state);
        contentValue.put(DatabaseHelper.P_DISTRICT, district);
        contentValue.put(DatabaseHelper.P_TEHESIL, tehesil);
        contentValue.put(DatabaseHelper.P_VILLAGE, village);

        database.insert(DatabaseHelper.TABLE_NAME2, null, contentValue);
    }
    //FOR Temprary_ADDRESS
    public void T_A_insert(String address, String pin, String state, String district, String tehesil, String village) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.T_ADDRESS, address);
        contentValue.put(DatabaseHelper.T_PIN, pin);
        contentValue.put(DatabaseHelper.T_STATE, state);
        contentValue.put(DatabaseHelper.T_DISTRICT, district);
        contentValue.put(DatabaseHelper.T_TEHESIL, tehesil);
        contentValue.put(DatabaseHelper.T_VILLAGE, village);

        database.insert(DatabaseHelper.TABLE_NAME3, null, contentValue);
    }

    //FOR SPOUSE
    public void SP_insert(String name, String father, String address, String pin, String gotra, String state, String district, String tehesil, String village) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SP_NAME, name);
        contentValue.put(DatabaseHelper.SP_FATHER, father);
        contentValue.put(DatabaseHelper.SP_ADDRESS, address);
        contentValue.put(DatabaseHelper.SP_PIN, pin);
        contentValue.put(DatabaseHelper.SP_GOTRA, gotra);
        contentValue.put(DatabaseHelper.SP_STATE, state);
        contentValue.put(DatabaseHelper.SP_DISTRICT, district);
        contentValue.put(DatabaseHelper.SP_TEHESIL, tehesil);
        contentValue.put(DatabaseHelper.SP_VILLAGE, village);

        database.insert(DatabaseHelper.TABLE_NAME4, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.AGE, DatabaseHelper.RELATION,
        DatabaseHelper.EDUCATION, DatabaseHelper.CAST, DatabaseHelper.EMPLOYMENT, DatabaseHelper.GENDER};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch1() {
        String[] columns = new String[] { DatabaseHelper.G_ID, DatabaseHelper.G_NAME, DatabaseHelper.G_AGE, DatabaseHelper.G_CATAGORY,
                DatabaseHelper.G_MOBILE, DatabaseHelper.G_CAST, DatabaseHelper.G_EMPLOYMENT};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME1, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String age, String relation, String education, String castin, String employment) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.AGE, age);

        contentValues.put(DatabaseHelper.RELATION, relation);
        contentValues.put(DatabaseHelper.EDUCATION, education);
        contentValues.put(DatabaseHelper.CAST, castin);
        contentValues.put(DatabaseHelper.EMPLOYMENT, employment);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public boolean deleteTitle(long _id)
    {
        return database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null) > 0;
    }
}
