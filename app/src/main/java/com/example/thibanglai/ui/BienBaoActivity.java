package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameDB;
import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.BienBaoAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.thibanglai.adapter.LoaiBienBaoAdapter;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.model.BienBao;

import java.util.ArrayList;
import java.util.List;

public class BienBaoActivity extends AppCompatActivity
{
    ArrayList<BienBao> data = new ArrayList<>();
    BienBaoAdapter adapter;

    BienBao bienBao = null;
    ListView lv_bien_bao;

    Database databaseBB;
    SharedPreferences sharedPreferences;
    boolean isFirstRun;
    SharedPreferences.Editor editor;

    LoaiBienBaoAdapter loaiBienBaoAdapter;
    List<String> listLoaiBB;
    LinearLayoutManager linearLayoutManager;
    public static RecyclerView rv_loaiBB;
    public static SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bien_bao);
        setControl();
        setListLoaiBB();
        Khoi_tao();
        setEvent();
    }

    private void Khoi_tao() {
        databaseBB = new Database(this,nameDB,null,1);
        sharedPreferences = getSharedPreferences(nameSharedPreference,MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        if(isFirstRun){
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun",false);
            editor.apply();
            databaseBB.FirstRun();
        }
    }

    private void setControl() {
        lv_bien_bao = findViewById(R.id.lv_bien_bao);
        searchView = findViewById(R.id.searchView_BB);
    }
    private void setEvent() {
        //databaseBB = new Database(this,nameDB,null,1);
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
