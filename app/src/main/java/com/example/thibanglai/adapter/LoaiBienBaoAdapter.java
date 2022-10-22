package com.example.thibanglai.adapter;

import static com.example.thibanglai.setting.MyApplication.isChangeEdtInAdapter;
import static com.example.thibanglai.setting.MyApplication.nameDB;

import static com.example.thibanglai.ui.BienBaoActivity.rv_loaiBB;
import static com.example.thibanglai.ui.BienBaoActivity.searchView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.database.Database;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.model.BienBao;

import java.util.List;

public class LoaiBienBaoAdapter extends RecyclerView.Adapter<LoaiBienBaoAdapter.LoaiBienBaoViewHolder> {

    Context context;
    List<String> listLoaiBB;
    Database database;

    public LoaiBienBaoAdapter(Context context, List<String> listLoaiBB) {
        this.context = context;
        this.listLoaiBB = listLoaiBB;
        database = new Database(context,nameDB,null,1);
    }

    @NonNull
    @Override
    public LoaiBienBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listbb,parent,false);
        return new LoaiBienBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiBienBaoViewHolder holder, int position) {
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                String loaiBB = listLoaiBB.get(position);
                searchView.setQuery(loaiBB,false);
            }
        });
        holder.textView.setText(listLoaiBB.get(position));
    }

    @Override
    public int getItemCount() {
        if(listLoaiBB ==null) return 0;
        return listLoaiBB.size();
    }

    public class LoaiBienBaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        IItemClick iItemClick;

        public void setiItemClick(IItemClick iItemClick){
            this.iItemClick = iItemClick;
        }

        public LoaiBienBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_loaiBB);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClick.onClick(view,getAdapterPosition());
        }
    }
}
