package com.siddhi.softwares.sutsamaj.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.siddhi.softwares.sutsamaj.Forms.AddressActivity;
import com.siddhi.softwares.sutsamaj.MainActivity;
import com.siddhi.softwares.sutsamaj.R;
import com.siddhi.softwares.sutsamaj.ViewDialog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

    private TextView Login;

    EditText UserName, Password;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    String result="";
    String status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        Login.setOnClickListener(v-> {
            dialog = ProgressDialog.show(LoginActivity.this, "",
                    "Validating user...", true);
            new Thread(new Runnable() {
                public void run() {
                    login();
                }
            }).start();

        });
    }

    private void init() {
        Login= findViewById(R.id.tv_btn_login);

        UserName= findViewById(R.id.username);
        Password= findViewById(R.id.password);
    }
    void login(){
        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://sutsarthisamajcg.in/survey/api/login"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("username",UserName.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("password",Password.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);

            JSONObject json= null;  //your response
            try {
                json = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                //String id, name, mobile, member_added;
                result = json.getString("userdata");
                status = json.getString("status");

                /*id = json.getString("id");
                name = json.getString("name");
                mobile = json.getString("mobile");
                member_added = json.getString("member_added");*/


                Log.d("Success", status);
                //Log.d("XmsgX", id+"---------"+name+"--------"+mobile+"--------"+member_added+"---------");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Log.i("ChallanResponse" ,response);
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            });


            //"{\"responce\": true}"
            if(status.equalsIgnoreCase("Success")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        //SaveData1(result);
                        SaveData(UserName.getText().toString().trim(), Password.getText().toString().trim());
                        Toast.makeText(LoginActivity.this, "ID Verified", Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else{
                showAlert();
            }

        }catch(Exception e){
            dialog.dismiss();
            Log.i("Exce" ,"e"+e.getMessage());
        }
    }

    private void SaveData1(String result) {
        SQLiteDatabase sq = openOrCreateDatabase("DBAttendance", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  LoginMSt where id=1", null);

        if (c1424.moveToFirst()) {

            do {
                ContentValues cr4 = new ContentValues();
                cr4.put("result", result);


                sq.insert("LoginMSt", null, cr4);
                sq.update("LoginMSt", cr4, "id='1'", null);

            } while (c1424.moveToNext());
        } else {
            ContentValues cr4 = new ContentValues();
            cr4.put("id", "1");
            cr4.put("result", result);

            sq.insert("LoginMSt", null, cr4);
        }
    }

    public void SaveData(String UserName, String Password) {
        SQLiteDatabase sq = openOrCreateDatabase("DBAttendance", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  LoginMSt where id=1", null);

        if (c1424.moveToFirst()) {

            do {
                ContentValues cr4 = new ContentValues();
                cr4.put("UserName", UserName);
                cr4.put("PassWord", Password);


                sq.insert("LoginMSt", null, cr4);
                sq.update("LoginMSt", cr4, "id='1'", null);

            } while (c1424.moveToNext());
        } else {
            ContentValues cr4 = new ContentValues();
            cr4.put("id", "1");
            cr4.put("UserName", UserName);
            cr4.put("PassWord", Password);

            sq.insert("LoginMSt", null, cr4);
        }
    }

    public void showAlert(){
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Validation Error.");
                builder.setMessage("Operator not Found.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}