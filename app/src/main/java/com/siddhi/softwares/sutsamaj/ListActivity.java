package com.siddhi.softwares.sutsamaj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.DB.DatabaseHelper;

public class ListActivity extends AppCompatActivity {
    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.GENDER,
    DatabaseHelper.AGE, DatabaseHelper.EDUCATION, DatabaseHelper.CAST, DatabaseHelper.EMPLOYMENT};

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc, R.id.desc1};

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        addButton= findViewById(R.id.addmore);

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record_member, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        addButton.setOnClickListener(v->{
            Intent add_mem = new Intent(this, DemoAddMmbr.class);
            startActivity(add_mem);
        });
    }
}