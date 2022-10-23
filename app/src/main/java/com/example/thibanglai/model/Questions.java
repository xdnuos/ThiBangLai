package com.example.thibanglai.model;

import java.io.Serializable;

public class Questions implements Serializable {
    private int maCH,correct_answer; // maCH FK
    private String question_content;
    private String image;
    private String answer1,answer2,answer3,answer4;
    private String answer_des;
    private int question_type;
    private boolean marked,wrong,question_die;//question_die: câu điểm liệt

    public Questions() {
    }

    public Questions(int maCH, String question_content, String image, String option1, String option2, String option3, String option4, int answer, String answer_des, int question_type) {
        this.maCH = maCH;
        this.question_content = question_content;
        this.image = image;
        this.answer1 = option1;
        this.answer2 = option2;
        this.answer3 = option3;
        this.answer4 = option4;
        this.correct_answer = answer;
        this.answer_des = answer_des;
        this.question_type = question_type;
        this.marked = false;
        this.wrong = false;
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

    public String getOption1() {
        return answer1;
    }

    public void setOption1(String option1) {
        this.answer1 = option1;
    }

    public String getOption2() {
        return answer2;
    }

    public void setOption2(String option2) {
        this.answer2 = option2;
    }

    public String getOption3() {
        return answer3;
    }

    public void setOption3(String option3) {
        this.answer3 = option3;
    }

    public String getOption4() {
        return answer4;
    }

    public void setOption4(String option4) {
        this.answer4 = option4;
    }

    public int getAnswer() {
        return correct_answer;
    }

    public void setAnswer(int answer) {
        this.correct_answer = answer;
    }

    public String getAnswer_des() {
        return answer_des;
    }

    public void setAnswer_des(String answer_des) {
        this.answer_des = answer_des;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
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
}
