package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();

    List<Question> selectAll();

    Question selectById(int id);

    boolean deleteQuestionById(int id);

    void addNewQuestion(NewQuestionDTO question);

    int getNumberOfQuestion();
}
