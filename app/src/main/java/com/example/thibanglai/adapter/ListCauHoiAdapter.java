package com.example.thibanglai.adapter;

import static com.example.thibanglai.setting.MyApplication.nameDB;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.ui.QuestionActivity;


import java.util.List;

public class ListCauHoiAdapter extends RecyclerView.Adapter<ListCauHoiAdapter.ListCauHoiViewHolder> {

    Context context;
    List<String> listCauHoi;
    Database database;

    public ListCauHoiAdapter(Context context, List<String> listCauHoi) {
        this.context = context;
        this.listCauHoi = listCauHoi;
        database = new Database(context,nameDB,null,1);
    }

    @NonNull
    @Override
    public ListCauHoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_cau_hoi,parent,false);
        return new ListCauHoiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCauHoiViewHolder holder, int position) {
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                if (context instanceof QuestionActivity) {
                    int cr_ans = position+1;
                    if(cr_ans==25){
                        ((QuestionActivity)context).change_nextButton();
                    }
                    ((QuestionActivity)context).current_answer = cr_ans;
                    ((QuestionActivity)context).set_answer(cr_ans);
                }
            }
        });
        holder.textView.setText(listCauHoi.get(position));
    }

    @Override
    public int getItemCount() {
        if(listCauHoi ==null) return 0;
        return listCauHoi.size();
    }

    public class ListCauHoiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        IItemClick iItemClick;

        public void setiItemClick(IItemClick iItemClick){
            this.iItemClick = iItemClick;
        }

        public ListCauHoiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.btn_list_CH);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClick.onClick(view,getAdapterPosition());
        }
    }
}
