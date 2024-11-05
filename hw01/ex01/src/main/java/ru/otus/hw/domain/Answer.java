package ru.otus.hw.domain;

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

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getPosition() {
        return position;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
