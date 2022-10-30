package com.example.thibanglai.other;

import android.content.Context;
import android.util.Log;

import com.example.thibanglai.R;

public class FormattingString {
    public String getTenItem(Context context, String maBienBao, String loaiBienBao, String tenBienBao ) {
        if(loaiBienBao.equalsIgnoreCase("adfasdf")){
            return "Vạch kẻ đường: "+tenBienBao;
        }else return context.getResources().getString(R.string.item_bien_bao,loaiBienBao,maBienBao,tenBienBao);
    }
}
