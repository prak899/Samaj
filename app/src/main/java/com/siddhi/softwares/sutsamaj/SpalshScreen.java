package com.siddhi.softwares.sutsamaj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.siddhi.softwares.sutsamaj.Login.LoginActivity;

public class SpalshScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.color_new_blue));
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_new_blue));
        }

        CreateDatabase();
        fillVersion();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getpage();
                finish();


            }
        }, 3000);

    }

    public void CreateDatabase() {
        SQLiteDatabase sq = openOrCreateDatabase("DBAttendance", MODE_PRIVATE, null);
        String FP = "CREATE TABLE IF NOT EXISTS LoginMSt(id INTEGER PRIMARY KEY, username TEXT NOT NULL, password TEXT NOT NULL)";
        //EmpName nvarchar null, EmpID INTEGER null, DesignationID INTEGER null, BranchID INTEGER null
        //table created
        sq.execSQL(FP);

    }
    private void fillVersion() {
        String appName = getString(R.string.app_name);
        ((TextView) findViewById(R.id.tv_splash_app_title)).setText(appName);
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            ((TextView) findViewById(R.id.tv_splash_app_version)).setText(getString(R.string.splash_app_version, versionName));
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("TAG", e.getMessage());
        }
    }

    public void getpage() {
        SQLiteDatabase sq=openOrCreateDatabase("DBAttendance", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  LoginMSt where id=1",null);

        if(c1424.moveToFirst()){

            do{
                startActivity(new Intent(SpalshScreen.this, MainActivity.class));
            }while(c1424.moveToNext());
        }
        else
        {
            startActivity(new Intent(SpalshScreen.this, LoginActivity.class));
        }
    }
}

