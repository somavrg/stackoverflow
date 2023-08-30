package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionsDAO.selectAll().stream()
                .map(question -> {
                    int id = question.questionId();
                    String title = question.title();
                    String description = question.description();
                    LocalDateTime createdDate = question.dateTime();
                    int score = question.score();
                    return new QuestionDTO(id, title, description, createdDate, score);
                }).toList();
    }

    public QuestionDTO getQuestionById(int id) {
        String title = questionsDAO.selectById(id).title();
        String description = questionsDAO.selectById(id).description();
        LocalDateTime createdDate = questionsDAO.selectById(id).dateTime();
        int score = questionsDAO.selectById(id).score();
        return new QuestionDTO(id, title, description, createdDate, score);
    }

    public boolean deleteQuestionById(int id) {
       return questionsDAO.deleteQuestionById(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        questionsDAO.addNewQuestion(question);
        return 0;
    }
}
