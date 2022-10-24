package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListCauHoiAdapter;
import com.example.thibanglai.adapter.QuestionAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.dialog.Submit_dialog;
import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.model.DA;
import com.example.thibanglai.model.Questions;
import com.google.android.material.button.MaterialButton;

import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

    DataBaseHelper database;
    Cursor cursor;
    ArrayList<Questions> listQuestion = new ArrayList<>();

    //code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        database = new DataBaseHelper(this);
        setControl();
        getListQuestion();
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
                    listQuestion.get(current_answer-1).setMarked(true);
                } else {
                    btn_save.setChecked(false);
                    listQuestion.get(current_answer-1).setMarked(true);
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
    private void setMarked(){

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
    private void getListQuestion(){
        cursor = database.getData("SELECT * FROM Question as qs JOIN links as lk ON qs.id = lk.maCH WHERE lk.maDe = 1");
        while (cursor.moveToNext()){
            //Questions(String question_content, String image, String answer1, String answer2, String answer3, String answer4, int correct_answer, String answer_des, boolean marked, boolean wrong, boolean question_die, int choose)
            listQuestion.add(new Questions(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(10) == 1,cursor.getInt(11)==1,cursor.getInt(12)==1,0));
        }
    }
    public void set_answer(int question_index){
        String a,b,c,d;
        a = listQuestion.get(question_index-1).getAnswer1();
        b = listQuestion.get(question_index-1).getAnswer2();
        c = listQuestion.get(question_index-1).getAnswer3();
        d = listQuestion.get(question_index-1).getAnswer4();

        listDA = new ArrayList<>();
        listDA.add(a);
        listDA.add(b);
        if (c!=null){
            listDA.add(c);
        }
        if (d!=null){
            listDA.add(d);
        }

        rv_listDA = findViewById(R.id.listDapAn);
        listDA_adapter = new QuestionAdapter(this,listDA);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_listDA.setAdapter(listDA_adapter);
        rv_listDA.setLayoutManager(linearLayoutManager);
    }
}