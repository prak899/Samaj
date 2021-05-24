package com.siddhi.softwares.sutsamaj.Forms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;
import com.siddhi.softwares.sutsamaj.R;
import com.siddhi.softwares.sutsamaj.ViewDialog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;


public class AddressActivity extends AppCompatActivity {

    private ImageButton completeButton;
    Spinner spinner, spinner1, spinner2, spinner3;
    String[] categorydrop = {"Select Category", "Demo1", "Demo2", "Demo3"};

    private static final String TAG = SurveyForm.class.getSimpleName();
    /*private static String url1 = "http://siddhisoftwares.com/sutsamaj/api/getDistricts?state=1";
    private static String url2 = "http://siddhisoftwares.com/sutsamaj/api/getTehsils";
    private static String url3 = "http://siddhisoftwares.com/sutsamaj/api/getVillages";*/



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

    private ProgressDialog pDialog;
    private TextInputEditText Address, Pin;
    private DBManager dbManager;

    private Button Next, Recheck;

    SwipeRefreshLayout AddressRefresh;

    String stateId, districtId, tehesilsId, villagesId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        init();
        dbManager = new DBManager(this);
        dbManager.open();

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



        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, categorydrop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setOnItemSelectedListener(new AddressActivity.MySpinnerSelectedListener());

                stateId = getStatesId.get(position);
                GetDistrict(stateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1.setOnItemSelectedListener(new AddressActivity.MySpinnerSelectedListener1());

                districtId = getDistrictId.get(position);

                GetThehesils(districtId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddressActivity.this, "Please select", Toast.LENGTH_SHORT).show();
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setOnItemSelectedListener(new AddressActivity.MySpinnerSelectedListener2());
                tehesilsId = getTehesilsId.get(position);

                GetVillage(tehesilsId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddressActivity.this, "Please select", Toast.LENGTH_SHORT).show();
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        AddressRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                        AddressRefresh.setRefreshing(false);
                    }
                }
        );
    }

    public void InsertData(){
        //Editext data
        String address = Address.getText().toString();
        String pin = Pin.getText().toString();

        if(TextUtils.isEmpty(address)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

            Address.requestFocus();
        }else if (TextUtils.isEmpty(pin)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

            Pin.requestFocus();
        }else if (spinner1.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else if (spinner2.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

        }else if (spinner3.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else {
            String state= spinner.getSelectedItem().toString();
            String district= spinner1.getSelectedItem().toString();
            String tehesils= spinner2.getSelectedItem().toString();
            String village= spinner3.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(), TemporaryAddressActivity.class);
                i.putExtra("setState",stateId);
                i.putExtra("setDistrict",districtId);
                i.putExtra("setTehesils",tehesilsId);
                i.putExtra("setVillage",villagesId);

                i.putExtra("setAddress", address);
                i.putExtra("setPin", pin);
                Log.d("XValueX", village+state+district+tehesils);
            dbManager.P_A_insert(address, pin, stateId, districtId, tehesilsId, villagesId);
            startActivity(i);
            //startActivity(new Intent(this, TemporaryAddressActivity.class));
        }
    }

    public void Preview(){
        //Editext data
        String address = Address.getText().toString();
        String pin = Pin.getText().toString();

        Log.d("XvalX", "state-------"+ stateId + "sitrict-------"+ districtId + "tehesils-------"+ tehesilsId + "villages-------"+ villagesId);

        if(TextUtils.isEmpty(address)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

            Address.requestFocus();
        }else if (TextUtils.isEmpty(pin)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

            Pin.requestFocus();
        }else if (spinner1.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else if (spinner2.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");

        }else if (spinner3.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(AddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else {
            String state= spinner.getSelectedItem().toString();
            String district= spinner1.getSelectedItem().toString();
            String tehesils= spinner2.getSelectedItem().toString();
            String village= spinner3.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(), PreviewForm.class);
            i.putExtra("setCatagory",state);
            i.putExtra("setCast",district);
            i.putExtra("setGotra",tehesils);
            i.putExtra("setEducation",village);

            i.putExtra("setCastin", address);
            i.putExtra("setEmployement", pin);
            Log.d("XValueX", village+state+district+tehesils);
            //dbManager.P_A_insert(address, pin, state, district, tehesils, village);
            startActivity(i);
        }

    }


    private void init() {
        spinner= findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        spinner3= findViewById(R.id.spinner3);
        completeButton= findViewById(R.id.completeButton);

        //TextInputEditText Bindings
        Address= findViewById(R.id.address);
        Pin= findViewById(R.id.pin);

        Next=findViewById(R.id.nextAddress);
        Recheck=findViewById(R.id.preview);

        AddressRefresh= findViewById(R.id.swipeRefresh);
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
            Log.d(TAG, "onItemSelected: "+tehesilsId);
            GetVillage(tehesilsId);

            for (String s : getVillagesId){
                Log.d("ArrayVill", s);
            }
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddressActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = "http://sutsarthisamajcg.in/survey/api/getStates";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

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
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
                Log.e(TAG, "Couldn't get json from server.");
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
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getStates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            //StrRe();
            spinner.setAdapter(adapter);
        }

    }

    public void GetDistrict(String re){

        String a;

        //a  = re.replaceAll("[^0-9]", "");

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(AddressActivity.this);

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

                    if (re.equals("1"))
                        spinner1.setAdapter(adapter);
                    else{
                        spinner1.setAdapter(null);
                        //Toast.makeText(AddressActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
        String a;

        a  = re.replaceAll("[^0-9]", "");

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(AddressActivity.this);

        String url = "http://sutsarthisamajcg.in/survey/api/getTehsils?district="+a;

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, getTehesils );
                    adapter.notifyDataSetChanged();
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
        String a;

        a  = re.replaceAll("[^0-9]", "");

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(AddressActivity.this);

        String url = "http://sutsarthisamajcg.in/survey/api/getVillages?tehsil="+a;

        Log.d("XurlX", url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {
                    getVillagesId.clear();
                    getVillages.clear();
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
                    spinner3.setAdapter(adapter);


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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