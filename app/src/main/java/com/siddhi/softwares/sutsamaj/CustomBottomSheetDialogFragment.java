package com.siddhi.softwares.sutsamaj;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.Forms.MembersActivity;
import com.siddhi.softwares.sutsamaj.Forms.SurveyForm;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private EditText Name, Age, JobDescription;
    ImageButton cameraButton;
    Button backButton, submitButton, addButton;

    private static final String TAG = SurveyForm.class.getSimpleName();

    private static String url1 = "http://sutsarthisamajcg.in/survey/api/getRelations";
    private static String url2 = "http://sutsarthisamajcg.in/survey/api/getEducation";
    private static String url3 = "http://sutsarthisamajcg.in/survey/api/getCastInCertificates";
    private static String url4 = "http://sutsarthisamajcg.in/survey/api/getEmployment";
    String[] categorydrop = {"MALE", "FEMALE"};


    ArrayList<String> getRelation;
    ArrayList<String> getCastInCertificates;
    ArrayList<String> getEdu;
    ArrayList<String> getEmployment;

    ArrayList<String> getRelationId;
    ArrayList<String> getCastInCertificatesId;
    ArrayList<String> getEduId;
    ArrayList<String> getEmploymentId;

    private ProgressDialog pDialog;
    Spinner spinner, spinner1, spinner2, spinner3, spinner0;


    String relationId, cicId, eduId, employmentId;

    private DBManager dbManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container,false);

        init(view);

        getRelation = new ArrayList<>();
        getEdu= new ArrayList<>();
        getCastInCertificates= new ArrayList<>();
        getEmployment= new ArrayList<>();

        getRelationId = new ArrayList<>();
        getEduId= new ArrayList<>();
        getCastInCertificatesId= new ArrayList<>();
        getEmploymentId= new ArrayList<>();

        new GetContacts().execute();

        dbManager = new DBManager(getContext());
        dbManager.open();

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, categorydrop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner0.setAdapter(adapter);
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected= spinner0.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d("XaX", spinner0.getSelectedItem().toString());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                relationId= getRelationId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                eduId= getEduId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                employmentId= getEmploymentId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner3.setOnItemSelectedListener(new CustomBottomSheetDialogFragment.MySpinnerSelectedListener1());


                cicId= getCastInCertificatesId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cameraButton.setOnClickListener(v-> {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 7);
        });

        backButton.setOnClickListener(v-> {
            Toast.makeText(getContext(), "Back pressed", Toast.LENGTH_SHORT).show();
        });
        submitButton.setOnClickListener(v-> {

            String MmbrName = Name.getText().toString();
            String MmbrAge = Age.getText().toString();

            if(TextUtils.isEmpty(MmbrName)) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "Please fill in a valid value for all the required fields.");

                Name.requestFocus();
            }else if (TextUtils.isEmpty(MmbrAge)){
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "Please fill in a valid value for all the required fields.");

                Age.requestFocus();
            }else {

                String MmbrGender= spinner0.getSelectedItem().toString();


                Log.d("memeberXXXX", MmbrName+ MmbrAge+ relationId+ cicId+ eduId+ employmentId+ MmbrGender);
                dbManager.insert(MmbrName, MmbrAge, relationId, cicId, eduId, employmentId, MmbrGender);

                Intent main = new Intent(getContext(), MembersActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });
        addButton.setOnClickListener(v-> {
            Toast.makeText(getContext(), "Add pressed", Toast.LENGTH_SHORT).show();
        });


        return view;
    }

    private void init(View view) {

        spinner= view.findViewById(R.id.spinner);
        spinner1= view.findViewById(R.id.spinner1);
        spinner2= view.findViewById(R.id.spinner2);
        spinner3= view.findViewById(R.id.spinner3);
        spinner0= view.findViewById(R.id.spinner0);


        cameraButton= (ImageButton) view.findViewById(R.id.cameraButton);

        backButton= (Button) view.findViewById(R.id.back);
        submitButton= (Button) view.findViewById(R.id.submit);
        addButton= (Button) view.findViewById(R.id.add);

        Name= (EditText) view.findViewById(R.id.nameMember);
        Age= (EditText) view.findViewById(R.id.ageMember);
        JobDescription= (EditText) view.findViewById(R.id.jobDesc);
    }
    public void CheckEmployee(String employee){
        if (employee.equals("6") || employee.equals("7")){
            JobDescription.setVisibility(View.VISIBLE);
        }else {
            JobDescription.setVisibility(View.GONE);
        }
    }

    public class MySpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String selected = parent.getItemAtPosition(pos).toString();

            employmentId = getEduId.get(pos);
            CheckEmployee(employmentId);
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
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

                        getRelation.add(name1);
                        getRelationId.add(id1);
                    }
                    for (int i = 0; i < contacts2.length(); i++) {
                        JSONObject c2 = contacts2.getJSONObject(i);



                        String id2 = c2.getString("id");
                        String name2 = c2.getString("name");


                        getEdu.add(name2);
                        getEduId.add(id2);
                    }
                    for (int i = 0; i < contacts3.length(); i++) {
                        JSONObject c3 = contacts3.getJSONObject(i);



                        String id3 = c3.getString("id");
                        String name3 = c3.getString("name");


                        getCastInCertificates.add(name3);
                        getCastInCertificatesId.add(id3);
                    }
                    for (int i = 0; i < contacts4.length(); i++) {
                        JSONObject c4 = contacts4.getJSONObject(i);



                        String id4 = c4.getString("id");
                        String name4 = c4.getString("name");


                        getEmployment.add(name4);
                        getEmploymentId.add(id4);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, getRelation);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, getEdu);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, getCastInCertificates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, getEmployment);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner1.setAdapter(adapter1);
            spinner2.setAdapter(adapter2);
            spinner3.setAdapter(adapter3);
        }

    }
}