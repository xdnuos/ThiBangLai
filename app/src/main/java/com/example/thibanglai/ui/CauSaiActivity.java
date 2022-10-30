package com.example.thibanglai.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListCauHoiAdapter;
import com.example.thibanglai.adapter.ListSaveAdapter;
import com.example.thibanglai.adapter.ListWrongAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.Questions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CauSaiActivity extends AppCompatActivity {
    public int number_wrong = 0;
    RecyclerView rv_list_save;
    Cursor cursor;
    DataBaseHelper database;
    List<String> listNumberSave;
    ListWrongAdapter listWrongAdapter;
    ArrayList<Questions> listQuestion = new ArrayList<>();
    GridLayoutManager mGridLayoutManager;
    BottomNavigationView bottomNavigationView;
    TextView tv_chua_luu;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_sai);
        database = new DataBaseHelper(this);

        setControl();
        setEvent();
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
        number_wrong = database.getNumberWrong();
        set_listCH();
        setListWrongQuestion();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openDetailWrongQuestion(int position){
        Intent intent = new Intent(this,DetailCauSaiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("qs_index",position+1);
        bundle.putSerializable("value",listQuestion.get(position));
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    private void setControl() {
        tv_chua_luu = findViewById(R.id.tv_chua_luu);
        rv_list_save = findViewById(R.id.rv_list_save);
        btn_back = findViewById(R.id.btnBack);
    }
    private void set_listCH(){

        listNumberSave = new ArrayList<>();
        for (int CH_index =1; CH_index<=number_wrong; CH_index++){
            listNumberSave.add(String.valueOf(CH_index));
        }
        if(listNumberSave.size() ==0){
            tv_chua_luu.setText("Bạn chưa làm sai câu nào !");
        } else tv_chua_luu.setText("");
        listWrongAdapter = new ListWrongAdapter(this,listNumberSave);
        mGridLayoutManager = new GridLayoutManager(this,7);
        rv_list_save.setAdapter(listWrongAdapter);
        rv_list_save.setLayoutManager(mGridLayoutManager);
    }
    private void setListWrongQuestion(){
        cursor = database.getData("SELECT * FROM Question WHERE wrong = 1");
        while (cursor.moveToNext()){
            //Questions(int question_id,String question_content, String image, String answer1, String answer2, String answer3, String answer4, int correct_answer, String answer_des, boolean marked, boolean wrong, boolean question_die, int choose,int maDe)
            listQuestion.add(new Questions(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(10) == 1,cursor.getInt(11)==1,cursor.getInt(12)==1,-1,cursor.getInt(13)));
        }
    }

}