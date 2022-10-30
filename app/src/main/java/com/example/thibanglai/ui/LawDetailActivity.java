package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LawDetailActivity extends AppCompatActivity {
    ImageView btn_back;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);
        btn_back = findViewById(R.id.btn_back);
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