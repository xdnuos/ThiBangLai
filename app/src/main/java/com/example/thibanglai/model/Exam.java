package com.example.thibanglai.model;

public class Exam {
    private int maDe;//FK
    private int time;
    private int current_question,num_correct_answer;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCurrent_question() {
        return current_question;
    }

    public void setCurrent_question(int current_question) {
        this.current_question = current_question;
    }

    public int getNum_answer() {
        return num_correct_answer;
    }

    public void setNum_answer(int num_correct_answer) {
        this.num_correct_answer = num_correct_answer;
    }

    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public Exam(int maDe,int time, int current_question, int num_correct_answer) {
        this.maDe = maDe;
        this.time = time;
        this.current_question = current_question;
        this.num_correct_answer = num_correct_answer;
    }

    public Exam() {
    }
}
