package com.example.thibanglai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.ui.QuestionActivity;

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
        View itemview;
        if (viewType != 0){
            itemview = LayoutInflater.from(context).inflate(R.layout.item_answer_active,parent,false);
        }else itemview= LayoutInflater.from(context).inflate(R.layout.item_answer,parent,false);

        return new QuestionAdapter.QuestionViewHolder(itemview);
    }
    @Override
    public int getItemViewType(int position) {
        if (position+1 == ((QuestionActivity)context).getchoose()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                //((QuestionActivity)context).save_dap_an(position);
                int choose = ((QuestionActivity)context).getchoose();
                if (choose != position+1){// neu dap an cu khac dap an da chon
                    ((QuestionActivity)context).setchoose(position+1);//luu dap an ms vao db
                } else { // neu dap an cu la dap an da chon
                    ((QuestionActivity)context).setchoose(0); // huy luu
                }
                int cr_ans= ((QuestionActivity)context).current_answer;
                ((QuestionActivity)context).set_answer(cr_ans);
            }
        });
        int choose = ((QuestionActivity)context).getchoose();
        if(choose==position+1){
            holder.textView.setTextColor(Color.parseColor("#000000"));
            holder.textView.setSelected(false);
        }

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
}
