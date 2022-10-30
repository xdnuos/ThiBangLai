package com.example.thibanglai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.interf.IItemClick;

import java.util.List;

public class ListSaveAdapter extends RecyclerView.Adapter<ListSaveAdapter.ListCauHoiViewHolder> {

    Context context;
    List<String> listCauHoi;
    DataBaseHelper database;
    EventListener listener;
    public interface EventListener {
        void openDetailSaveQuestion(int position);
    }

    public ListSaveAdapter(Context context, List<String> listCauHoi,EventListener listener) {
        this.context = context;
        this.listCauHoi = listCauHoi;
        database = new DataBaseHelper(context);
        this.listener = listener;
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
                //((CauLuuActivity)context).openDetailSaveQuestion(position);
                listener.openDetailSaveQuestion(position);
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
