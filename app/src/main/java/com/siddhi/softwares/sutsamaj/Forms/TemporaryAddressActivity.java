package com.siddhi.softwares.sutsamaj.Forms;

import android.annotation.SuppressLint;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class TemporaryAddressActivity extends AppCompatActivity {
    private ImageButton completeButton;
    Spinner spinner, spinner1, spinner2, spinner3;
    String[] categorydrop = {"Select Category", "Demo1", "Demo2", "Demo3"};
    private Button sameAs;
    private TextInputEditText Address, Pin;

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
    private static final String TAG = SurveyForm.class.getSimpleName();
    private DBManager dbManager;
    private Button Next, Recheck;

    private SwipeRefreshLayout TemporaryRefresh;

    String stateId, districtId, tehesilsId, villagesId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary_address);

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

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categorydrop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setOnItemSelectedListener(new MySpinnerSelectedListener());

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
                spinner1.setOnItemSelectedListener(new MySpinnerSelectedListener1());

                districtId = getDistrictId.get(position);
                GetThehesils(districtId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setOnItemSelectedListener(new MySpinnerSelectedListener2());

                tehesilsId = getTehesilsId.get(position);
                GetVillage(tehesilsId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        completeButton.setOnClickListener(v-> {
            InsertData();
        });
        sameAs.setOnClickListener(v-> {
            String state= getIntent().getStringExtra("setState");
            String district= getIntent().getStringExtra("setDistrict");
            String tehesils= getIntent().getStringExtra("setTehesils");
            String village= getIntent().getStringExtra("setVillage");

            String address= getIntent().getStringExtra("setAddress");
            String pin= getIntent().getStringExtra("setPin");

            dbManager.T_A_insert(address, pin, state, district, tehesils, village);
            startActivity(new Intent(this, SpouseActivity.class));
        });
        Next.setOnClickListener(v-> {
            InsertData();
        });
        Recheck.setOnClickListener(v-> {
            Preview();
        });
        TemporaryRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                        TemporaryRefresh.setRefreshing(false);
                    }
                }
        );
    }


    private void init() {
        spinner= findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        spinner3= findViewById(R.id.spinner3);
        completeButton= findViewById(R.id.completeButton);

        sameAs= findViewById(R.id.completeButton1);
        //TextInputEditText Bindings
        Address= findViewById(R.id.address);
        Pin= findViewById(R.id.pin);

        Next=findViewById(R.id.nextAddress);
        Recheck=findViewById(R.id.preview);

        TemporaryRefresh= findViewById(R.id.swipeRefresh);
    }
    public void InsertData(){
        //Editext data
        String Tempaddress = Address.getText().toString();
        String Temppin = Pin.getText().toString();

        if(TextUtils.isEmpty(Tempaddress)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            Address.requestFocus();
        }else if (TextUtils.isEmpty(Temppin)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            Pin.requestFocus();
        }
        else if (spinner1.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else if (spinner2.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");

        }else if (spinner3.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else {
            String Tempstate= spinner.getSelectedItem().toString();
            String Tempdistrict= spinner1.getSelectedItem().toString();
            String Temptehesils= spinner2.getSelectedItem().toString();
            String village= spinner3.getSelectedItem().toString();

            dbManager.T_A_insert(Tempaddress, Temppin, stateId, districtId, tehesilsId, villagesId);
            startActivity(new Intent(this, SpouseActivity.class));
        }
    }

    public void Preview(){
        //Editext data
        String Tempaddress = Address.getText().toString();
        String Temppin = Pin.getText().toString();

        if(TextUtils.isEmpty(Tempaddress)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            Address.requestFocus();
        }else if (TextUtils.isEmpty(Temppin)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "Please fill in a valid value for all the required fields.");

            Pin.requestFocus();
        }
        else if (spinner1.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else if (spinner2.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");

        }else if (spinner3.getCount()==0){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(TemporaryAddressActivity.this, "Please fill in a valid value for all the required fields.");
        }else {
            String Tempstate= spinner.getSelectedItem().toString();
            String Tempdistrict= spinner1.getSelectedItem().toString();
            String Temptehesils= spinner2.getSelectedItem().toString();
            String village= spinner3.getSelectedItem().toString();

            Intent i = new Intent(getApplicationContext(), PreviewForm.class);
            i.putExtra("setCatagory",Tempstate);
            i.putExtra("setCast",Tempdistrict);
            i.putExtra("setGotra",Temptehesils);
            i.putExtra("setEducation",village);

            i.putExtra("setCastin", Tempaddress);
            i.putExtra("setEmployement", Temppin);
            startActivity(i);
        }
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
    @SuppressLint("StaticFieldLeak")
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TemporaryAddressActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = "https://sutsarthisamajcg.in/survey/api/getStates";
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

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(TemporaryAddressActivity.this);

        String url = "https://sutsarthisamajcg.in/survey/api/getDistricts?state="+re;

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
                        //Toast.makeText(TemporaryAddressActivity.this, "Error", Toast.LENGTH_SHORT).show();
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

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(TemporaryAddressActivity.this);

        String url = "https://sutsarthisamajcg.in/survey/api/getTehsils?district="+re;

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

        //String a=String.valueOf(2);
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(TemporaryAddressActivity.this);

        String url = "https://sutsarthisamajcg.in/survey/api/getVillages?tehsil="+re;

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