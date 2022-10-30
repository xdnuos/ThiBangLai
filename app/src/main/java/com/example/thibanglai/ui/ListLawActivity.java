package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ItemLawAdapter;
import com.example.thibanglai.model.ItemLaw;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListLawActivity extends AppCompatActivity {

    ListView listView;
    ItemLawAdapter adapter;
    List<ItemLaw> list;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_law);
        BottomNavigationView bottomNavigationView;
        listView = findViewById(R.id.lv_law);
        list=  new ArrayList<>();
        list.add(new ItemLaw("Không chấp hành hiệu lệnh, chỉ dẫn của biển báo hiệu, vạch kẻ đường","Phạt tiền từ 100.000 đến 200.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        list.add(new ItemLaw("Không chấp hành hiệu lệnh của đèn tín hiệu giao thông","Phạt tiền từ 600.000 đến 1.000.000vnđ"));
        adapter = new ItemLawAdapter(this,list);
        listView.setAdapter(adapter);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //
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