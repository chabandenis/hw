package ru.otus.ex01.domain;

public class Answer {
    String text;
    boolean isCorrect;
    int position;

    public Answer(String text, boolean isCorrect, int position) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
