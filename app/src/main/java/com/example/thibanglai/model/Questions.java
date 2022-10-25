package com.example.thibanglai.model;

import java.io.Serializable;

public class Questions implements Serializable {
    private int question_id;
    private String question_content;
    private String image;
    private String answer1,answer2,answer3,answer4;
    private int correct_answer;
    private String answer_des;
    private boolean marked,wrong,question_die;//question_die: câu điểm liệt
    private int choose; // maCH FK
    private int maDe;

    public Questions() {
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public Questions(int question_id,String question_content, String image, String answer1, String answer2, String answer3, String answer4, int correct_answer, String answer_des, boolean marked, boolean wrong, boolean question_die, int choose) {
        this.question_content = question_content;
        this.image = image;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct_answer = correct_answer;
        this.answer_des = answer_des;
        this.marked = marked;
        this.wrong = wrong;
        this.question_die = question_die;
        this.choose = choose;
        this.question_id = question_id;
    }

    public int getMaDe() {
        return maDe;
    }

    public void setMaDe(int maDe) {
        this.maDe = maDe;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswer_des() {
        return answer_des;
    }

    public void setAnswer_des(String answer_des) {
        this.answer_des = answer_des;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isWrong() {
        return wrong;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
    }

    public boolean isQuestion_die() {
        return question_die;
    }

    public void setQuestion_die(boolean question_die) {
        this.wrong = question_die;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
