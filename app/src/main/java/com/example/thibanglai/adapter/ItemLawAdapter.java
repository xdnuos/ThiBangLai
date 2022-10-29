package com.example.thibanglai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thibanglai.R;
import com.example.thibanglai.model.ItemLaw;
import com.example.thibanglai.ui.LawDetailActivity;

import java.util.List;

public class ItemLawAdapter extends BaseAdapter {

    Context context;
    List<ItemLaw> list;

    public ItemLawAdapter(Context context, List<ItemLaw> list) {
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
        view = layoutInflater.inflate(R.layout.layout_item_law,null);
        TextView tvNamePentally = view.findViewById(R.id.tv_name_pentaly);
        TextView tvMoneyPentally = view.findViewById(R.id.money_pentally);
        tvMoneyPentally.setText(list.get(i).getMoneyPentally());
        tvNamePentally.setText(list.get(i).getNamePentally());
        TextView tvDetail = view.findViewById(R.id.present_detail);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LawDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
