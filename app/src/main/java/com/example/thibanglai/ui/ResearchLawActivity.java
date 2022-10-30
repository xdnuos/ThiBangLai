package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ItemGridAdapter;
import com.example.thibanglai.model.ItemGrid;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ResearchLawActivity extends AppCompatActivity {

    GridView gridView;
    ItemGridAdapter adapter;
    List<ItemGrid> list;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView;
        setContentView(R.layout.activity_research_law);
        gridView = findViewById(R.id.grid_item);
        list = new ArrayList<>();
        list.add(new ItemGrid(R.drawable.hlcd,"Hiệu lệnh chỉ dẫn"));
        list.add(new ItemGrid(R.drawable.tdvkcat,"Tốc độ, khoảng cách an toàn"));
        list.add(new ItemGrid(R.drawable.vcnh,"Vận chuyển người, hàng"));
        list.add(new ItemGrid(R.drawable.tbutvc,"Thiết bị ưu tiên và còi"));
        list.add(new ItemGrid(R.drawable.ndc,"Nồng độ cồn, chất kích thích"));
        list.add(new ItemGrid(R.drawable.gtx,"Giấy tờ xe"));
        list.add(new ItemGrid(R.drawable.dxdx,"Dừng xe, đỗ xe"));
        list.add(new ItemGrid(R.drawable.chnd,"Chuyển hướng nhường đường"));
        list.add(new ItemGrid(R.drawable.dcdm,"Đường cấm, đường một chiều"));
        list.add(new ItemGrid(R.drawable.ttbpt,"Trang thiết bị phương tiện"));
        list.add(new ItemGrid(R.drawable.other,"Khác"));
        adapter = new ItemGridAdapter(this,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ResearchLawActivity.this,ListLawActivity.class);
                startActivity(intent);
            }
        });

        btn_back =findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        int size = bottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            bottomNavigationView.getMenu().getItem(i).setCheckable(false);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                switch (item.getItemId()){
                    case R.id.search:
                        i = new Intent(getApplicationContext(), com.example.thibanglai.MainActivity.class);
                        i.putExtra("tab", 1);
                        startActivity(i);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.save:
                        i = new Intent(getApplicationContext(), com.example.thibanglai.MainActivity.class);
                        i.putExtra("tab", 2);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }
}