package com.siddhi.softwares.sutsamaj.Forms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.siddhi.softwares.sutsamaj.CustomBottomSheetDialogFragment;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.DB.DatabaseHelper;
import com.siddhi.softwares.sutsamaj.DemoAddMmbr;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;
import com.siddhi.softwares.sutsamaj.Handler.VollyMultipartRequest;
import com.siddhi.softwares.sutsamaj.ListActivity;
import com.siddhi.softwares.sutsamaj.MainActivity;
import com.siddhi.softwares.sutsamaj.Model.GeneralModel;
import com.siddhi.softwares.sutsamaj.Model.MembersListModel;
import com.siddhi.softwares.sutsamaj.Model.PermanenetAddress;
import com.siddhi.softwares.sutsamaj.Model.SpouseModel;
import com.siddhi.softwares.sutsamaj.Model.TempAddress;
import com.siddhi.softwares.sutsamaj.R;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME1;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME2;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME3;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME4;

public class MembersActivity extends AppCompatActivity {
    Button RegisterButton, FinalSubmit;
    LinearLayout linearLayout;
    ImageButton imageButton, StartRide;

    private static final String TAG = SurveyForm.class.getSimpleName();

    private static String url1 = "http://sutsarthisamajcg.in/survey/api/getRelations";
    private static String url2 = "http://sutsarthisamajcg.in/survey/api/getEducation";
    private static String url3 = "http://sutsarthisamajcg.in/survey/api/getCastInCertificates";
    private static String url4 = "http://sutsarthisamajcg.in/survey/api/memberSave";

    private static String ROOT_URL = "http://sutsarthisamajcg.in/survey/api/getEmployment";
    DatabaseHelper dbhelperr;

    ArrayList<MembersListModel> arraylistmembers = new ArrayList<>();

    ArrayList<GeneralModel> arrayListgeneral = new ArrayList<>();
    ArrayList<TempAddress> tempAddressArrayList = new ArrayList<>();
    ArrayList<PermanenetAddress> permanenetAddressArrayList = new ArrayList<>();
    ArrayList<SpouseModel> spouseModelArrayList = new ArrayList<>();

    private ProgressDialog pDialog;
    Spinner spinner, spinner1, spinner2, spinner3;
    TextInputLayout EmployeeDetailLayout;

    HttpPost httppost;

    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    private DBManager dbManager;

    private ListView listView, listView1;

