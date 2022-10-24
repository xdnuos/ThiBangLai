package com.example.thibanglai.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.model.DA;
import com.example.thibanglai.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    Context context;
    List<String> list_answer;

    public QuestionAdapter(Context context, List<String> answer) {
        this.context = context;
        this.list_answer = answer;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_answer,parent,false);
        return new QuestionAdapter.QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        holder.textView.setText(list_answer.get(position));
    }

    @Override
    public int getItemCount() {
        if(list_answer == null) return 0;
        return list_answer.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        IItemClick iItemClick;

        public void setiItemClick(IItemClick iItemClick){
            this.iItemClick = iItemClick;
        }

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_answer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClick.onClick(view,getAdapterPosition());
        }
    }
    public void setMarked(){


    }

}
