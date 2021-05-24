package com.siddhi.softwares.sutsamaj.Forms;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;
import com.siddhi.softwares.sutsamaj.Handler.ImageSheet;
import com.siddhi.softwares.sutsamaj.MainActivity;
import com.siddhi.softwares.sutsamaj.R;
import com.siddhi.softwares.sutsamaj.ViewDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SurveyForm extends AppCompatActivity {
    ImageView img;
    String encodedImage;
    Button IMAGE;
    byte[] byteArray;

    String[] categorydrop = {"MALE", "FEMALE"};
    Spinner spinner, spinner1, spinner2, spinner3, spinner4, spinner5;
    private ImageButton completeButton, Home;

    private static final String TAG = SurveyForm.class.getSimpleName();
    private static String url = "http://sutsarthisamajcg.in/survey/api/getCasts";
    private static String url1 = "http://sutsarthisamajcg.in/survey/api/getGotras";
    private static String url2 = "http://sutsarthisamajcg.in/survey/api/getEducation";
    private static String url3 = "http://sutsarthisamajcg.in/survey/api/getCastInCertificates";
    private static String url4 = "http://sutsarthisamajcg.in/survey/api/getEmployment";

    //For storing the name
    ArrayList<String> casteList;
    ArrayList<String> gotraList;
    ArrayList<String> qualificationList;
    ArrayList<String> cicList;
    ArrayList<String> employmentList;

    //For storing id's
    ArrayList<String> casteIdList;
    ArrayList<String> gotraIdList;
    ArrayList<String> qualificationIdList;
    ArrayList<String> cicIdList;
    ArrayList<String> employmentIdList;

    private ProgressDialog pDialog;


    private TextInputLayout EmployeeDetailLayout;

    private TextInputEditText familyHead, F_HName, Age, Number, EmployeeDetail;
    private DBManager dbManager;
    Button Next;
    SwipeRefreshLayout SurveyRefresh;
    String casteId, gotraId, qualificationId, cicId, employmentId;

    private Button Recheck;

    private Boolean profileSet = false;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST =1 ;
    private Bitmap bitmap;
    private String filePath;
    private static final int PICK_IMAGE_REQUEST1 =7 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        init();

        dbManager = new DBManager(this);
        dbManager.open();


        //init for name
        casteList = new ArrayList<>();
        gotraList = new ArrayList<>();
        qualificationList= new ArrayList<>();
        cicList= new ArrayList<>();
        employmentList= new ArrayList<>();

        //init for id
        casteIdList = new ArrayList<>();
        gotraIdList = new ArrayList<>();
        qualificationIdList= new ArrayList<>();
        cicIdList= new ArrayList<>();
        employmentIdList= new ArrayList<>();


        new GetContacts().execute();

        if (ContextCompat.checkSelfPermission(SurveyForm.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SurveyForm.this, new String[]{Manifest.permission.CAMERA}, 100);
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categorydrop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                casteId = casteIdList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gotraId = gotraIdList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qualificationId = qualificationIdList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cicId = cicIdList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner5.setOnItemSelectedListener(new MySpinnerSelectedListener1());
                employmentId = employmentIdList.get(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        completeButton.setOnClickListener(v-> {
            InsertData();

        });
        IMAGE.setOnClickListener(v-> {

            if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                if ((ActivityCompat.shouldShowRequestPermissionRationale(SurveyForm.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(SurveyForm.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE))) {

                } else {
                    ActivityCompat.requestPermissions(SurveyForm.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSIONS);
                }
            } else {
                Log.e("Else", "Else");
                Cick();
            }
        });
        Next.setOnClickListener(v-> {
            InsertData();
        });
        Home.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });
        SurveyRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                        SurveyRefresh.setRefreshing(false);
                    }
                }
        );
        Recheck.setOnClickListener(v->{
            Preview();
        });
    }

    private void InsertData() {
        //Spinner data
        String catagory= spinner.getSelectedItem().toString();
        //Editext data
        String nameMembers = familyHead.getText().toString();
        String fatherNames = F_HName.getText().toString();
        String age = Age.getText().toString();
        String numbers = Number.getText().toString();

        if(TextUtils.isEmpty(nameMembers)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

            familyHead.requestFocus();
        }else if (TextUtils.isEmpty(fatherNames)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

            F_HName.requestFocus();
        }else if (TextUtils.isEmpty(age)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

        }
        else if (TextUtils.isEmpty(numbers)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

        }
        else {

            dbManager.G_insert(catagory, nameMembers, fatherNames, age, numbers, casteId, gotraId, cicId,employmentId, qualificationId, bitmap);
            startActivity(new Intent(this, AddressActivity.class));
        }
    }

    public class MySpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            //String selected = parent.getItemAtPosition(pos).toString();

            employmentId = employmentIdList.get(pos);
            CheckEmployee(employmentId);

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
    public void CheckEmployee(String employee){
        if (employee.equals("6")){
            EmployeeDetailLayout.setVisibility(View.VISIBLE);
        }else {
            EmployeeDetailLayout.setVisibility(View.GONE);
        }
    }
    void init(){
        //Spinner Bindings
        spinner= findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);
        spinner2= findViewById(R.id.spinner2);
        spinner3= findViewById(R.id.spinner3);
        spinner4= findViewById(R.id.spinner4);
        spinner5= findViewById(R.id.spinner5);
        //Button Bindings
        completeButton= findViewById(R.id.completeButton);
        Home= findViewById(R.id.exitFormImageView);

        IMAGE= findViewById(R.id.image);
        img= findViewById(R.id.imageView);

        EmployeeDetailLayout= findViewById(R.id.employeDetailsLayout);
        EmployeeDetail= findViewById(R.id.employeDetails);
        //TextInputEditText Bindings
        familyHead = findViewById(R.id.nameMember);
        F_HName= findViewById(R.id.fatherName);
        Age= findViewById(R.id.age);
        Number= findViewById(R.id.number);

        Next=findViewById(R.id.nextAddress);
        SurveyRefresh= findViewById(R.id.swipeRefresh);

        Recheck= findViewById(R.id.preview);
    }

    public void Preview(){
        //Spinner data
        String catagory= spinner.getSelectedItem().toString();
        String cast= spinner1.getSelectedItem().toString();
        String gotra= spinner2.getSelectedItem().toString();
        String education= spinner3.getSelectedItem().toString();
        String castin= spinner4.getSelectedItem().toString();
        String employement= spinner5.getSelectedItem().toString();
        //Editext data
        String nameMembers = familyHead.getText().toString();
        String fatherNames = F_HName.getText().toString();
        String age = Age.getText().toString();
        String numbers = Number.getText().toString();

        Log.d("XpreviewX", casteId+gotraId+qualificationId+cicId+employmentId);
        if(TextUtils.isEmpty(nameMembers)) {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

            familyHead.requestFocus();
        }else if (TextUtils.isEmpty(fatherNames)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

            F_HName.requestFocus();
        }else if (TextUtils.isEmpty(age)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

        }
        else if (TextUtils.isEmpty(numbers)){
            ViewDialog alert = new ViewDialog();
            alert.showDialog(SurveyForm.this, "Please fill in a valid value for all the required fields.");

        }
        else {

            Intent i = new Intent(getApplicationContext(), PreviewForm.class);
            i.putExtra("setCatagory",catagory);
            i.putExtra("setCast",cast);
            i.putExtra("setGotra",gotra);
            i.putExtra("setEducation",education);
            i.putExtra("setCastin",castin);
            i.putExtra("setEmployement",employement);

            i.putExtra("setName", nameMembers);
            i.putExtra("setFather", fatherNames);
            i.putExtra("setAge", age);
            i.putExtra("setNumber", numbers);

            startActivity(i);
        }

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            Toast.makeText(SurveyForm.this, selectedImage.toString(),
                    Toast.LENGTH_LONG).show();
            //txtmsg.setText(selectedImage.toString());
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(
                        selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);

            } catch (FileNotFoundException e) {

                //txtmsg.setText(e.getMessage().toString());
            }
            if (originBitmap != null) {
                this.img.setImageBitmap(originBitmap);
                Bitmap bitmap = ((BitmapDrawable) this.img.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray = stream.toByteArray();

                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.d("XmytagX","------endocde = "+encodedImage);
                Toast.makeText(SurveyForm.this, "Conversion Done",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 7 && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            this.img.setImageBitmap(photo);
            Bitmap bitmap = ((BitmapDrawable) this.img.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();

            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.d("XotX","------endocde = "+encodedImage);
            Toast.makeText(SurveyForm.this, "Conversion Done", Toast.LENGTH_SHORT).show();
        } else {
            //txtmsg.setText("Please Re-Capture Image and Upload It Again!");
            Toast.makeText(this, "Please Re-Capture Image and Upload It Again!", Toast.LENGTH_SHORT).show();
        }
    }*/

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SurveyForm.this);
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
            String jsonStr2 = sh.makeServiceCall(url2);
            String jsonStr3 = sh.makeServiceCall(url3);
            String jsonStr4 = sh.makeServiceCall(url4);

            Log.e(TAG, "Response from url: " + jsonStr + jsonStr1 + jsonStr2 + jsonStr3 + jsonStr4);

            if (jsonStr != null && jsonStr1 !=null && jsonStr2 !=null && jsonStr3 !=null && jsonStr4 !=null) {
                try {

                    JSONArray contacts = new JSONArray(jsonStr);
                    JSONArray contacts1 = new JSONArray(jsonStr1);
                    JSONArray contacts2 = new JSONArray(jsonStr2);
                    JSONArray contacts3 = new JSONArray(jsonStr3);
                    JSONArray contacts4 = new JSONArray(jsonStr4);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");


                        // adding contact to contact list
                        casteList.add(name);
                        casteIdList.add(id);
                    }
                    for (int i = 0; i < contacts1.length(); i++) {
                        JSONObject c1 = contacts1.getJSONObject(i);



                        String id1 = c1.getString("id");
                        String name1 = c1.getString("name");


                        gotraList.add(name1);
                        gotraIdList.add(id1);
                    }
                    for (int i = 0; i < contacts2.length(); i++) {
                        JSONObject c2 = contacts2.getJSONObject(i);



                        String id2 = c2.getString("id");
                        String name2 = c2.getString("name");

                        qualificationList.add(name2);
                        qualificationIdList.add(id2);
                    }
                    for (int i = 0; i < contacts3.length(); i++) {
                        JSONObject c3 = contacts3.getJSONObject(i);


                        String id3 = c3.getString("id");
                        String name3 = c3.getString("name");


                        cicList.add(name3);
                        cicIdList.add(id3);
                    }
                    for (int i = 0; i < contacts4.length(); i++) {
                        JSONObject c4 = contacts4.getJSONObject(i);

                        String id4 = c4.getString("id");
                        String name4 = c4.getString("name");


                        employmentList.add(name4);
                        employmentIdList.add(id4);
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
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, casteList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, gotraList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, qualificationList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter3 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cicList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ArrayAdapter adapter4 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, employmentList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner1.setAdapter(adapter);
            spinner2.setAdapter(adapter1);
            spinner3.setAdapter(adapter2);
            spinner4.setAdapter(adapter3);
            spinner5.setAdapter(adapter4);
        }

    }

    public void Cick() {
        ImageSheet dialog = new ImageSheet(profileSet);
        dialog.showNow(getSupportFragmentManager(), ImageSheet.class.getSimpleName());
        dialog.setDialogClickListener(new ImageSheet.DialogClickListener() {
            @Override
            public void onDialogGalleryClick() {
                showFileChooser();

            }

            @Override
            public void onDialogCameraClick() {
                showCameraChooser();

            }

            @Override
            public void onDialogRemoveClick() {
                //viewModel.removeProfilePicture(requireActivity().getApplicationContext());
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void showCameraChooser() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 7);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();
            filePath = getPath(picUri);
            if (filePath != null) {
                try {

                    //textView.setText("File Selected");
                    Log.d("filePath", String.valueOf(filePath));
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                    //uploadBitmap(bitmap);
                    img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
            {
                Toast.makeText(
                        SurveyForm.this,"no image selected",
                        Toast.LENGTH_LONG).show();
            }
        }if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            Toast.makeText(SurveyForm.this, selectedImage.toString(),
                    Toast.LENGTH_LONG).show();
            //txtmsg.setText(selectedImage.toString());
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(
                        selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);

            } catch (FileNotFoundException e) {

                //txtmsg.setText(e.getMessage().toString());
            }
            if (originBitmap != null) {
                this.img.setImageBitmap(originBitmap);
                Bitmap bitmap = ((BitmapDrawable) this.img.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray = stream.toByteArray();

                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.d("XmytagX","------endocde = "+encodedImage);
                Toast.makeText(SurveyForm.this, "Conversion Done",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 7 && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            this.img.setImageBitmap(photo);
            Bitmap bitmap = ((BitmapDrawable) this.img.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();

            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.d("XotX","------endocde = "+encodedImage);
            Toast.makeText(SurveyForm.this, "Conversion Done", Toast.LENGTH_SHORT).show();
        } else {
            //txtmsg.setText("Please Re-Capture Image and Upload It Again!");
            Toast.makeText(this, "Please Re-Capture Image and Upload It Again!", Toast.LENGTH_SHORT).show();
        }

    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}