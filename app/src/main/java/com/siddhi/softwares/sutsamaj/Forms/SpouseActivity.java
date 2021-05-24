package com.siddhi.softwares.sutsamaj.Forms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.DemoAddMmbr;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;
import com.siddhi.softwares.sutsamaj.R;
import com.siddhi.softwares.sutsamaj.ViewDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpouseActivity extends AppCompatActivity {

    private ImageButton completeButton;
    Spinner spinner, spinner1, spinner2, spinner3, spinner4;

    private ProgressDialog pDialog;
    private static String url1 = "http://sutsarthisamajcg.in/survey/api/getGotras";
    private static String url =  "http://sutsarthisamajcg.in/survey/api/getStates";


    ArrayList<String> getGotras;
    ArrayList<String> getGotrasId;

    //For storing name
    ArrayList<String> getStates;
    ArrayList<String> getDistrict;
    ArrayList<String> getTehesils;
    ArrayList<String> getVillages;

    //For storing id's
    ArrayList<String> getStatesId;
    ArrayList<String> getDistrictId;
    ArrayList<String> getTehesilsId;
    ArrayList<String> getVillagesId;

    private TextInputEditText WName, WHusbandname, WAddress, WPin;
    private DBManager dbManager;

    private Button Next, Recheck;

    private SwipeRefreshLayout SpouseRefresh;

    String gotraId, stateId, districtId, tehesilsId, villagesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spouse);

        init();
        dbManager = new DBManager(this);
        dbManager.open();

        getGotras = new ArrayList<>();
        getGotrasId = new ArrayList<>();

        //For name
        getStates= new ArrayList<>();
        getDistrict= new ArrayList<>();
        getTehesils= new ArrayList<>();
        getVillages= new ArrayList<>();

        //For id's
        getStatesId= new ArrayList<>();
        getDistrictId= new ArrayList<>();
        getTehesilsId= new ArrayList<>();
        getVillagesId= new ArrayList<>();

        new GetContacts().execute();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gotraId = getGotrasId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1.setOnItemSelectedListener(new SpouseActivity.MySpinnerSelectedListener());
                stateId = getStatesId.get(position);
                GetDistrict(stateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setOnItemSelectedListener(new SpouseActivity.MySpinnerSelectedListener1());
                districtId = getDistrictId.get(position);
                GetThehesils(districtId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner3.setOnItemSelectedListener(new SpouseActivity.MySpinnerSelectedListener2());

                tehesilsId = getTehesilsId.get(position);
                GetVillage(tehesilsId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                villagesId = getVillagesId.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        completeButton.setOnClickListener(v-> {
            InsertData();
        });

        Next.setOnClickListener(v-> {
            InsertData();
        });
        Recheck.setOnClickListener(v-> {
            Preview();
        });

        SpouseRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                        SpouseRefresh.setRefreshing(false);
                    }
                }
        );
    }



    private void InsertData() {
        //Editext data
        String Spname = WName.getText().toString();
        String Sphusband = WHusbandname.getText().toString();
        String Spaddress = WAddress.getText().toString();
        String Sppin = WPin.getText().toString();



        if(TextUtils.isEmpty(Spname)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            WName.requestFocus();
        }else if (TextUtils.isEmpty(Sphusband)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            WHusbandname.requestFocus();
        }else if (TextUtils.isEmpty(Spaddress)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            WAddress.requestFocus();
        }else if (TextUtils.isEmpty(Sppin)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            WPin.requestFocus();
        }else if (spinner1.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
        }else if (spinner2.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");

        }else if (spinner3.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
        } else if (spinner4.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
        }else {

            dbManager.SP_insert(Spname, Sphusband, Spaddress, Sppin, gotraId, stateId, districtId, tehesilsId, villagesId);
            startActivity(new Intent(this, MembersActivity.class));

        }
    }

    private void Preview(){
        {
            //Editext data
            String Spname = WName.getText().toString();
            String Sphusband = WHusbandname.getText().toString();
            String Spaddress = WAddress.getText().toString();
            String Sppin = WPin.getText().toString();



            if(TextUtils.isEmpty(Spname)) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(this, "Please fill in a valid value for all the required fields.");

                WName.requestFocus();
            }else if (TextUtils.isEmpty(Sphusband)){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(this, "Please fill in a valid value for all the required fields.");

                WHusbandname.requestFocus();
            }else if (TextUtils.isEmpty(Spaddress)){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(this, "Please fill in a valid value for all the required fields.");

                WAddress.requestFocus();
            }else if (TextUtils.isEmpty(Sppin)){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(this, "Please fill in a valid value for all the required fields.");

                WPin.requestFocus();
            }else if (spinner1.getCount()==0){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
            }else if (spinner2.getCount()==0){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");

            }else if (spinner3.getCount()==0){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
            } else if (spinner4.getCount()==0){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(SpouseActivity.this, "Please fill in a valid value for all the required fields.");
            }else {
                String Spgotra= spinner.getSelectedItem().toString();
                String Spstate= spinner1.getSelectedItem().toString();
                String Spdistrict= spinner2.getSelectedItem().toString();
                String Sptehesils= spinner3.getSelectedItem().toString();
                String Spvillage= spinner4.getSelectedItem().toString();

                Intent i = new Intent(getApplicationContext(), PreviewForm.class);
                i.putExtra("setCatagory", Spgotra);
                i.putExtra("setCast", Spstate);
                i.putExtra("setGotra", Spdistrict);
                i.putExtra("setEducation", Sptehesils);
                i.putExtra("setCastin", Spvillage);

                i.putExtra("setEmployement", Spname);
                i.putExtra("setName", Sphusband);
                i.putExtra("setFather", Spaddress);
                i.putExtra("setAge", Sppin);

                startActivity(i);

            }
        }
    }

    private void init() {
        spinner= findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        spinner3= findViewById(R.id.spinner3);
        spinner4= findViewById(R.id.spinner4);
        completeButton= findViewById(R.id.completeButton);

        //TextInputEditText Bindings
        WName=findViewById(R.id.Wname);
        WHusbandname=findViewById(R.id.Whusbandname);
        WAddress=findViewById(R.id.Waddress);
        WPin=findViewById(R.id.Wpin);

        Next=findViewById(R.id.nextAddress);
        Recheck=findViewById(R.id.preview);

        SpouseRefresh= findViewById(R.id.swipeRefresh);
    }
    public class MySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            stateId = getStatesId.get(pos);
            GetDistrict(stateId);

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
    public class MySpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            districtId = getDistrictId.get(pos);
            GetThehesils(districtId);

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
    public class MySpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            tehesilsId = getTehesilsId.get(pos);
            GetVillage(tehesilsId);

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SpouseActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(url);
            String jsonStr1 = sh.makeServiceCall(url1);

            if (jsonStr1 !=null && jsonStr != null) {
                try {


                    JSONArray contacts1 = new JSONArray(jsonStr1);

                    // looping through All Contacts
                    for (int i = 0; i < contacts1.length(); i++) {
                        JSONObject c = contacts1.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");


                        // adding contact to contact list
                        getGotras.add(name);
                        getGotrasId.add(id);

                    }
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");


                        // adding contact to contact list
                        getStates.add(name);
                        getStatesId.add(id);
                    }

                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getGotras);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getStates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner1.setAdapter(adapter1);

        }

    }

    public void GetDistrict(String re){

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(SpouseActivity.this);

        String url = "http://sutsarthisamajcg.in/survey/api/getDistricts?state="+re;

        Log.d("XurlX", url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {

                    JSONArray contacts = new JSONArray(response);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");

                        Log.d("XhdsgfX", name+id);

                        // adding contact to contact list
                        getDistrict.add(name);
                        getDistrictId.add(id);

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getDistrict);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter);


                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        ExampleRequestQueue.add(ExampleStringRequest);

    }
    public void GetThehesils(String re){

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        String url = "http://sutsarthisamajcg.in/survey/api/getTehsils?district="+re;

        Log.d("XurlX", url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {
                    getTehesils.clear();
                    getTehesilsId.clear();
                    JSONArray contacts = new JSONArray(response);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");

                        Log.d("XhdsgfX", name+id);

                        // adding contact to contact list
                        getTehesils.add(name);
                        getTehesilsId.add(id);

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getTehesils);
                    adapter.notifyDataSetChanged();
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner3.setAdapter(adapter);

                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        ExampleRequestQueue.add(ExampleStringRequest);

    }
    private void GetVillage(String re) {

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(SpouseActivity.this);

        String url = "http://sutsarthisamajcg.in/survey/api/getVillages?tehsil="+re;

        Log.d("XurlX", url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {
                    getVillages.clear();
                    getVillagesId.clear();
                    JSONArray contacts = new JSONArray(response);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");

                        Log.d("XhdsgfX", name+id);

                        // adding contact to contact list
                        getVillages.add(name);
                        getVillagesId.add(id);

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getVillages);
                    adapter.notifyDataSetChanged();
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(adapter);

                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        ExampleRequestQueue.add(ExampleStringRequest);
    }
}