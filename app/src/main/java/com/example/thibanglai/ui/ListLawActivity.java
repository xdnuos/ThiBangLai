package com.example.thibanglai.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ItemLawAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.model.ItemLaw;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListLawActivity extends AppCompatActivity {

    ListView listView;
    ItemLawAdapter adapter;
    List<ItemLaw> list;
    ImageView btn_back;
    DataBaseHelper database;


    public void openDetailLaw(int law_index){
        Intent intent = new Intent(this, LawDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("value",list.get(law_index));
        intent.putExtras(bundle);
        this.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_law);
        BottomNavigationView bottomNavigationView;
        listView = findViewById(R.id.lv_law);
        Cursor cursor;
        database = new DataBaseHelper(this);

        //get type
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        list= new ArrayList<>();

        cursor = database.getData("SELECT * FROM law WHERE Group_ID="+type);
        while (cursor.moveToNext()){
            list.add(new ItemLaw(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5)));
        }
        adapter = new ItemLawAdapter(this,list);
        listView.setAdapter(adapter);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        //  BOTTOM NAVIGATION
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