package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswerDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    private final AnswerDAO answerDAO;

    @Autowired
    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public List<AnswerDTO> getAnswersById(int questionID) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        answerDAO.selectAllById(questionID).forEach(answer ->{
        String text = answer.text();
        LocalDateTime createdDate = answer.dateTime();
        int score = answer.score();
        int id = answer.answerId();
        answerDTOS.add(new AnswerDTO(id, text, createdDate, questionID, score));});

        return answerDTOS;
    }

    public AnswerDTO getAnswerById(int answerId) {
       Answer answer = answerDAO.selectAnswerById(answerId);
        String text = answer.text();
        LocalDateTime createdDate = answer.dateTime();
        int score = answer.score();
        int questionID = answer.questionID();
        return new AnswerDTO(answerId,text,createdDate,questionID,score);
    }

    public void deleteAllByQuestionId(int questionId) {
        answerDAO.deleteAllById(questionId);
    }

    public void deleteAnswerById(int id) {
        answerDAO.deleteAnswerById(id);
    }

    public void addNewAnswer(NewAnswerDTO newAnswerDTO) {
        answerDAO.addNewAnswer(newAnswerDTO);
    }

    public int getNumberOfAnswers() {
        return answerDAO.getNumberOfAnswers();
    }

    public void voteAnswer(int id, int voteValue) { answerDAO.voteAnswer(id, voteValue);}
}
