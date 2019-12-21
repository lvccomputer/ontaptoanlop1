package com.dev.lvc.math1.models;

import java.util.ArrayList;

public class Answer {
    public  String[] answer = {"A","B","C","D"};
    private ArrayList<String> answers;
    private boolean result;
    private boolean isSelect;


    public Answer() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
