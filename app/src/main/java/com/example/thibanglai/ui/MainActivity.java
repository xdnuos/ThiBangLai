package com.example.thibanglai.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.thibanglai.R;
import com.skydoves.expandablelayout.ExpandableLayout;

public class MainActivity extends AppCompatActivity {
    Button btn_lamde;
    boolean is_Expand = false;
    ExpandableLayout ex;

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
}