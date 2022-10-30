package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thibanglai.R;
import com.example.thibanglai.database.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skydoves.expandablelayout.ExpandableLayout;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    boolean is_Expand = false;
    ExpandableLayout ex;

    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    boolean isFirstRun;
    SharedPreferences.Editor editor;
    Button btn_lam_de,btn_cau_sai,btn_bien_bao,btn_cau_save,btn_tips,btn_tra_cuu_luat;
    DataBaseHelper database;

    private void setControl() {
        btn_lam_de = findViewById(R.id.btn_lamde);
        btn_cau_sai = findViewById(R.id.btn_cau_sai);
        btn_bien_bao = findViewById(R.id.btn_bien_bao);
        btn_cau_save = findViewById(R.id.btn_cau_save);
        btn_tips = findViewById(R.id.btn_tips);
        btn_tra_cuu_luat = findViewById(R.id.btn_tra_cuu_luat);
    }
    private void setEvent() {
        btn_bien_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BienBaoActivity.class);
                startActivity(intent);
            }
        });
        btn_tra_cuu_luat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResearchLawActivity.class);
                startActivity(intent);
            }
        });
        btn_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TipActivity.class);
                startActivity(intent);
            }
        });
        btn_lam_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                startActivity(intent);
            }
        });
        btn_cau_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CauLuuActivity.class);
                startActivity(intent);
            }
        });
        btn_cau_sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CauSaiActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Khoi_tao();
            Log.d("adu", "Copy database ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setControl();
        setEvent();
//        ex = findViewById(R.id.CT_cau_TL);
//        ex.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(is_Expand==false){
//                    is_Expand=true;
//                    ex.expand();
//                }else {
//                    is_Expand=false;
//                    ex.collapse();
//                }
//            }
//        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),TimKiemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),CauLuuActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }



    private void Khoi_tao() throws IOException {
        database = new DataBaseHelper(this);
        sharedPreferences = getSharedPreferences(nameSharedPreference,MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        if(isFirstRun){
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun",false);
            editor.apply();
            database.createDatabase();
        }
    }

}