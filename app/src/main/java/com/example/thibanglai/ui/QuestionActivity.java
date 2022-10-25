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
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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

    TextView tv_cau_hoi,tv_ten_cau_hoi,tv_time;
    CountDownTimer Timer;

    RecyclerView recyclerView;
    //code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        database = new DataBaseHelper(this);
        setControl();
        getListQuestion(1);
        set_answer(current_answer);
        set_listCH();
        setEvent();
        count_down();
        status_btnsave();
    }

    private void setEvent() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = listQuestion.get(current_answer-1).getQuestion_id();
                if (btn_save.isChecked() & !listQuestion.get(current_answer-1).isMarked()){
                    btn_save.setChecked(true);
                    setMarked(id,true);
                    listQuestion.get(current_answer-1).setMarked(true);
                    Toast.makeText(QuestionActivity.this, String.valueOf(database.getMarked(id)), Toast.LENGTH_SHORT).show();
                } else {
                    btn_save.setChecked(false);
                    setMarked(id,false);
                    listQuestion.get(current_answer-1).setMarked(false);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                int i = listQuestion.get(current_answer-1).getChoose();
                Toast.makeText(QuestionActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
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
    public void status_btnsave(){
        if (listQuestion.get(current_answer-1).isMarked()){
            btn_save.setChecked(true);
        } else btn_save.setChecked(false);
    }
    public void submit() {

        int cau_dung=0;
        int cau_sai=0;
        int cau_chua_lam=0;
        //int question_dead=0;
        for (int i = 0; i < listQuestion.size(); i++) {
            int choose = listQuestion.get(i).getChoose();
            if (choose==0){
                cau_chua_lam+=1;
            }
            if (listQuestion.get(i).getChoose()==listQuestion.get(i).getCorrect_answer()){
                cau_dung+=1;
            }else cau_sai+=1;
        }

        Intent intent = new Intent(getApplicationContext(), KetQuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("time",String.valueOf(tv_time.getText()));
        bundle.putInt("cau_dung", cau_dung);
        bundle.putInt("cau_sai", cau_sai);
        bundle.putInt("cau_chua_lam", cau_chua_lam);
        intent.putExtras(bundle);
        startActivity(intent);
        //Toast.makeText(this, "Đã nộp bài", Toast.LENGTH_SHORT).show();
    }

    private void submitDialog() {
            final Submit_dialog dialog = new Submit_dialog(this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
    }
    private void setMarked(int question_id,boolean marked_stt){
        //setMarked(int question_id,boolean marked_stt)
        database.setMarked(question_id,marked_stt);
    }

    private void nextQuestion() {
        //done
        current_answer+=1;
        set_answer(current_answer);
    }
    private void setControl() {
        btn_back = findViewById(R.id.btnBack);
        btn_submit = findViewById(R.id.btn_submit);
        btn_next = findViewById(R.id.btn_next);
        btn_save = findViewById(R.id.btn_save);
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        tv_cau_hoi = findViewById(R.id.tv_cau_hoi);
        tv_ten_cau_hoi = findViewById(R.id.tv_ten_cau_hoi);
        tv_time = findViewById(R.id.tv_time);
        recyclerView = findViewById(R.id.listDapAn);
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
    private void getListQuestion(int maDe){
        cursor = database.getData("SELECT * FROM Question as qs JOIN links as lk ON qs.id = lk.maCH WHERE lk.maDe = 1");
        while (cursor.moveToNext()){
            //Questions(int question_id,String question_content, String image, String answer1, String answer2, String answer3, String answer4, int correct_answer, String answer_des, boolean marked, boolean wrong, boolean question_die, int choose,int maDe)
            listQuestion.add(new Questions(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(10) == 1,cursor.getInt(11)==1,cursor.getInt(12)==1,cursor.getInt(16)));
        }
    }
    public void set_answer(int question_index){
        tv_ten_cau_hoi.setText("Câu "+String.valueOf(question_index));
        tv_cau_hoi.setText(listQuestion.get(question_index-1).getQuestion_content());
        //
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
    public String convert_time(long millisUntilFinished){
        long minutes = (millisUntilFinished / 1000) / 60;
        long seconds = (millisUntilFinished / 1000) % 60;
        return String.valueOf(minutes)+":"+String.valueOf(seconds);
    }
    private void count_down(){
        Timer = new CountDownTimer(1140*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText(convert_time(millisUntilFinished));
            }
            @Override
            public void onFinish() {
                tv_time.setText("Hết giờ");
            }
        }.start();
    }
    public int getchoose(){
        int maDe = 1;
        int question_id = listQuestion.get(current_answer-1).getQuestion_id();
        return database.getChoose(question_id,maDe);
    }
    public void setchoose(int choose){
        int maDe = 1;
        int question_id = listQuestion.get(current_answer-1).getQuestion_id();
        listQuestion.get(current_answer-1).setChoose(choose);
        database.setChoose(question_id,choose,maDe);
    }
}