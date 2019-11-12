package com.dev.lvc.math1.models;

public class History {

    private String lesson;
    private String time;
    private String result;

    public History(String lesson, String time, String result) {
        this.lesson = lesson;
        this.time = time;
        this.result = result;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
