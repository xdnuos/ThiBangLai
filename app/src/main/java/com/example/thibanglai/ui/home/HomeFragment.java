package com.example.thibanglai.ui.home;

import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thibanglai.MainActivity;
import com.example.thibanglai.R;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.databinding.FragmentHomeBinding;
import com.example.thibanglai.ui.BienBaoActivity;
import com.example.thibanglai.ui.CauSaiActivity;
import com.example.thibanglai.ui.ExamActivity;
import com.example.thibanglai.ui.ResearchLawActivity;
import com.example.thibanglai.ui.TipActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button btn_lam_de,btn_cau_sai,btn_bien_bao,btn_cau_save,btn_tips,btn_tra_cuu_luat;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setControl();
        setEvent();
        return root;
    }

    private void setControl() {
        btn_lam_de = binding.getRoot().findViewById(R.id.btn_lamde);
        btn_cau_sai = binding.getRoot().findViewById(R.id.btn_cau_sai);
        btn_bien_bao = binding.getRoot().findViewById(R.id.btn_bien_bao);
        btn_cau_save = binding.getRoot().findViewById(R.id.btn_cau_save);
        btn_tips = binding.getRoot().findViewById(R.id.btn_tips);
        btn_tra_cuu_luat = binding.getRoot().findViewById(R.id.btn_tra_cuu_luat);
    }
    private void setEvent() {
        btn_bien_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), BienBaoActivity.class);
                startActivity(intent);
            }
        });
        btn_tra_cuu_luat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ResearchLawActivity.class);
                startActivity(intent);
            }
        });
        btn_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TipActivity.class);
                startActivity(intent);
            }
        });
        btn_lam_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ExamActivity.class);
                startActivity(intent);
            }
        });
        btn_cau_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                intent.putExtra("tab",2);
                startActivity(intent);
            }
        });
        btn_cau_sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CauSaiActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}