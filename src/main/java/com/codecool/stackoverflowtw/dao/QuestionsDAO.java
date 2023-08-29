package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;

public interface QuestionsDAO {
    void sayHi();
    void selectAll();
    void selectById(int id);
    void deleteQuestionById(int id);

    void addNewQuestion(NewQuestionDTO question);
}
