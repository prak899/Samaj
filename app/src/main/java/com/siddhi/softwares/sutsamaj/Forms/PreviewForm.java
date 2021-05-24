package com.siddhi.softwares.sutsamaj.Forms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.siddhi.softwares.sutsamaj.R;

public class PreviewForm extends AppCompatActivity {
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_form);

        init();


        String catagory= getIntent().getStringExtra("setCatagory").replaceAll("[^A-Z]", "");
        String cast= getIntent().getStringExtra("setCast").replaceAll("[^A-Z]", "");
        String gotra= getIntent().getStringExtra("setGotra").replaceAll("[^A-Z]", "");
        String education= getIntent().getStringExtra("setEducation").replaceAll("[^A-Z]", "");
        String castin= getIntent().getStringExtra("setCastin").replaceAll("[^A-Z]", "");
        String employement= getIntent().getStringExtra("setEmployement").replaceAll("[^A-Z]", "");

        String headName= getIntent().getStringExtra("setName");
        String fatherName= getIntent().getStringExtra("setFather");
        String age= getIntent().getStringExtra("setAge");
        String number= getIntent().getStringExtra("setNumber");



        textView1.setText(catagory);
        textView2.setText(cast);
        textView3.setText(gotra);
        textView4.setText(education);
        textView5.setText(castin);
        textView6.setText(employement);
        textView7.setText(headName);
        textView8.setText(fatherName);
        textView9.setText(age);
        textView10.setText(number);

    }

    private void init() {

        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        textView3=findViewById(R.id.text3);
        textView4=findViewById(R.id.text4);
        textView5=findViewById(R.id.text5);
        textView6=findViewById(R.id.text6);
        textView7=findViewById(R.id.text7);
        textView8=findViewById(R.id.text8);
        textView9=findViewById(R.id.text9);
        textView10=findViewById(R.id.text10);
    }
}