package com.example.thibanglai.ui;

import static com.example.thibanglai.ui.search.SearchFragment.rvHashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.model.ItemLaw;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LawDetailActivity extends AppCompatActivity {
    ImageView btn_back;
    LinearLayout LL_khacphuc,LL_bo_sung;
    TextView tv_namePen,tv_object,tv_fines,tv_additional_pen,tv_remedial_reasures;
    BottomNavigationView bottomNavigationView;

    private void setControl(){
        btn_back = findViewById(R.id.btn_back);
        tv_namePen = findViewById(R.id.tv_namePen);
        tv_object = findViewById(R.id.tv_object);
        tv_fines = findViewById(R.id.tv_fines);
        tv_additional_pen = findViewById(R.id.tv_additional_pen);
        tv_remedial_reasures = findViewById(R.id.tv_remedial_reasures);
        LL_khacphuc = findViewById(R.id.LL_khacphuc);
        LL_bo_sung = findViewById(R.id.LL_bo_sung);
    }

    private void setEvent(){
        ItemLaw item = (ItemLaw) getIntent().getExtras().getSerializable("value");
        tv_namePen.setText(item.getName());
        tv_object.setText(item.getObject());
        tv_fines.setText(item.getFines());

        if (item.getAditional_pen()!=null){
            tv_additional_pen.setText(item.getAditional_pen());
        }else{
            LL_bo_sung.setVisibility(View.GONE);
        }
        if (item.getRemedial_measures()!=null){
            tv_remedial_reasures.setText(item.getRemedial_measures());
        }else{
            LL_khacphuc.setVisibility(View.GONE);
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);

        Intent intent = getIntent();
        setControl();
        setEvent();

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