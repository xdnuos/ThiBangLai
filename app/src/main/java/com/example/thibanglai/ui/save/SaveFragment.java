package com.example.thibanglai.ui.save;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.adapter.ListSaveAdapter;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.databinding.FragmentSaveBinding;
import com.example.thibanglai.model.Questions;
import com.example.thibanglai.ui.DetailCauLuuActivity;

import java.util.ArrayList;
import java.util.List;

public class SaveFragment extends Fragment implements ListSaveAdapter.EventListener{

    private FragmentSaveBinding binding;

    public int number_save = 0;
    RecyclerView rv_list_save;
    Cursor cursor;
    DataBaseHelper database;
    List<String> listNumberSave;
    ListSaveAdapter listSaveAdapter;
    ArrayList<Questions> listQuestion = new ArrayList<>();
    GridLayoutManager mGridLayoutManager;
    TextView tv_chua_luu;
    ImageButton btn_back;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSaveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setControl();
        setEvent();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void setEvent() {
        database = new DataBaseHelper(getActivity());
        number_save = database.getNumberSave();
        set_listCH();
        setListQuestion();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    public void openDetailSaveQuestion(int position){
        Intent intent = new Intent(getActivity(), DetailCauLuuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("qs_index",position+1);
        bundle.putSerializable("value",listQuestion.get(position));
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    private void setControl() {
        tv_chua_luu = binding.getRoot().findViewById(R.id.tv_chua_luu);
        rv_list_save = binding.getRoot().findViewById(R.id.rv_list_save);
        btn_back = binding.getRoot().findViewById(R.id.btnBack);
    }
    private void set_listCH(){

        listNumberSave = new ArrayList<>();
        for (int CH_index =1; CH_index<=number_save; CH_index++){
            listNumberSave.add(String.valueOf(CH_index));
        }
        if(listNumberSave.size() ==0){
            tv_chua_luu.setText("Bạn chưa lưu câu hỏi nào !");
        } else tv_chua_luu.setText("");
        listSaveAdapter = new ListSaveAdapter(getActivity(),listNumberSave,this);
        mGridLayoutManager = new GridLayoutManager(getActivity(),7);
        rv_list_save.setAdapter(listSaveAdapter);
        rv_list_save.setLayoutManager(mGridLayoutManager);
    }
    private void setListQuestion(){
        cursor = database.getData("SELECT * FROM Question WHERE marked = 1");
        while (cursor.moveToNext()){
            //Questions(int question_id,String question_content, String image, String answer1, String answer2, String answer3, String answer4, int correct_answer, String answer_des, boolean marked, boolean wrong, boolean question_die, int choose,int maDe)
            listQuestion.add(new Questions(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(10) == 1,cursor.getInt(11)==1,cursor.getInt(12)==1,0,-1));
        }
    }
}