package com.example.thibanglai.ui;

import static com.example.thibanglai.setting.MyApplication.nameDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.BienBaoAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.model.BienBao;

import java.util.ArrayList;

public class DetailBienBaoActivity extends AppCompatActivity {
    TextView CTBB_tv_tenBB,CTBB_tv_noiDungBB;
    ImageView CTBB_iv_BB;
    ListView lv_bien_bao;
    ImageButton ib_Back;

    ArrayList<BienBao> data = new ArrayList<>();
    BienBaoAdapter adapter;

    DataBaseHelper databaseBB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_bien_bao);
        setControl();
        setEvent();

    }

    private void setEvent() {
        BienBao item = (BienBao) getIntent().getExtras().getSerializable("value");
        CTBB_tv_tenBB.setText(getTenItem(item.getMaBienBao(), item.getLoaiBienBao(), item.getTenBienBao()));
        CTBB_tv_noiDungBB.setText(item.getNoiDung());
        CTBB_iv_BB.setImageResource(this.getResources().getIdentifier(item.getThumb(),"drawable",this.getPackageName()));
        List_bien_bao();

        ib_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BienBaoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void List_bien_bao() {
        databaseBB = new DataBaseHelper(this);
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
    }

    private void setControl() {
        CTBB_tv_tenBB = findViewById(R.id.CTBB_tv_tenBB);
        CTBB_tv_noiDungBB = findViewById(R.id.CTBB_tv_noiDungBB);
        CTBB_iv_BB = findViewById(R.id.CTBB_iv_BB);
        lv_bien_bao = findViewById(R.id.lv_bien_bao);
        ib_Back = findViewById(R.id.ib_Back);

    }
    public String getTenItem(String maBienBao, String loaiBienBao, String tenBienBao ) {
        return this.getResources().getString(R.string.item_bien_bao,loaiBienBao,maBienBao,tenBienBao);
    }
}