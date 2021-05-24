package com.siddhi.softwares.sutsamaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.siddhi.softwares.sutsamaj.Adapter.Adapter;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.DB.DatabaseHelper;
import com.siddhi.softwares.sutsamaj.Forms.MembersActivity;
import com.siddhi.softwares.sutsamaj.Forms.SurveyForm;
import com.siddhi.softwares.sutsamaj.Model.Survey;

import java.util.ArrayList;

import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME1;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME2;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME3;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME4;


public class MainActivity extends AppCompatActivity {
    private ImageView ProfileSection;

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter Sadapter;

    final String[] from = new String[] { DatabaseHelper.G_NAME,
            DatabaseHelper.G_CATAGORY, DatabaseHelper.G_ID,
            DatabaseHelper.G_AGE, DatabaseHelper.G_EMPLOYMENT, DatabaseHelper.G_CAST, DatabaseHelper.G_MOBILE};

    final int[] to = new int[] { R.id.formTitleTextView, R.id.formCategoryTextView, R.id.imageView};
    SwipeRefreshLayout MainRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCities();

        MainRefresh= findViewById(R.id.swipeRefresh);
        ArrayList<Survey> cities = initCities();

        RecyclerView recyclerView = findViewById(R.id.formRecyclerView);
        ProfileSection= findViewById(R.id.exitAppImageView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter adapter = new Adapter(cities);
        recyclerView.setAdapter(adapter);

        ProfileSection.setOnClickListener(v-> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        MainRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        RefreshMain();
                    }
                }
        );
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch1();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        Sadapter = new SimpleCursorAdapter(this, R.layout.list_row_item, cursor, from, to, 0);
        Sadapter.notifyDataSetChanged();

        listView.setAdapter(Sadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage(R.string.text7)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteId();
                                RefreshMain();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void RefreshMain() {
        finish();
        startActivity(getIntent());
        MainRefresh.setRefreshing(false);
    }

    public void deleteId(){
        SQLiteDatabase sq=openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        sq.execSQL("delete from "+ TABLE_NAME);
        sq.execSQL("delete from "+ TABLE_NAME1);
        sq.execSQL("delete from "+ TABLE_NAME2);
        sq.execSQL("delete from "+ TABLE_NAME3);
        sq.execSQL("delete from "+ TABLE_NAME4);

    }
    private ArrayList<Survey> initCities() {
        ArrayList<Survey> list = new ArrayList<>();

        list.add(new Survey("Registration", "For registring new member's in your survey"));

        return list;
    }

}