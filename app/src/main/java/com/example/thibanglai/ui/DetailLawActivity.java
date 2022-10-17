package com.example.thibanglai.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thibanglai.R;
import com.google.android.material.tabs.TabLayout;

public class DetailLawActivity extends AppCompatActivity {

    ImageView imgBack;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_law);
        tabLayout = findViewById(R.id.tabLayout2);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_tab);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_setting);
        imgBack = findViewById(R.id.img_back_detail_law);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DetailLawActivity.this,TimKiemActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}