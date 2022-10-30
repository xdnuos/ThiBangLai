package com.example.thibanglai.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.WrongQuestionAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.Questions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailCauSaiActivity extends AppCompatActivity {
    DataBaseHelper database;

    WrongQuestionAdapter listDA_adapter;
    TextView tv_cau_hoi,tv_ten_cau_hoi,tv_des;
    ImageView iv_cauhoi;
    List<String> listDA;
    LinearLayoutManager linearLayoutManager;
    public static RecyclerView rv_listDA;

    //
    TextView tv_time;
    ImageButton btn_back;
    RecyclerView recyclerView;
    ToggleButton btn_save;
    BottomNavigationView bottomNavigationView;

    int qs_index =1;
    Questions item = new Questions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cau_luu);

        item = (Questions) getIntent().getExtras().getSerializable("value");
        database = new DataBaseHelper(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        qs_index= bundle.getInt("qs_index", 1);

        setControl();
        setEvent();
        set_answer();

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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
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
    private void setEvent() {
        status_btnsave();
        tv_time.setText("Câu sai");
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = item.getQuestion_id();
                if (btn_save.isChecked() & !item.isMarked()){
                    btn_save.setChecked(true);
                    setMarked(id,true);
                    item.setMarked(true);
                } else {
                    btn_save.setChecked(false);
                    setMarked(id,false);
                    item.setMarked(false);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CauSaiActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setMarked(int question_id,boolean marked_stt){
        //setMarked(int question_id,boolean marked_stt)
        database.setMarked(question_id,marked_stt);
    }
    public void status_btnsave(){
        if (item.isMarked()){
            btn_save.setChecked(true);
        } else btn_save.setChecked(false);
    }
    private void setControl() {
        btn_back = findViewById(R.id.btnBack);
        btn_save = findViewById(R.id.btn_save);
        tv_cau_hoi = findViewById(R.id.tv_cau_hoi);
        tv_ten_cau_hoi = findViewById(R.id.tv_ten_cau_hoi);
        recyclerView = findViewById(R.id.listDapAn);
        iv_cauhoi = findViewById(R.id.iv_cauhoi);
        tv_des = findViewById(R.id.tv_des);
        tv_time = findViewById(R.id.tv_time);
    }

    public void set_answer(){
        tv_ten_cau_hoi.setText("Câu "+String.valueOf(qs_index));
        tv_cau_hoi.setText(item.getQuestion_content());
        tv_des.setText(item.getAnswer_des());
        String image = item.getImage();
        if(image != null){
            try {
                InputStream ims = this.getAssets().open("images/"+image);
                Drawable d = Drawable.createFromStream(ims, null);
                iv_cauhoi.setImageDrawable(d);
            } catch (IOException ex) {
                Log.d("error", "error load image: "+ex);
            }
        } else{
            iv_cauhoi.setImageDrawable(null);
        }

        //
        String a,b,c,d;
        a = item.getAnswer1();
        b = item.getAnswer2();
        c = item.getAnswer3();
        d = item.getAnswer4();
        Log.d("cautl", a+b);

        listDA = new ArrayList<>();
        listDA.add(a);
        listDA.add(b);
        if (c!=null){
            listDA.add(c);
        }
        if (d!=null){
            listDA.add(d);
        }
        rv_listDA = findViewById(R.id.listDapAnSave);
        listDA_adapter = new WrongQuestionAdapter(this,listDA);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_listDA.setAdapter(listDA_adapter);
        rv_listDA.setLayoutManager(linearLayoutManager);
    }
    public int getCorrectAnswer(){
        return item.getCorrect_answer();
    }
    public int getWrongAnswer(){
        Log.d("id_wrong choose", String.valueOf(item.getQuestion_id()));
        return database.getWrongChoose(item.getQuestion_id());

    }
}