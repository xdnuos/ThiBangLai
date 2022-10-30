package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.thibanglai.R;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.adapter.ListDe_Adapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.model.Exam;
import com.example.thibanglai.model.Questions;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {
    ListView lv_ds_de;
    ArrayList<Exam> data = new ArrayList<>();
    ListDe_Adapter adapter;

    DataBaseHelper database;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_de);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = new DataBaseHelper(this);
        data.addAll(database.ReadDe());
        //tryuen de vao list view
        adapter = new ListDe_Adapter(this,R.layout.item_de,data);
        lv_ds_de.setAdapter(adapter);

        lv_ds_de.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ExamActivity.this, "ok", Toast.LENGTH_LONG).show();
                if (i==0){
                    database.generateDe();
                    data.clear();
                    data.addAll(database.ReadDe());
                    adapter.notifyDataSetChanged();
                }else {
                    final Exam item=data.get(i);
                    if (isFinish(i+1)){
                        database.clear(i+1);
                    }
                    int time = database.getTime(i+1);
                    int currentQuestion = database.getCurrentQuestion(i+1);
                    if (currentQuestion ==0){
                        currentQuestion =1;
                    }
                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                    intent.putExtra("maDe", i+1);
                    intent.putExtra("time", time);
                    intent.putExtra("currentQS", currentQuestion);
                    startActivity(intent);
                }
            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean isFinish(int maDe){
        if (database.getIsFinish(maDe)==1){
            return true;
        }else return false;
    }
    private void setControl() {
        lv_ds_de = findViewById(R.id.lv_ds_de);
    }
}