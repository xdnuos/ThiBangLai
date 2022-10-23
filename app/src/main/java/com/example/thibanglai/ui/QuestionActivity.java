package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListCauHoiAdapter;
import com.example.thibanglai.adapter.QuestionAdapter;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.dialog.Submit_dialog;
import com.google.android.material.button.MaterialButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
// CẤU TRÚC ĐỀ THI: 15 LUẬT GIAO THÔNG, 5 SA HÌNH, 5 BIỂN BÁO
public class QuestionActivity extends AppCompatActivity{
    //Khai bao
    public static RecyclerView rv_listCH,rv_listDA;
    List<String> listCH,listDA;
    ArrayList ketqua;
    ListCauHoiAdapter listCauHoiAdapter;
    QuestionAdapter listDA_adapter;
    GridLayoutManager mGridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    public int current_answer =1;


    ImageButton btn_back;
    MaterialButton btn_submit;
    Button btn_next,btn_yes,btn_no;
    ToggleButton btn_save;

    Database database;

    //code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        database = new Database(this,nameDB,null,1);
        setControl();
        set_answer(current_answer);
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
                save_status();
                finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_answer==25){
                    submitDialog();
                }else nextQuestion();

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDialog();
            }
        });
    }
    public void change_nextButton(){
        btn_next.setText("Nộp bài");
    }

    public void submit() {
//        ArrayList ketqua = new ArrayList<>();
//        ketqua.add(15); //correct answer
//        ketqua.add(150); //time
//        ketqua.add(0);//question_dead
//        Intent intent = new Intent(getApplicationContext(), DetailBienBaoActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putIntegerArrayList("value",ketqua);
//        intent.putExtras(bundle);
//        startActivity(intent);
        Toast.makeText(this, "Đã nộp bài", Toast.LENGTH_SHORT).show();
    }

    private void submitDialog() {
            final Submit_dialog dialog = new Submit_dialog(this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
    }
    private void save_status() {
        //save câu đang làm
        //save time
        //save lựa chọn của từng câu hỏi
        // nếu xong thì save_result();
    }
    private void save_result(){
        //save câu đúng và sai
    }

    private void nextQuestion() {
        listDA.clear();
        // lấy question từ db ra
        //get index
        set_answer(1);
    }
    private void get_answer(){
        // get đáp án đã chọn, chưa chọn thì bỏ trống
    }
    private void setControl() {
        btn_back = findViewById(R.id.btnBack);
        btn_submit = findViewById(R.id.btn_submit);
        btn_next = findViewById(R.id.btn_next);
        btn_save = findViewById(R.id.btn_save);
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
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
    public void set_answer(int question_index){
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