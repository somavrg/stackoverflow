package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.AnswerDAO;


public class AnswerService {
    private final AnswerDAO answerDAO;

    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }
}
