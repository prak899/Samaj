package com.siddhi.softwares.sutsamaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.siddhi.softwares.sutsamaj.Adapter.Adapter;
import com.siddhi.softwares.sutsamaj.Model.Survey;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*initCities();


        ArrayList<Survey> cities = initCities();

        RecyclerView recyclerView = findViewById(R.id.formRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter adapter = new Adapter(cities);
        recyclerView.setAdapter(adapter);*/
    }

    /*private ArrayList<Survey> initCities() {
        ArrayList<Survey> list = new ArrayList<>();

        list.add(new Survey("Registration", "For registring new member's in your survey"));
        list.add(new Survey("Report", "Report are here"));

        return list;
    }*/
}