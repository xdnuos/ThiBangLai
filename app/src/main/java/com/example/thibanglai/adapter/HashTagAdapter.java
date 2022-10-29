package com.example.thibanglai.adapter;

import static com.example.thibanglai.setting.MyApplication.isChangeEdtInAdapter;
import static com.example.thibanglai.ui.TimKiemActivity.edtSearch;
import static com.example.thibanglai.ui.TimKiemActivity.lawSearchedAdapter;
import static com.example.thibanglai.ui.TimKiemActivity.BienBaoSearch;
import static com.example.thibanglai.ui.TimKiemActivity.rvHashtag;
import static com.example.thibanglai.ui.TimKiemActivity.rvLawSearched;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.database.DataBaseHelper;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.model.BienBao;

import java.util.List;

public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.HashTagViewHolder> {

    Context context;
    List<String> listHashtag;
    DataBaseHelper database;

    public HashTagAdapter(Context context, List<String> listHashtag) {
        this.context = context;
        this.listHashtag = listHashtag;
        database = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public HashTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hastag,parent,false);
        return new HashTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HashTagViewHolder holder, int position) {
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                isChangeEdtInAdapter = true;
                rvHashtag.setVisibility(View.GONE);
                String tag = listHashtag.get(position);
                edtSearch.setText(tag);
                Cursor cursor = database.getData("SELECT * FROM Signs WHERE hastag = '"+tag+"'");
                BienBaoSearch.clear();
                while (cursor.moveToNext()){
                    BienBaoSearch.add(new BienBao(cursor.getInt(5),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(6)));
                }
                if(lawSearchedAdapter!=null) lawSearchedAdapter.notifyDataSetChanged();
                else {
                    lawSearchedAdapter = new LawSearchedAdapter(context, BienBaoSearch);
                    rvLawSearched.setAdapter(lawSearchedAdapter);
                }
            }
        });
        holder.textView.setText(listHashtag.get(position));
    }

    @Override
    public int getItemCount() {
        if(listHashtag==null) return 0;
        return listHashtag.size();
    }

    public class HashTagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        IItemClick iItemClick;

        public void setiItemClick(IItemClick iItemClick){
            this.iItemClick = iItemClick;
        }

        public HashTagViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_hashtag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClick.onClick(view,getAdapterPosition());
        }
    }
}
