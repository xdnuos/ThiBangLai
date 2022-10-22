package com.example.thibanglai.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListCauHoiAdapter;
import com.example.thibanglai.adapter.QuestionAdapter;
import com.google.android.material.button.MaterialButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    //Khai bao
    public static RecyclerView rv_listCH,rv_listDA;
    List<String> listCH,listDA;
    ListCauHoiAdapter listCauHoiAdapter;
    QuestionAdapter listDA_adapter;
    GridLayoutManager mGridLayoutManager;
    LinearLayoutManager linearLayoutManager;

    ImageButton btn_back;
    MaterialButton btn_submit;
    Button btn_next;
    ToggleButton btn_save;

    //code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setControl();
        set_answer();
        set_listCH();
        setEvent();
    }

    private void setEvent() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_save.isChecked()){
                    btn_save.setChecked(true);
                    //marked = true
                } else {
                    btn_save.setChecked(false);
                    //marked = false
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if so cau = 25 else hide
                nextQuestion();
            }
        });

    }

    private void nextQuestion() {
        listDA.clear();
        listDA.add("cde");
        listDA.add("cde");
        listDA.add("cde");
    }

    private void setControl() {
        btn_back = findViewById(R.id.btnBack);
        btn_submit = findViewById(R.id.btn_submit);
        btn_next = findViewById(R.id.btn_next);
        btn_save = findViewById(R.id.btn_save);
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
    private void set_answer(int question_index){
        rv_listDA = findViewById(R.id.listDapAn);

        listDA = new ArrayList<>();
        listDA.add("abc");
        listDA.add("abc");
        listDA.add("abc");

        listDA_adapter = new QuestionAdapter(this,listDA);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_listDA.setAdapter(listDA_adapter);
        rv_listDA.setLayoutManager(linearLayoutManager);
    }

}