package com.example.thibanglai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.BienBaoAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.model.BienBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity {
    TextView tv_ket_qua,tv_time_lam,tv_so_cau_da_lam,tv_cau_dung,tv_cau_sai;
    ImageButton btn_lam_lai,btn_back;

    ArrayList<BienBao> data = new ArrayList<>();
    BienBaoAdapter adapter;

    DataBaseHelper databaseBB;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ket_qua);
        setControl();
        setEvent();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), TimKiemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.settings:
                        //startActivity(new Intent(getApplicationContext(),TimKiemActivity.class));
                        //overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    //ket_qua: time,cau_dung,cau_sai,cau_chua_lam
    private void setEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String time= bundle.getString("time", "");
        int cau_dung= bundle.getInt("cau_dung", 0);
        int cau_sai= bundle.getInt("cau_sai", 0);
        int cau_chua_lam= bundle.getInt("cau_chua_lam", 0);
        int maDe= bundle.getInt("maDe", 2);

        tv_time_lam.setText(time);
        tv_ket_qua.setText(getKetqua(cau_dung));
        tv_cau_dung.setText(String.valueOf(cau_dung));
        tv_cau_sai.setText(String.valueOf(cau_sai));
        tv_so_cau_da_lam.setText(String.valueOf(25-cau_chua_lam)+"/"+"25");

        btn_lam_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(KetQuaActivity.this, String.valueOf(maDe), Toast.LENGTH_SHORT).show();
                databaseBB = new DataBaseHelper(getApplicationContext());
                databaseBB.clear(maDe);
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("maDe", maDe);
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
    }
    public String getKetqua(int cau_dung){
        if (cau_dung>20){
            tv_ket_qua.setSelected(true);
            return "Đạt";
        } else return "Không Đạt";
    }

    private void setControl() {
        tv_ket_qua = findViewById(R.id.tv_ket_qua);
        tv_time_lam = findViewById(R.id.tv_time_lam);
        tv_so_cau_da_lam = findViewById(R.id.tv_so_cau_da_lam);
        tv_cau_dung = findViewById(R.id.tv_cau_dung);
        tv_cau_sai = findViewById(R.id.tv_cau_sai);
        btn_lam_lai = findViewById(R.id.btn_lam_lai);
        btn_back = findViewById(R.id.btn_back);
    }
}
