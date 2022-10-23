package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameDB;
import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.thibanglai.R;
import com.example.thibanglai.database.Database;
import com.skydoves.expandablelayout.ExpandableLayout;


public class MainActivity extends AppCompatActivity {
    Button btn_lamde;
    boolean is_Expand = false;
    ExpandableLayout ex;

    SharedPreferences sharedPreferences;
    boolean isFirstRun;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bien_bao);
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
    }
    private void Khoi_tao() {
        //databaseBB = new Database(this,nameDB,null,1)
        sharedPreferences = getSharedPreferences(nameSharedPreference,MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        if(isFirstRun){
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun",false);
            editor.apply();
            //random ra 5 de thi
            //databaseBB.FirstRun();
        }
    }
}