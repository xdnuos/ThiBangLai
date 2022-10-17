package com.example.thibanglai.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thibanglai.R;
import com.example.thibanglai.interf.IItemClick;
import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.other.FormattingString;
import com.example.thibanglai.ui.DetailLawActivity;

import java.util.List;

public class LawSearchedAdapter extends RecyclerView.Adapter<LawSearchedAdapter.LawSearchedViewHolder> {

    Context context;
    List<BienBao> laws;
    FormattingString getTenItem = new FormattingString();

    public LawSearchedAdapter(Context context, List<BienBao> laws) {
        this.context = context;
        this.laws = laws;
    }

    @NonNull
    @Override
    public LawSearchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_searched,parent,false);
        return new LawSearchedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LawSearchedViewHolder holder, int position) {
        BienBao bienBao = laws.get(position);
        holder.setiItemClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, DetailLawActivity.class);
                context.startActivity(intent);
            }
        });
        Resources resources = context.getResources();
        holder.imgThumb.setImageResource(resources.getIdentifier(bienBao.getThumb(),"drawable",context.getPackageName()));
        holder.tvContent.setText(bienBao.getNoiDung());
        holder.tvName.setText(getTenItem.getTenItem(context,bienBao.getMaBienBao(), bienBao.getLoaiBienBao(), bienBao.getTenBienBao()));
    }

    @Override
    public int getItemCount() {
        if(laws == null) return 0;
        return laws.size();
    }

    public class LawSearchedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgThumb;
        TextView tvName,tvContent;
        IItemClick iItemClick;

        public void setiItemClick(IItemClick iItemClick){
            this.iItemClick = iItemClick;
        }

        public LawSearchedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb_searched);
            tvName = itemView.findViewById(R.id.tv_name_searched);
            tvContent = itemView.findViewById(R.id.tv_content_searched);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iItemClick.onClick(view,getAdapterPosition());
        }
    }
}
