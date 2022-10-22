package com.example.thibanglai.model;

import java.io.Serializable;

public class Questions implements Serializable {
    private int id;
    private String question_content;
    private String image;
    private String option1,option2,option3,option4;
    private String answer,answer_des;
    private int question_type;
    private boolean marked,wrong;

    public Questions() {
    }

    public Questions(int id, String question_content, String image, String option1, String option2, String option3, String option4, String answer, String answer_des, int question_type) {
        this.id = id;
        this.question_content = question_content;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
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
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
}
