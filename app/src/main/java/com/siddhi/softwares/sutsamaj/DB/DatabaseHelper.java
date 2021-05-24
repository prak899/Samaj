package com.siddhi.softwares.sutsamaj.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.siddhi.softwares.sutsamaj.Model.GeneralModel;
import com.siddhi.softwares.sutsamaj.Model.MembersListModel;
import com.siddhi.softwares.sutsamaj.Model.PermanenetAddress;
import com.siddhi.softwares.sutsamaj.Model.SpouseModel;
import com.siddhi.softwares.sutsamaj.Model.TempAddress;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "SUTSAMAJ";
    public static final String TABLE_NAME1 = "SUTSAMAJ_GENERAL";
    public static final String TABLE_NAME2 = "SUTSAMAJ_P_ADDRESS";
    public static final String TABLE_NAME3 = "SUTSAMAJ_T_ADDRESS";
    public static final String TABLE_NAME4 = "SUTSAMAJ_SPOUSE";

    // Table columns FOR sutsamaj
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String RELATION = "relation";
    public static final String EDUCATION = "education";
    public static final String CAST = "castin";
    public static final String EMPLOYMENT = "employment";
    public static final String GENDER = "gender";

    // Table columns FOR general
    public static final String G_ID = "_id";
    public static final String G_CATAGORY = "catagory";
    public static final String G_NAME = "name";
    public static final String G_FATHERNAME = "fathername";
    public static final String G_AGE = "age";
    public static final String G_MOBILE = "mobile";
    public static final String G_CAST = "g_cast";
    public static final String G_EDUCATION = "education";
    public static final String G_GOTRA = "gotra";
    public static final String G_CAST_IN_CERTIFICATE = "castin";
    public static final String G_EMPLOYMENT = "employment";
    public static final String G_PHOTO = "photo";

    // Table columns FOR P_address
    public static final String P_ID = "_id";
    public static final String P_ADDRESS = "address";
    public static final String P_PIN = "pin";
    public static final String P_STATE = "state";
    public static final String P_DISTRICT = "district";
    public static final String P_TEHESIL = "tehesil";
    public static final String P_VILLAGE = "village";

    // Table columns FOR T_address
    public static final String T_ID = "_id";
    public static final String T_ADDRESS = "address";
    public static final String T_PIN = "pin";
    public static final String T_STATE = "state";
    public static final String T_DISTRICT = "district";
    public static final String T_TEHESIL = "tehesil";
    public static final String T_VILLAGE = "village";

    // Table columns FOR SPOUSE
    public static final String SP_ID = "_id";
    public static final String SP_NAME = "name";
    public static final String SP_FATHER= "father";
    public static final String SP_ADDRESS = "address";
    public static final String SP_PIN = "pin";
    public static final String SP_GOTRA = "gotra";
    public static final String SP_STATE = "state";
    public static final String SP_DISTRICT = "district";
    public static final String SP_TEHESIL = "tehesil";
    public static final String SP_VILLAGE = "village";
    public static final String SP_AGE = "_id";

    // Database Information
    static final String DB_NAME = "SUT_SAMAJ_SURVEY.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY , " + NAME + " TEXT NOT NULL, " + AGE + " INTEGER, " + RELATION + " INTEGER, " + EDUCATION + " INTEGER, " + CAST + " INTEGER, " + EMPLOYMENT + " INTEGER, " + GENDER + " TEXT);";

    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "(" + G_ID
            + " INTEGER PRIMARY KEY ," + G_CATAGORY + " TEXT, " + G_NAME + " TEXT NOT NULL, " + G_FATHERNAME + " TEXT, " + G_AGE + " INTEGER, " + G_MOBILE + " INTEGER, " + G_CAST + " INTEGER, " + G_GOTRA + " INTEGER, " + G_CAST_IN_CERTIFICATE + " INTEGER, " + G_EMPLOYMENT + " INTEGER, "+ G_EDUCATION + " INTEGER, "+ G_PHOTO + " BLOB);";

    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" + P_ID
            + " INTEGER PRIMARY KEY ," + P_ADDRESS + " TEXT, " + P_PIN + " INTEGER, " + P_STATE + " INTEGER, " + P_DISTRICT + " INTEGER, " + P_TEHESIL + " INTEGER, "+ P_VILLAGE + " INTEGER);";

    private static final String CREATE_TABLE3 = "create table " + TABLE_NAME3 + "(" + T_ID
            + " INTEGER PRIMARY KEY ," + T_ADDRESS + " TEXT, " + T_PIN + " INTEGER, " + T_STATE + " INTEGER, " + T_DISTRICT + " INTEGER, " + T_TEHESIL + " INTEGER , " + T_VILLAGE + " INTEGER);";

    private static final String CREATE_TABLE4 = "create table " + TABLE_NAME4 + "(" + SP_ID
            + " INTEGER PRIMARY KEY ," + SP_NAME + " TEXT, " + SP_FATHER + " TEXT, " + SP_ADDRESS + " TEXT, " + SP_PIN + " INTEGER, " + SP_GOTRA + " INTEGER, " + SP_STATE + " INTEGER, " + SP_DISTRICT + " INTEGER, " + SP_TEHESIL + " INTEGER , " + SP_VILLAGE + " INTEGER);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    public ArrayList<MembersListModel> getmembers() {
        ArrayList<MembersListModel> arrayList = new ArrayList<>();

        // select all query
        String select_query=  "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MembersListModel noteModel = new MembersListModel();
                noteModel.setName(cursor.getString(1));
                noteModel.setAge(cursor.getInt(2));
                noteModel.setCast_certificate(cursor.getInt(5));
                noteModel.setEducation(cursor.getInt(4));
                noteModel.setEmployment(cursor.getInt(6));
                noteModel.setGender(cursor.getString(7));
                noteModel.setRelation(cursor.getString(3));

                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<GeneralModel> getGeneral() {
        ArrayList<GeneralModel> arrayList = new ArrayList<>();

        // select all query
        String select_query=  "SELECT *FROM " + TABLE_NAME1;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GeneralModel generalModel = new GeneralModel();
                generalModel.setHof(cursor.getString(1));
                generalModel.setName(cursor.getString(2));
                generalModel.setFather_name(cursor.getString(3));
                generalModel.setAge(cursor.getInt(4));
                generalModel.setMobile(cursor.getInt(5));
                generalModel.setCast(cursor.getInt(6));
                generalModel.setGotra(cursor.getInt(7));
                generalModel.setCast_certificate(cursor.getInt(8));
                generalModel.setEmployment(cursor.getInt(9));
                generalModel.setEducation(cursor.getInt(10));
                generalModel.setPhoto(cursor.getString(11));
                generalModel.setAny_other(cursor.getString(0));

                arrayList.add(generalModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<PermanenetAddress> getP_Address() {
        ArrayList<PermanenetAddress> arrayList = new ArrayList<>();

        // select all query
        String select_query=  "SELECT *FROM " + TABLE_NAME2;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PermanenetAddress permanenetAddress = new PermanenetAddress();
                permanenetAddress.setP_address(cursor.getString(1));
                permanenetAddress.setP_pin(cursor.getInt(2));
                permanenetAddress.setP_state(cursor.getInt(3));
                permanenetAddress.setP_district(cursor.getInt(4));
                permanenetAddress.setP_tehsil(cursor.getInt(5));
                permanenetAddress.setP_village(cursor.getInt(6));


                arrayList.add(permanenetAddress);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<TempAddress> getT_Address() {
        ArrayList<TempAddress> arrayList = new ArrayList<>();

        // select all query
        String select_query=  "SELECT *FROM " + TABLE_NAME3;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TempAddress tempAddress = new TempAddress();
                tempAddress.setAddress(cursor.getString(1));
                tempAddress.setPin(cursor.getInt(2));
                tempAddress.setState(cursor.getInt(3));
                tempAddress.setDistrict(cursor.getInt(4));
                tempAddress.setTehsil(cursor.getInt(5));
                tempAddress.setVillage(cursor.getInt(6));


                arrayList.add(tempAddress);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<SpouseModel> getSpouse() {
        ArrayList<SpouseModel> arrayList = new ArrayList<>();

        // select all query
        String select_query=  "SELECT *FROM " + TABLE_NAME4;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SpouseModel spouseModel = new SpouseModel();
                spouseModel.setW_name(cursor.getString(1));
                spouseModel.setW_father_name(cursor.getString(2));
                spouseModel.setW_address(cursor.getString(3));
                spouseModel.setW_pin(cursor.getInt(4));
                spouseModel.setW_gotra(cursor.getInt(5));
                spouseModel.setW_state(cursor.getInt(6));
                spouseModel.setW_district(cursor.getInt(7));
                spouseModel.setW_tehsil(cursor.getInt(8));
                spouseModel.setW_village(cursor.getInt(9));


                arrayList.add(spouseModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
}
