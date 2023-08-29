package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.database.Connection;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private final Connection connection;

    public QuestionsDaoJdbc(Connection connection) {
        this.connection = connection;
    }

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
