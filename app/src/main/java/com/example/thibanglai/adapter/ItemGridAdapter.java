package com.example.thibanglai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thibanglai.R;
import com.example.thibanglai.model.ItemGrid;

import java.util.List;

public class ItemGridAdapter extends BaseAdapter {

    Context context;
    List<ItemGrid> list;

    public ItemGridAdapter(Context context, List<ItemGrid> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.layout_item_grid,null);
        TextView textView = view.findViewById(R.id.tv_name_item_grid);
        ImageView img = view.findViewById(R.id.img_item_grid);
        ItemGrid itemGrid = list.get(i);
        textView.setText(itemGrid.getNameItem());
        img.setImageResource(itemGrid.getResource());
        return view;
    }
}
