package com.example.thibanglai.model;

import android.content.res.Resources;

import java.io.Serializable;

public class BienBao implements Serializable {
    private int loaiBienBao;
    private String maBienBao;
    private String tenBienBao;
    private String noiDung;
    private String thumb;
    private String hashtag;

    public BienBao() {
    }

    public BienBao(int loaiBienBao, String maBienBao, String tenBienBao, String noiDung,String thumb,String hashtag) {
        this.loaiBienBao = loaiBienBao;
        this.maBienBao = maBienBao;
        this.tenBienBao = tenBienBao;
        this.noiDung = noiDung;
        this.thumb = thumb;
        this.hashtag= hashtag;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String hinhAnh) {
        this.thumb = hinhAnh;
    }

    public String getLoaiBienBao() {
        if(loaiBienBao==1){
            return "báo cấm";
        }
        if(loaiBienBao==2){
            return "hiệu lệnh";
        }
        if(loaiBienBao==3){
            return "chỉ dẫn";
        }
        if(loaiBienBao==4){
            return "báo nguy hiểm";
        }
        return "";
    }

    public void setLoaiBienBao(int loaiBienBao) {
        this.loaiBienBao = loaiBienBao;
    }

    public String getMaBienBao() {
        return maBienBao;
    }

    public void setMaBienBao(String maBienBao) {
        this.maBienBao = maBienBao;
    }

    public String getTenBienBao() {
        return tenBienBao;
    }

    public void setTenBienBao(String tenBienBao) {
        this.tenBienBao = tenBienBao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}