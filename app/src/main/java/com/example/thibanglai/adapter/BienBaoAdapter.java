package com.example.thibanglai.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.R;
import com.example.thibanglai.other.FormattingString;
import java.io.IOException;
import java.io.InputStream;
import android.graphics.drawable.Drawable;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import java.util.ArrayList;

public class BienBaoAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<BienBao> arrBienBao;
    ArrayList<BienBao> data_filter = new ArrayList<BienBao>();
    FormattingString getTenItem = new FormattingString();


    public BienBaoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BienBao> arrBienBao) {
        super(context, resource,arrBienBao);
        this.context=context;
        this.arrBienBao=arrBienBao;
        this.resource=resource;
        this.data_filter.addAll(arrBienBao);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(resource, null);
            holder.tvTenItem=convertView.findViewById(R.id.ITBB_tvTenBien);
            holder.tvNoiDung=convertView.findViewById(R.id.ITBB_tvNoiDung);
            holder.ivHinhAnh = convertView.findViewById(R.id.ITBB_ivBienBao);
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder) convertView.getTag();
        final BienBao item=arrBienBao.get(position);
        Resources resources = context.getResources();
        holder.tvTenItem.setText(getTenItem.getTenItem(context,item.getMaBienBao(), item.getLoaiBienBao(), item.getTenBienBao()));
        holder.tvNoiDung.setText(item.getNoiDung());
        holder.ivHinhAnh.setImageResource(resources.getIdentifier(item.getThumb(),"drawable",context.getPackageName()));
        return convertView;
    }

    public class ViewHolder{
        TextView tvTenItem;
        TextView tvNoiDung;
        ImageView ivHinhAnh;
    }

    public void filter(String key){
        arrBienBao.clear();
        if(key.length()==0)
            arrBienBao.addAll(data_filter);
        else
            for (BienBao item:data_filter){
                key = removeAccent(key);
                String value= getTenItem.getTenItem(context,item.getMaBienBao(), item.getLoaiBienBao(), item.getTenBienBao());
                value = removeAccent(value);
                if (value.contains(key))
                    arrBienBao.add(item);
            }
        notifyDataSetChanged();
    }


    public String removeAccent(String s) {
            s = s.toLowerCase(Locale.ROOT);
            String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
    }


}