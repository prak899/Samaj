package com.siddhi.softwares.sutsamaj;
public class OldCode {
/*
    package com.siddhi.softwares.sutsamaj.Forms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.siddhi.softwares.sutsamaj.CustomBottomSheetDialogFragment;
import com.siddhi.softwares.sutsamaj.DB.DBManager;
import com.siddhi.softwares.sutsamaj.DB.DatabaseHelper;
import com.siddhi.softwares.sutsamaj.DemoAddMmbr;
import com.siddhi.softwares.sutsamaj.Handler.HttpHandler;
import com.siddhi.softwares.sutsamaj.Handler.VollyMultipartRequest;
import com.siddhi.softwares.sutsamaj.ListActivity;
import com.siddhi.softwares.sutsamaj.MainActivity;
import com.siddhi.softwares.sutsamaj.Model.MembersListModel;
import com.siddhi.softwares.sutsamaj.R;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME1;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME2;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME3;
import static com.siddhi.softwares.sutsamaj.DB.DatabaseHelper.TABLE_NAME4;

    public class MembersActivity extends AppCompatActivity {
        Button RegisterButton, FinalSubmit;
        LinearLayout linearLayout;
        ImageButton imageButton, StartRide;
        private TextInputEditText MemberName, MemberAge, JobDescription;

        private static final String TAG = SurveyForm.class.getSimpleName();

        private static String url1 = "http://sutsarthisamajcg.in/survey/api/getRelations";
        private static String url2 = "http://sutsarthisamajcg.in/survey/api/getEducation";
        private static String url3 = "http://sutsarthisamajcg.in/survey/api/getCastInCertificates";
        private static String url4 = "http://sutsarthisamajcg.in/survey/api/memberSave";

        private static String ROOT_URL = "http://sutsarthisamajcg.in/survey/api/getEmployment";
        DatabaseHelper dbhelperr;
        ArrayList<String> getRelation;
        ArrayList<String> getCastInCertificates;
        ArrayList<String> getEdu;
        ArrayList<String> getEmployment;

        //For id's
        ArrayList<String> getRelationId;
        ArrayList<String> getCastInCertificatesId;
        ArrayList<String> getEduId;
        ArrayList<String> getEmploymentId;


        ArrayList<MembersListModel> arraylistmembers = new ArrayList<>();
        private ProgressDialog pDialog;
        Spinner spinner, spinner1, spinner2, spinner3;
        TextInputLayout EmployeeDetailLayout;

        HttpPost httppost;

        HttpResponse response;
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        ProgressDialog dialog = null;

        private DBManager dbManager;

        private ListView listView, listView1;

        private SimpleCursorAdapter adapter, adapter1;

        final String[] from = new String[]{DatabaseHelper._ID,
                DatabaseHelper.NAME, DatabaseHelper.GENDER,
                DatabaseHelper.AGE, DatabaseHelper.CAST, DatabaseHelper.RELATION, DatabaseHelper.EMPLOYMENT};

        final int[] to = new int[]{R.id.id, R.id.title, R.id.desc, R.id.desc1};

        final String[] from1 = new String[]{DatabaseHelper.G_ID,
                DatabaseHelper.G_NAME, DatabaseHelper.G_CATAGORY,
                DatabaseHelper.G_AGE, DatabaseHelper.G_CAST, DatabaseHelper.G_MOBILE, DatabaseHelper.G_EMPLOYMENT};

        final int[] to1 = new int[]{R.id.id, R.id.title, R.id.desc, R.id.desc1};

        private Button ShowMember;

        private SwipeRefreshLayout MembersRefresh;
*/
/*
    protected String getName0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender0() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }


    protected String getName1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=2", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender1() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }


    protected String getName2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=3", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=3", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=3", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=3", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=3", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender2() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }


    protected String getName3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=4", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender3() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getName4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=5", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender4() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getName5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=6", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender5() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getName6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=7", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender6() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }


    protected String getName7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=8", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender7() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }


    protected String getName8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=9", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender8() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getName9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(1);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getAge9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(2);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getRelation9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(3);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEducation9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(4);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getCast9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(5);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getEmployment9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=10", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(6);

            } while (c1424.moveToNext());
        }
        return "";
    }

    protected String getGender9() {

        SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ where _id=1", null);

        if (c1424.moveToFirst()) {
            do {

                return c1424.getString(7);

            } while (c1424.moveToNext());
        }
        return "";
    }*//*



        //THIS IS FOR GENERAL SECTION DATA
        protected String getGCatagory() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(1);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGHeadName() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(2);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGFatherName() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(3);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGAge() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(4);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGMobile() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(5);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGCast() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(6);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGGotra() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(7);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGEducation() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(8);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGCastInCert() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(9);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGEmployment() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(10);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getGPhoto() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_GENERAL where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(11);

                } while (c1424.moveToNext());
            }
            return "";
        }

        String GCat, Ghead, Gfather, Gage, Gmobile, Gcast, Ggotra, GEducation, Gcastin, Gemplyement;

        //THIS IS FOR PERMANENT ADDRESS SECTION DATA
        protected String getPAddress() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(1);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getPPin() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(2);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getPState() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(3);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getPDistrict() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(4);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getPTehesil() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(5);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getPVillage() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_P_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(6);

                } while (c1424.moveToNext());
            }
            return "";
        }

        String PAddress, PPin, PState, PDistrict, PTehesil, PVillage;

        //THIS IS FOR TEMPRARY ADDRESS SECTION DATA
        protected String getTAddress() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(1);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getTPin() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(2);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getTState() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(3);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getTDistrict() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(4);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getTTehesil() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(5);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getTVillage() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_T_ADDRESS where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(6);

                } while (c1424.moveToNext());
            }
            return "";
        }

        String TAddress, TPin, TState, TDistrict, TTehesil, TVillage;

        //THIS IS FOR SPOUSE SECTION DATA
        protected String getSName() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(1);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSFathername() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(2);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSAddress() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(3);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSPin() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(4);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSGotra() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(5);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSState() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(6);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSDistrict() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(7);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSTehesil() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(8);

                } while (c1424.moveToNext());
            }
            return "";
        }

        protected String getSVillage() {

            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            @SuppressLint("Recycle") Cursor c1424 = sq.rawQuery("SELECT * FROM  SUTSAMAJ_SPOUSE where _id=1", null);

            if (c1424.moveToFirst()) {
                do {

                    return c1424.getString(9);

                } while (c1424.moveToNext());
            }
            return "";
        }

        String SName, SFathername, SAddress, SPin, SGotra, SState, SDistrict, STehesil, SVillage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_members);

            init();
            dbhelperr = new DatabaseHelper(this);
            dbManager = new DBManager(this);
            dbManager.open();
            Cursor cursor = dbManager.fetch();

            adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);

            dbManager = new DBManager(this);
            dbManager.open();
            Cursor cursor1 = dbManager.fetch1();

            adapter1 = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor1, from1, to1, 0);
            adapter1.notifyDataSetChanged();

            listView1.setAdapter(adapter1);


            //For general data
            GCat = getGCatagory();
            Ghead = getGHeadName();
            Gfather = getGFatherName();
            Gage = getGAge();
            Gmobile = getGMobile();
            Gcast = getGCast().replaceAll("[^0-9]", "");
            Ggotra = getGGotra().replaceAll("[^0-9]", "");
            GEducation = getGEducation().replaceAll("[^0-9]", "");
            Gcastin = getGCastInCert().replaceAll("[^0-9]", "");
            Gemplyement = getGEmployment().replaceAll("[^0-9]", "");


            //For permamnet address
            PAddress = getPAddress();
            PPin = getPPin();
            PState = getPState().replaceAll("[^0-9]", "");
            PDistrict = getPDistrict().replaceAll("[^0-9]", "");
            PTehesil = getPTehesil().replaceAll("[^0-9]", "");
            PVillage = getPVillage().replaceAll("[^0-9]", "");

            //Temprary address
            TAddress = getTAddress();
            TPin = getTPin();
            TState = getTState().replaceAll("[^0-9]", "");
            TDistrict = getTDistrict().replaceAll("[^0-9]", "");
            TTehesil = getTTehesil().replaceAll("[^0-9]", "");
            TVillage = getTVillage().replaceAll("[^0-9]", "");

            //For spouse activity
            SName = getSName();
            SFathername = getSFathername();
            SAddress = getSAddress();
            SPin = getSPin();
            SGotra = getSGotra().replaceAll("[^0-9]", "");
            SState = getSState().replaceAll("[^0-9]", "");
            SDistrict = getSDistrict().replaceAll("[^0-9]", "");
            STehesil = getSTehesil().replaceAll("[^0-9]", "");
            SVillage = getSVillage().replaceAll("[^0-9]", "");

            Log.d("XGeneralX", SVillage + "Sp-----" + TVillage + "TA----------" + PVillage + "PA--------");

            RegisterButton.setOnClickListener(v -> {
                new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");

                //ShowMember.setVisibility(View.VISIBLE);
            });

            StartRide.setOnClickListener(v -> {

                dialog = ProgressDialog.show(com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this, "",
                        "Uploading data...", true);
                new Thread(new Runnable() {
                    public void run() {
                        startingRide();

                    }
                }).start();
            });

            FinalSubmit.setOnClickListener(v -> {

                dialog = ProgressDialog.show(com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this, "",
                        "Uploading data...", true);
                new Thread(new Runnable() {
                    public void run() {
                        startingRide();
                    }
                }).start();
            });

            ShowMember.setOnClickListener(v -> {
                startActivity(new Intent(this, ListActivity.class));
            });

            MembersRefresh.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            finish();
                            startActivity(getIntent());
                            MembersRefresh.setRefreshing(false);
                        }
                    }
            );
        }

        public void CheckEmployee(String employee) {
            if (employee.equals("Govt Job1")) {
                EmployeeDetailLayout.setVisibility(View.VISIBLE);
            } else {
                EmployeeDetailLayout.setVisibility(View.GONE);
            }
        }

        private void init() {
            //Spinner Bindings
            spinner = findViewById(R.id.spinner);
            spinner1 = findViewById(R.id.spinner1);
            spinner2 = findViewById(R.id.spinner2);
            spinner3 = findViewById(R.id.spinner3);

            RegisterButton = findViewById(R.id.register);
            FinalSubmit = findViewById(R.id.register1);
            ShowMember = findViewById(R.id.register2);
            linearLayout = findViewById(R.id.bottom_sheet);
            imageButton = findViewById(R.id.completeButton);

            StartRide = findViewById(R.id.uploadDB);

            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

            //TextInputEditText Bindings
            MemberName = findViewById(R.id.nameMember);
            MemberAge = findViewById(R.id.ageMember);
            JobDescription = findViewById(R.id.employeDetails);

            EmployeeDetailLayout = findViewById(R.id.employeDetailsLayout);

            listView = (ListView) findViewById(R.id.list_view);
            listView1 = findViewById(R.id.list_view1);
            listView.setEmptyView(findViewById(R.id.empty));

            MembersRefresh = findViewById(R.id.swipeRefresh);
        }

        public void complete(View v) {
        */
/*Intent add_mem = new Intent(this, DemoAddMmbr.class);
        startActivity(add_mem);*//*

            new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
        }

        private class GetContacts extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this);
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


                if (jsonStr1 != null && jsonStr2 != null && jsonStr3 != null && jsonStr4 != null) {
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


        void startingRide(){
            try{
                httpclient=new DefaultHttpClient();
                httppost= new HttpPost("http://sutsarthisamajcg.in/survey/api/memberSave"); // make sure the url is correct.
                //add your data
                nameValuePairs = new ArrayList<NameValuePair>(1);


                //General Sections
                nameValuePairs.add(new BasicNameValuePair("hof", GCat));
                nameValuePairs.add(new BasicNameValuePair("name", Ghead));
                nameValuePairs.add(new BasicNameValuePair("father_name", Gfather));
                nameValuePairs.add(new BasicNameValuePair("age", Gage));
                nameValuePairs.add(new BasicNameValuePair("cast", Gcast));
                nameValuePairs.add(new BasicNameValuePair("cast_certificate", Gcastin));
                nameValuePairs.add(new BasicNameValuePair("mobile", Gmobile));
                nameValuePairs.add(new BasicNameValuePair("gotra", Ggotra));
                nameValuePairs.add(new BasicNameValuePair("education", GEducation));
                nameValuePairs.add(new BasicNameValuePair("employment", Gemplyement));
                nameValuePairs.add(new BasicNameValuePair("any_other", ""));
                nameValuePairs.add(new BasicNameValuePair("photo", getGPhoto()));


                //Temporary Address Section
                nameValuePairs.add(new BasicNameValuePair("address", PAddress));
                nameValuePairs.add(new BasicNameValuePair("state", PState));
                nameValuePairs.add(new BasicNameValuePair("district", PDistrict));
                nameValuePairs.add(new BasicNameValuePair("tehsil", PTehesil));
                nameValuePairs.add(new BasicNameValuePair("village", PVillage));
                nameValuePairs.add(new BasicNameValuePair("pin", PPin));

                //Permanent Address Section
                nameValuePairs.add(new BasicNameValuePair("p_address", TAddress));
                nameValuePairs.add(new BasicNameValuePair("p_state", TState));
                nameValuePairs.add(new BasicNameValuePair("p_district", TDistrict));
                nameValuePairs.add(new BasicNameValuePair("p_tehsil", TTehesil));
                nameValuePairs.add(new BasicNameValuePair("p_village", TVillage));
                nameValuePairs.add(new BasicNameValuePair("p_pin", TPin));

                //Spouse(Wife) Section
                nameValuePairs.add(new BasicNameValuePair("w_name", SName));
                nameValuePairs.add(new BasicNameValuePair("w_father_name", SFathername));
                nameValuePairs.add(new BasicNameValuePair("w_gotra", SGotra));
                nameValuePairs.add(new BasicNameValuePair("w_address", SAddress));
                nameValuePairs.add(new BasicNameValuePair("w_state", SState));
                nameValuePairs.add(new BasicNameValuePair("w_district", SDistrict));
                nameValuePairs.add(new BasicNameValuePair("w_tehsil", STehesil));
                nameValuePairs.add(new BasicNameValuePair("w_village", SVillage));
                nameValuePairs.add(new BasicNameValuePair("w_pin", SPin));


                arraylistmembers = dbhelperr.getmembers();
                for (int i=0;i<arraylistmembers.size();i++)
                {
                    Log.d("MyTag","mmmmm name :- "+arraylistmembers.get(i).getName());
                    Log.d("MyTag","mmmmm relation :- "+arraylistmembers.get(i).getRelation());
                    Log.d("MyTag","mmmmm age :- "+arraylistmembers.get(i).getAge());
                    Log.d("MyTag","mmmmm cast :- "+arraylistmembers.get(i).getCast_certificate());
                    Log.d("MyTag","mmmmm education :- "+arraylistmembers.get(i).getEducation());
                    Log.d("MyTag","mmmmm employement :- "+arraylistmembers.get(i).getEmployment());
                    Log.d("MyTag","mmmmm gender :- "+arraylistmembers.get(i).getGender());

                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][name]",arraylistmembers.get(i).getName()));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][relation]",arraylistmembers.get(i).getRelation()));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][age]",String.valueOf(arraylistmembers.get(i).getAge())));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][cast_certificate]",String.valueOf(arraylistmembers.get(i).getCast_certificate())));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][education]",String.valueOf(arraylistmembers.get(i).getEducation())));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][employment]",String.valueOf(arraylistmembers.get(i).getEmployment())));
                    nameValuePairs.add(new BasicNameValuePair("children["+i+"][gender]",arraylistmembers.get(i).getGender()));

                }
                //Family Member Section Start from children[0] to children[9] for 10 family members
            */
/*nameValuePairs.add(new BasicNameValuePair("children[0][name]", getName0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][relation]", getRelation0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][age]", getAge0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][cast_certificate]", getCast0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][education]", getEducation0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][employment] ", getEmployment0()));
            nameValuePairs.add(new BasicNameValuePair("children[0][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[0][gender] ", getGender0()));

            //Family Member Section Start from children[1] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[1][name]", getName1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][relation]", getRelation1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][age]", getAge1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][cast_certificate]", getCast1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][education]", getEducation1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][employment] ", getEmployment1()));
            nameValuePairs.add(new BasicNameValuePair("children[1][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[1][gender] ", getGender1()));

            //Family Member Section Start from children[2] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[2][name]", getName2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][relation]", getRelation2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][age]", getAge2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][cast_certificate]", getCast2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][education]", getEducation2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][employment] ", getEmployment2()));
            nameValuePairs.add(new BasicNameValuePair("children[2][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[2][gender] ", getGender2()));

            //Family Member Section Start from children[3] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[3][name]", getName3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][relation]", getRelation3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][age]", getAge3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][cast_certificate]", getCast3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][education]", getEducation3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][employment] ", getEmployment3()));
            nameValuePairs.add(new BasicNameValuePair("children[3][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[3][gender] ", getGender3()));

            //Family Member Section Start from children[4] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[4][name]", getName4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][relation]", getRelation4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][age]", getAge4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][cast_certificate]", getCast4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][education]", getEducation4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][employment] ", getEmployment4()));
            nameValuePairs.add(new BasicNameValuePair("children[4][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[4][gender] ", getGender4()));

            //Family Member Section Start from children[5] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[5][name]", getName5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][relation]", getRelation5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][age]", getAge5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][cast_certificate]", getCast5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][education]", getEducation5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][employment] ", getEmployment5()));
            nameValuePairs.add(new BasicNameValuePair("children[5][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[5][gender] ", getGender5()));

            //Family Member Section Start from children[6] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[6][name]", getName6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][relation]", getRelation6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][age]", getAge6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][cast_certificate]", getCast6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][education]", getEducation6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][employment] ", getEmployment6()));
            nameValuePairs.add(new BasicNameValuePair("children[6][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[6][gender] ", getGender6()));

            //Family Member Section Start from children[7] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[7][name]", getName7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][relation]", getRelation7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][age]", getAge7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][cast_certificate]", getCast7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][education]", getEducation7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][employment] ", getEmployment7()));
            nameValuePairs.add(new BasicNameValuePair("children[7][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[7][gender] ", getGender7()));

            //Family Member Section Start from children[8] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[8][name]", getName8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][relation]", getRelation8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][age]", getAge8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][cast_certificate]", getCast8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][education]", getEducation8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][employment] ", getEmployment8()));
            nameValuePairs.add(new BasicNameValuePair("children[8][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[8][gender] ", getGender8()));

            //Family Member Section Start from children[9] to children[9] for 10 family members
            nameValuePairs.add(new BasicNameValuePair("children[9][name]", getName9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][relation]", getRelation9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][age]", getAge9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][cast_certificate]", getCast9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][education]", getEducation9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][employment] ", getEmployment9()));
            nameValuePairs.add(new BasicNameValuePair("children[9][employment_desc] ", ""));
            nameValuePairs.add(new BasicNameValuePair("children[9][gender] ", getGender9()));*//*



                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                //response=httpclient.execute(httppost);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);

                Log.i("UserLoginResponse" , String.valueOf(nameValuePairs));
                Log.i("XUserLoginResponseX" , String.valueOf(response));
                runOnUiThread(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                });


                if(response.equalsIgnoreCase("{\"status\":\"Success\",\"msg\":\"Form Saved Successfully\"}")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    deleteId();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this);

                                    builder.setMessage(R.string.text5)
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    Snackbar.make(findViewById(R.id.memberLay), "Succesfully Uploaded data", Snackbar.LENGTH_LONG).show();
                                                    startActivity(new Intent(com.siddhi.softwares.sutsamaj.Forms.MembersActivity.this, MainActivity.class));
                                                    finish();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            });
                        }
                    });

                }else{
                    Snackbar.make(findViewById(R.id.memberLay), "Server not respnding", Snackbar.LENGTH_LONG).show();

                }

            }catch(Exception e){
                dialog.dismiss();
                Log.i("Exce" ,"ex"+e.getMessage());
            }
        }
        public void deleteId() {
            SQLiteDatabase sq = openOrCreateDatabase("SUT_SAMAJ_SURVEY.DB", MODE_PRIVATE, null);
            sq.execSQL("delete from " + TABLE_NAME);
            sq.execSQL("delete from " + TABLE_NAME1);
            sq.execSQL("delete from " + TABLE_NAME2);
            sq.execSQL("delete from " + TABLE_NAME3);
            sq.execSQL("delete from " + TABLE_NAME4);

        }

        public byte[] getFileDataFromDrawable(Bitmap bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

    */
/*private void uploadBitmap() {

        VollyMultipartRequest volleyMultipartRequest = new VollyMultipartRequest(Request.Method.POST, ROOT_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError", "" + error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String er = "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";
                Map<String, String> params = new HashMap<>();
                params.put("address", Location.getText().toString());
                params.put("landmark", Landmark.getText().toString());
                params.put("type", Spinnertype);
                params.put("description", Description.getText().toString());
                params.put("number", numberShared);
                params.put("photo", er);


                //SuccessInflatMethod();
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //params.put("photo", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));

                //SuccessInflatMethod();
                return params;
            }

        };
    }*//*

    }

*/

}
