package com.example.thibanglai.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.adapter.BienBaoAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.thibanglai.adapter.LoaiBienBaoAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.BienBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BienBaoActivity extends AppCompatActivity
{
    public static ArrayList<BienBao> data = new ArrayList<>();
    BienBaoAdapter adapter;
    BienBao bienBao = null;
    ListView lv_bien_bao;

    DataBaseHelper databaseBB;
    SharedPreferences sharedPreferences;
    boolean isFirstRun;
    SharedPreferences.Editor editor;

    LoaiBienBaoAdapter loaiBienBaoAdapter;
    List<String> listLoaiBB;
    LinearLayoutManager linearLayoutManager;
    BottomNavigationView bottomNavigationView;
    public static RecyclerView rv_loaiBB;
    public static SearchView searchView;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bien_bao);
        setControl();
        setListLoaiBB();
        databaseBB = new DataBaseHelper(this);
        setEvent();
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
                        startActivity(new Intent(getApplicationContext(), com.example.thibanglai.MainActivity.class));
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

    private void setControl() {
        lv_bien_bao = findViewById(R.id.lv_bien_bao);
        searchView = findViewById(R.id.searchView_BB);
        btn_back = findViewById(R.id.btn_back);
    }
    private void setEvent() {
        databaseBB.openDatabase();
        data.clear();
        data.addAll(databaseBB.ReadBienBao());
        adapter = new BienBaoAdapter(this,R.layout.item_bien_bao,data);
        lv_bien_bao.setAdapter(adapter);

        lv_bien_bao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final BienBao item=data.get(i);
                Intent intent = new Intent(getApplicationContext(), DetailBienBaoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value",item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //SEARCH------------------
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return false;
            }
        });
    }
    private void setListLoaiBB(){
        rv_loaiBB = findViewById(R.id.rv_loaiBienBao);

        listLoaiBB = new ArrayList<>();
        listLoaiBB.add("Biển báo cấm");
        listLoaiBB.add("Biển hiệu lệnh");
        listLoaiBB.add("Biển chỉ dẫn");
        listLoaiBB.add("Biển cảnh báo");

        loaiBienBaoAdapter = new LoaiBienBaoAdapter(this,listLoaiBB);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_loaiBB.setAdapter(loaiBienBaoAdapter);
        rv_loaiBB.setLayoutManager(linearLayoutManager);
    }
}