    private SimpleCursorAdapter adapter, adapter1;

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.GENDER,
            DatabaseHelper.AGE, DatabaseHelper.CAST, DatabaseHelper.RELATION, DatabaseHelper.EMPLOYMENT};

    final int[] to = new int[]{R.id.id, R.id.title, R.id.desc, R.id.desc1};

    final String[] from1 = new String[]{DatabaseHelper.G_ID,
            DatabaseHelper.G_NAME, DatabaseHelper.G_CATAGORY,
            DatabaseHelper.G_AGE, DatabaseHelper.G_CAST, DatabaseHelper.G_MOBILE, DatabaseHelper.G_EMPLOYMENT};

    final int[] to1 = new int[]{R.id.id, R.id.title, R.id.desc, R.id.desc1};

    private Button ShowMember;

    private SwipeRefreshLayout MembersRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        init();
        dbhelperr = new DatabaseHelper(this);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor1 = dbManager.fetch1();

        adapter1 = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor1, from1, to1, 0);
        adapter1.notifyDataSetChanged();

        listView1.setAdapter(adapter1);

        RegisterButton.setOnClickListener(v -> {
            new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");

            //ShowMember.setVisibility(View.VISIBLE);
        });

        StartRide.setOnClickListener(v -> {

            dialog = ProgressDialog.show(MembersActivity.this, "",
                    "Uploading data...", true);
            new Thread(new Runnable() {
                public void run() {
                    startingRide();

                }
            }).start();
        });

        FinalSubmit.setOnClickListener(v -> {

            dialog = ProgressDialog.show(MembersActivity.this, "",
                    "Uploading data...", true);
            new Thread(new Runnable() {
                public void run() {
                    startingRide();
                }
            }).start();
        });

        ShowMember.setOnClickListener(v -> {
            startActivity(new Intent(this, ListActivity.class));
        });

        MembersRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                        MembersRefresh.setRefreshing(false);
                    }
                }
        );
    }

    private void init() {
        //Spinner Bindings
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        RegisterButton = findViewById(R.id.register);
        FinalSubmit = findViewById(R.id.register1);
        ShowMember = findViewById(R.id.register2);
        linearLayout = findViewById(R.id.bottom_sheet);
        imageButton = findViewById(R.id.completeButton);

        StartRide = findViewById(R.id.uploadDB);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        EmployeeDetailLayout = findViewById(R.id.employeDetailsLayout);

        listView = (ListView) findViewById(R.id.list_view);
        listView1 = findViewById(R.id.list_view1);
        listView.setEmptyView(findViewById(R.id.empty));

        MembersRefresh = findViewById(R.id.swipeRefresh);
    }

    public void complete(View v) {
        /*Intent add_mem = new Intent(this, DemoAddMmbr.class);
        startActivity(add_mem);*/
        new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
    }


    void startingRide(){
        try{
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://sutsarthisamajcg.in/survey/api/memberSave"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(1);


            arrayListgeneral = dbhelperr.getGeneral();
            for (int i=0;i<arrayListgeneral.size();i++) {
                Log.d("MyTag","mmmmm name :- "+arrayListgeneral.get(i).getName());
                Log.d("MyTag","mmmmm casr :- "+arrayListgeneral.get(i).getCast());
                Log.d("MyTag","mmmmm age :- "+arrayListgeneral.get(i).getAge());
                Log.d("MyTag","mmmmm cast :- "+arrayListgeneral.get(i).getCast_certificate());
                Log.d("MyTag","mmmmm education :- "+arrayListgeneral.get(i).getEducation());
                Log.d("MyTag","mmmmm employement :- "+arrayListgeneral.get(i).getEmployment());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getMobile());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getHof());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getPhoto());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getFather_name());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getGotra());
                Log.d("MyTag","mmmmm gender :- "+arrayListgeneral.get(i).getMobile());

                //General Sections
                nameValuePairs.add(new BasicNameValuePair("hof", arrayListgeneral.get(i).getHof()));
                nameValuePairs.add(new BasicNameValuePair("name", arrayListgeneral.get(i).getName()));
                nameValuePairs.add(new BasicNameValuePair("father_name", arrayListgeneral.get(i).getFather_name()));
                nameValuePairs.add(new BasicNameValuePair("age", String.valueOf(arrayListgeneral.get(i).getAge())));
                nameValuePairs.add(new BasicNameValuePair("cast", String.valueOf(arrayListgeneral.get(i).getCast())));
                nameValuePairs.add(new BasicNameValuePair("cast_certificate", String.valueOf(arrayListgeneral.get(i).getCast_certificate())));
                nameValuePairs.add(new BasicNameValuePair("mobile", String.valueOf(arrayListgeneral.get(i).getMobile())));
                nameValuePairs.add(new BasicNameValuePair("gotra", String.valueOf(arrayListgeneral.get(i).getGotra())));
                nameValuePairs.add(new BasicNameValuePair("education", String.valueOf(arrayListgeneral.get(i).getEducation())));
                nameValuePairs.add(new BasicNameValuePair("employment", String.valueOf(arrayListgeneral.get(i).getEmployment())));
                nameValuePairs.add(new BasicNameValuePair("any_other", ""));
                nameValuePairs.add(new BasicNameValuePair("photo", String.valueOf(arrayListgeneral.get(i).getPhoto())));
            }

            permanenetAddressArrayList = dbhelperr.getP_Address();
            for (int i=0;i<permanenetAddressArrayList.size();i++) {//General Sections

                nameValuePairs.add(new BasicNameValuePair("p_address", permanenetAddressArrayList.get(i).getP_address()));
                nameValuePairs.add(new BasicNameValuePair("p_state", String.valueOf(permanenetAddressArrayList.get(i).getP_state())));
                nameValuePairs.add(new BasicNameValuePair("p_district", String.valueOf(permanenetAddressArrayList.get(i).getP_district())));
                nameValuePairs.add(new BasicNameValuePair("p_tehsil", String.valueOf(permanenetAddressArrayList.get(i).getP_tehsil())));
                nameValuePairs.add(new BasicNameValuePair("p_village", String.valueOf(permanenetAddressArrayList.get(i).getP_village())));
                nameValuePairs.add(new BasicNameValuePair("p_pin", String.valueOf(permanenetAddressArrayList.get(i).getP_pin())));

            }
            tempAddressArrayList = dbhelperr.getT_Address();
            for (int i=0;i<tempAddressArrayList.size();i++) {//General Sections

                nameValuePairs.add(new BasicNameValuePair("address", tempAddressArrayList.get(i).getAddress()));
                nameValuePairs.add(new BasicNameValuePair("state", String.valueOf(tempAddressArrayList.get(i).getState())));
                nameValuePairs.add(new BasicNameValuePair("district", String.valueOf(tempAddressArrayList.get(i).getDistrict())));
                nameValuePairs.add(new BasicNameValuePair("tehsil", String.valueOf(tempAddressArrayList.get(i).getTehsil())));
                nameValuePairs.add(new BasicNameValuePair("village", String.valueOf(tempAddressArrayList.get(i).getVillage())));
                nameValuePairs.add(new BasicNameValuePair("pin", String.valueOf(tempAddressArrayList.get(i).getPin())));

            }
            spouseModelArrayList = dbhelperr.getSpouse();
            for (int i=0;i<spouseModelArrayList.size();i++) {//General Sections

                nameValuePairs.add(new BasicNameValuePair("w_name", spouseModelArrayList.get(i).getW_name()));
                nameValuePairs.add(new BasicNameValuePair("w_father_name", spouseModelArrayList.get(i).getW_father_name()));
                nameValuePairs.add(new BasicNameValuePair("w_gotra", String.valueOf(spouseModelArrayList.get(i).getW_gotra())));
                nameValuePairs.add(new BasicNameValuePair("w_address", spouseModelArrayList.get(i).getW_address()));
                nameValuePairs.add(new BasicNameValuePair("w_state", String.valueOf(spouseModelArrayList.get(i).getW_state())));
                nameValuePairs.add(new BasicNameValuePair("w_district", String.valueOf(spouseModelArrayList.get(i).getW_district())));
                nameValuePairs.add(new BasicNameValuePair("w_tehsil", String.valueOf(spouseModelArrayList.get(i).getW_tehsil())));
                nameValuePairs.add(new BasicNameValuePair("w_village", String.valueOf(spouseModelArrayList.get(i).getW_village())));
                nameValuePairs.add(new BasicNameValuePair("w_pin", String.valueOf(spouseModelArrayList.get(i).getW_pin())));
            }



            //Family Member Section Start from children[0] to children[9] for 10 family members
            arraylistmembers = dbhelperr.getmembers();
            for (int i=0;i<arraylistmembers.size();i++)
            {

                nameValuePairs.add(new BasicNameValuePair("children["+i+"][name]",arraylistmembers.get(i).getName()));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][relation]",arraylistmembers.get(i).getRelation()));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][age]",String.valueOf(arraylistmembers.get(i).getAge())));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][cast_certificate]",String.valueOf(arraylistmembers.get(i).getCast_certificate())));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][education]",String.valueOf(arraylistmembers.get(i).getEducation())));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][employment]",String.valueOf(arraylistmembers.get(i).getEmployment())));
                nameValuePairs.add(new BasicNameValuePair("children["+i+"][gender]",arraylistmembers.get(i).getGender()));

            }

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            //response=httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);

            Log.i("UserLoginResponse" , String.valueOf(nameValuePairs));
            Log.i("XUserLoginResponseX" , String.valueOf(response));
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            });


            if(response.equalsIgnoreCase("{\"status\":\"Success\",\"msg\":\"Form Saved Successfully\"}")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        MembersActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                deleteId();
                                AlertDialog.Builder builder = new AlertDialog.Builder(MembersActivity.this);

                                builder.setMessage(R.string.text5)
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                Snackbar.make(findViewById(R.id.memberLay), "Succesfully Uploaded data", Snackbar.LENGTH_LONG).show();
                                                startActivity(new Intent(MembersActivity.this, MainActivity.class));
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        });
                    }
                });

            }else{
                Snackbar.make(findViewById(R.id.memberLay), "Server not respnding", Snackbar.LENGTH_LONG).show();

            }

        }catch(Exception e){
            dialog.dismiss();
            Log.i("Exce" ,"ex"+e.getMessage());
        }
    }
    public void deleteId() {
        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        sq.execSQL("delete from " + TABLE_NAME);
        sq.execSQL("delete from " + TABLE_NAME1);
        sq.execSQL("delete from " + TABLE_NAME2);
        sq.execSQL("delete from " + TABLE_NAME3);
        sq.execSQL("delete from " + TABLE_NAME4);

    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /*private void uploadBitmap() {

        VollyMultipartRequest volleyMultipartRequest = new VollyMultipartRequest(Request.Method.POST, ROOT_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError", "" + error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String er = "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";
                Map<String, String> params = new HashMap<>();
                params.put("address", Location.getText().toString());
                params.put("landmark", Landmark.getText().toString());
                params.put("type", Spinnertype);
                params.put("description", Description.getText().toString());
                params.put("number", numberShared);
                params.put("photo", er);


                //SuccessInflatMethod();
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //params.put("photo", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));

                //SuccessInflatMethod();
                return params;
            }

        };
    }*/
}

