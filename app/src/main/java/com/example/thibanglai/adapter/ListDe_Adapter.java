package com.example.thibanglai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.thibanglai.R;
import com.example.thibanglai.model.Exam;

import java.util.ArrayList;

public class ListDe_Adapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Exam> data;
    public ListDe_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Exam> data) {
        super(context, resource, data);
        this.context=context;
        this.data=data;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(resource, null);
            holder.iv_de=convertView.findViewById(R.id.iv_de);
            holder.tv_de=convertView.findViewById(R.id.tv_de);
            holder.so_cau_da_lam=convertView.findViewById(R.id.so_cau_da_lam);
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder) convertView.getTag();
        final Exam item=data.get(position);
        if(item.getMaDe() == 1){
            holder.tv_de.setText("Tạo đề ngẫu nhiên");
            holder.so_cau_da_lam.setText("");
            holder.iv_de.setImageResource(R.drawable.ic_el_random);
        } else {
            holder.tv_de.setText("Đề: "+String.valueOf(item.getMaDe()-1));
            holder.so_cau_da_lam.setText(item.getNum_answer()+"/25");
        }
        return convertView;
    }
    public class ViewHolder{
        ImageView iv_de;
        TextView tv_de,so_cau_da_lam;
    }
}
