package com.example.thibanglai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.ui.DetailCauLuuActivity;

import java.util.List;

public class SaveQuestionAdapter extends RecyclerView.Adapter<SaveQuestionAdapter.QuestionViewHolder> {
    Context context;
    List<String> list_answer;
    public SaveQuestionAdapter(Context context, List<String> answer) {
        this.context = context;
        this.list_answer = answer;
    }

    @NonNull
    @Override
    public SaveQuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview;
        if (viewType != 0){
            itemview = LayoutInflater.from(context).inflate(R.layout.item_answer_active,parent,false);
        }else itemview= LayoutInflater.from(context).inflate(R.layout.item_answer,parent,false);

        return new SaveQuestionAdapter.QuestionViewHolder(itemview);
    }
    @Override
    public int getItemViewType(int position) {
        if (position+1 == ((DetailCauLuuActivity)context).getCorrectAnswer()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.textView.setText(list_answer.get(position));
    }
    @Override
    public int getItemCount() {
        if(list_answer == null) return 0;
        return list_answer.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_answer);
        }
    }
}
