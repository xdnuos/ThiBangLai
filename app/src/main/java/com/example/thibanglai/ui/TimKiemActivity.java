package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.isChangeEdtInAdapter;
import static com.example.thibanglai.setting.MyApplication.nameDB;
import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.HashTagAdapter;
import com.example.thibanglai.adapter.LawSearchedAdapter;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.model.BienBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    public static EditText edtSearch;
    public static RecyclerView rvHashtag,rvLawSearched;
    public static LawSearchedAdapter lawSearchedAdapter;
    HashTagAdapter hashTagAdapter;
    List<String> listHashtag;
    LinearLayoutManager linearLayoutManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isFirstRun;
    Database database;
    Handler handler;
    Runnable runnable;
    public static List<BienBao> BienBaoSearch;
    Cursor cursor;
    String stringSearched="";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tim_kiem);
        database = new Database(this,nameDB,null,1);
        sharedPreferences = getSharedPreferences(nameSharedPreference,MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        if(isFirstRun){
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun",false);
            editor.apply();
            database.FirstRun();

        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                BienBaoSearch.clear();
                cursor = database.getData("SELECT * FROM bienBao WHERE tenBienBao LIKE '%"+stringSearched+"%'"+" OR noiDung LIKE '%"+stringSearched+"%'");
                while (cursor.moveToNext()){
                    BienBaoSearch.add(new BienBao(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
                }
                if(stringSearched.length()==0) {
                    BienBaoSearch.clear();
                    rvHashtag.setVisibility(View.VISIBLE);
                }
                if(lawSearchedAdapter!=null) lawSearchedAdapter.notifyDataSetChanged();
                else {
                    lawSearchedAdapter = new LawSearchedAdapter(TimKiemActivity.this, BienBaoSearch);
                    rvLawSearched.setAdapter(lawSearchedAdapter);
                }
            }
        };
        initView();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(runnable);
                rvHashtag.setVisibility(View.GONE);
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!isChangeEdtInAdapter){
                    stringSearched = charSequence.toString();
                    handler.postDelayed(runnable,300);
                } else isChangeEdtInAdapter = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                    case R.id.settings:
                        //startActivity(new Intent(getApplicationContext(),TimKiemActivity.class));
                        //overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void initView(){
        rvHashtag = findViewById(R.id.rv_hashtag);
        edtSearch = findViewById(R.id.edt_search);
        rvLawSearched = findViewById(R.id.rv_law_searched);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        BienBaoSearch = new ArrayList<>();
        listHashtag = new ArrayList<>();

        listHashtag.add("#Lỗi vi phạm");
        listHashtag.add("#Uống rượu bia");
        listHashtag.add("#Quay đầu");
        listHashtag.add("#Rẽ trái");

        hashTagAdapter = new HashTagAdapter(this,listHashtag);
        rvHashtag.setLayoutManager(linearLayoutManager);
        rvHashtag.setAdapter(hashTagAdapter);

    }
}
