package com.example.thibanglai.model;

public class links {
    private int maDe,maCH,choose,id;
    //id FK
    //maDe,maCH UNK
    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public int getMaCH() {
        return maCH;
    }

    public void setMaCH(int maCH) {
        this.maCH = maCH;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public links(int maDe, int maCH, int choose) {
        this.maDe = maDe;
        this.maCH = maCH;
        this.choose = choose;
    }

    public links() {
    }
}
