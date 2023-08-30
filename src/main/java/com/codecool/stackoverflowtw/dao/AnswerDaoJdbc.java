package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoJdbc implements  AnswerDAO{
    private final java.sql.Connection connection;

    public AnswerDaoJdbc(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Answer> selectAllById(int id) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT id,text,date,score FROM answers WHERE question_id = ?";
        try (Connection conn = connection;

             PreparedStatement pstmt = conn.prepareStatement(sql)

        ) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int answerId = res.getInt("id");
                String text = res.getString("text");
                LocalDate date = res.getDate("date").toLocalDate();
                int score = res.getInt("score");
                int questionId = res.getInt("question_id");
                Answer answer = new Answer(answerId,text,date,score,questionId);
                answers.add(answer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return answers;
    }

    @Override
    public void deleteAllById(int id) {

    }

    @Override
    public void deleteAnswerById(int id) {


    }

    @Override
    public void addNewAnswer(NewAnswerDTO answerDTO) {

        String sql = "INSERT INTO answers (text,date,question_id) VALUES (?, ?, ?)";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, answerDTO.text());
                pstmt.setString(2, String.valueOf(LocalDate.now()));
                pstmt.setInt(3, answerDTO.questionID());


                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
