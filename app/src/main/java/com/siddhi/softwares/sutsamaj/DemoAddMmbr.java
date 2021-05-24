package com.siddhi.softwares.sutsamaj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.Forms.MembersActivity;
import com.siddhi.softwares.sutsamaj.Forms.SurveyForm;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DemoAddMmbr extends Activity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    private DBManager dbManager;



    private static final String TAG = SurveyForm.class.getSimpleName();

    private static String url1 = "http://sutsarthisamajcg.in/survey/api/getRelations";
    private static String url2 = "http://sutsarthisamajcg.in/survey/api/getEducation";
    private static String url3 = "http://sutsarthisamajcg.in/survey/api/getCastInCertificates";
    private static String url4 = "http://sutsarthisamajcg.in/survey/api/getEmployment";



    ArrayList<String> getRelation;
    ArrayList<String> getCastInCertificates;
    ArrayList<String> getEdu;
    ArrayList<String> getEmployment;

    private ProgressDialog pDialog;
    Spinner spinner, spinner1, spinner2, spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("Add Record");

        setContentView(R.layout.activity_demo_add_mmbr);

        init();

        getRelation = new ArrayList<>();
        getEdu= new ArrayList<>();
        getCastInCertificates= new ArrayList<>();
        getEmployment= new ArrayList<>();

        new GetContacts().execute();
        //This is from SurveyForm

        /*Log.d("XfinalExtrasX",
                "\n"+"FromSurveyForm-----------"+catagory+cast+gotra+education+"EditeText----------------------"+headName+fatherName+age+number+"\n"
                +"FromAddressActivity-----------"+state+district+tehesils+"Edittext-----------------------------"+address+pin+"\n"
                +"FromTempraryActivity----------"+MTempstate+MTempdistrict+MTemptehesils+"Edittext-----------------"+Tempaddress+Temppin+"\n"
                +"FromSpouseActivity------------"+MSpgotra+MSpstate+MSpdistricts+MSptehesils+"Edittext----------"+Spname+Sphusband+Spaddress+Sppin);
*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setOnItemSelectedListener(new DemoAddMmbr.MySpinnerSelectedListener());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1.setOnItemSelectedListener(new DemoAddMmbr.MySpinnerSelectedListener());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setOnItemSelectedListener(new DemoAddMmbr.MySpinnerSelectedListener());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner3.setOnItemSelectedListener(new DemoAddMmbr.MySpinnerSelectedListener());
                String selected= spinner3.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                String MmbrRelation= spinner.getSelectedItem().toString();
                String MmbrCastInCertificates= spinner1.getSelectedItem().toString();
                String MmbrEducation= spinner2.getSelectedItem().toString();
                String MmbrEmployment= spinner3.getSelectedItem().toString();

                //ReplaceMent of memebers spinner data id
                String MMmbrrelation  = MmbrRelation.replaceAll("[^0-9]", "");
                String MMmbrcastincertificates  = MmbrCastInCertificates.replaceAll("[^0-9]", "");
                String MMbreducation  = MmbrEducation.replaceAll("[^0-9]", "");
                String MMmbremployment  = MmbrEmployment.replaceAll("[^0-9]", "");

                dbManager.insert(name, desc, MMmbrrelation, MMmbrcastincertificates, MMbreducation, MMmbremployment, MMmbremployment);

                Intent main = new Intent(DemoAddMmbr.this, MembersActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

    private void init() {
        //Spinner Bindings
        spinner= findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        spinner3= findViewById(R.id.spinner3);
    }
    public class MySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String selected = parent.getItemAtPosition(pos).toString();

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DemoAddMmbr.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response

            String jsonStr1 = sh.makeServiceCall(url1);
            String jsonStr2 = sh.makeServiceCall(url2);
            String jsonStr3 = sh.makeServiceCall(url3);
            String jsonStr4 = sh.makeServiceCall(url4);


            if (jsonStr1 !=null && jsonStr2 !=null && jsonStr3 !=null && jsonStr4 !=null) {
                try {

                    JSONArray contacts1 = new JSONArray(jsonStr1);
                    JSONArray contacts2 = new JSONArray(jsonStr2);
                    JSONArray contacts3 = new JSONArray(jsonStr3);
                    JSONArray contacts4 = new JSONArray(jsonStr4);
                    // looping through All Contacts

                    for (int i = 0; i < contacts1.length(); i++) {
                        JSONObject c1 = contacts1.getJSONObject(i);



                        String id1 = c1.getString("id");
                        String name1 = c1.getString("name");


                        getRelation.add(name1+id1);
                    }
                    for (int i = 0; i < contacts2.length(); i++) {
                        JSONObject c2 = contacts2.getJSONObject(i);



                        String id2 = c2.getString("id");
                        String name2 = c2.getString("name");


                        getEdu.add(name2+id2);
                    }
                    for (int i = 0; i < contacts3.length(); i++) {
                        JSONObject c3 = contacts3.getJSONObject(i);



                        String id3 = c3.getString("id");
                        String name3 = c3.getString("name");


                        getCastInCertificates.add(name3+id3);
                    }
                    for (int i = 0; i < contacts4.length(); i++) {
                        JSONObject c4 = contacts4.getJSONObject(i);



                        String id4 = c4.getString("id");
                        String name4 = c4.getString("name");


                        getEmployment.add(name4+id4);
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
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getRelation);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getEdu);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getCastInCertificates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter3 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getEmployment);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner1.setAdapter(adapter1);
            spinner2.setAdapter(adapter2);
            spinner3.setAdapter(adapter3);

        }

    }
}