package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswerDAO {
    Answer selectAnswerById(int id);
    List<Answer> selectAllById(int id);

    void deleteAllById(int id);

    void deleteAnswerById(int id);

    void addNewAnswer(NewAnswerDTO newAnswerDTO);
    int getNumberOfAnswers();
}
