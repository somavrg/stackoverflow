package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;

public class QuestionsDaoJdbc implements QuestionsDAO {
    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }

    @Override
    public void selectAll() {

    }

    @Override
    public void selectById(int id) {

    }

    @Override
    public void deleteQuestionById(int id) {

    }

    @Override
    public void addNewQuestion(NewQuestionDTO question) {

    }
}
