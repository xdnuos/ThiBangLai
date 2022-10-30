package com.example.thibanglai.ui.search;

import static com.example.thibanglai.setting.MyApplication.isChangeEdtInAdapter;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.HashTagAdapter;
import com.example.thibanglai.adapter.LawSearchedAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.databinding.FragmentSearchBinding;
import com.example.thibanglai.model.BienBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    public static EditText edtSearch;
    public static RecyclerView rvHashtag,rvLawSearched;
    public static LawSearchedAdapter lawSearchedAdapter;
    HashTagAdapter hashTagAdapter;
    List<String> listHashtag;
    LinearLayoutManager linearLayoutManager;
    DataBaseHelper database;
    Handler handler;
    Runnable runnable;
    public static List<BienBao> BienBaoSearch;
    Cursor cursor;
    String stringSearched="";
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //code
        database = new DataBaseHelper(getActivity());
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                BienBaoSearch.clear();
                cursor = database.getData("SELECT * FROM Signs WHERE name LIKE '%"+stringSearched+"%'"+" OR description LIKE '%"+stringSearched+"%'");
                while (cursor.moveToNext()){
                    BienBaoSearch.add(new BienBao(cursor.getInt(5),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(6)));
                }
                if(stringSearched.length()==0) {
                    BienBaoSearch.clear();
                    rvHashtag.setVisibility(View.VISIBLE);
                }
                if(lawSearchedAdapter!=null) lawSearchedAdapter.notifyDataSetChanged();
                else {
                    lawSearchedAdapter = new LawSearchedAdapter(getActivity(), BienBaoSearch);
                    rvLawSearched.setAdapter(lawSearchedAdapter);
                }
            }
        };
        initView();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(runnable);
                rvHashtag.setVisibility(View.GONE);
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!isChangeEdtInAdapter){
                    stringSearched = charSequence.toString();
                    handler.postDelayed(runnable,300);
                } else isChangeEdtInAdapter = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //code
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void initView(){

        rvHashtag = binding.getRoot().findViewById(R.id.rv_hashtag);
        edtSearch = binding.getRoot().findViewById(R.id.edt_search);
        rvLawSearched = binding.getRoot().findViewById(R.id.rv_law_searched);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        BienBaoSearch = new ArrayList<>();
        listHashtag = new ArrayList<>();

        listHashtag.add("#Rẽ phải");
        listHashtag.add("#Uống rượu bia");
        listHashtag.add("#Quay đầu");
        listHashtag.add("#Rẽ trái");

        hashTagAdapter = new HashTagAdapter(getActivity(),listHashtag);
        rvHashtag.setLayoutManager(linearLayoutManager);
        rvHashtag.setAdapter(hashTagAdapter);
    }
}