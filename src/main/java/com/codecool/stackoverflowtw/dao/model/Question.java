package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public class Question {

    private final int questionId;

    private final String title;

    private final String description;

    private final LocalDate date;

    public Question(int questionId, String title, String description, LocalDate date) {
        this.questionId = questionId;
        this.title = title;
        this.description = description;

        this.date = date;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
