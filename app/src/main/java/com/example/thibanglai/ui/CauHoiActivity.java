package com.example.thibanglai.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListCauHoiAdapter;
import com.example.thibanglai.adapter.LoaiBienBaoAdapter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CauHoiActivity extends AppCompatActivity {
    //Khai bao
    public static RecyclerView rv_listCH;
    List<String> listCH;
    ListCauHoiAdapter listCauHoiAdapter;
    GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        set_listCH();
    }
    private void set_listCH(){
        rv_listCH = findViewById(R.id.listCauHoi);

        listCH = new ArrayList<>();
        for (int CH_index =1; CH_index<=25; CH_index++){
            listCH.add(String.valueOf(CH_index));
        }

        listCauHoiAdapter = new ListCauHoiAdapter(this,listCH);
        mGridLayoutManager = new GridLayoutManager(this,7);
        rv_listCH.setAdapter(listCauHoiAdapter);
        rv_listCH.setLayoutManager(mGridLayoutManager);
    }
}