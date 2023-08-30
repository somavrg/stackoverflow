package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoJdbc implements AnswerDAO {
    private final java.sql.Connection connection;

    public AnswerDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Answer selectAnswerById(int id) {
        String sql = "SELECT id,text,date,score FROM answers WHERE id = ?";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet res = pstmt.executeQuery();
                int answerId = res.getInt("id");
                String text = res.getString("text");
                LocalDateTime dateTime = res.getTimestamp("date").toLocalDateTime();
                int score = res.getInt("score");
                int questionId = res.getInt("question_id");
                return new Answer(answerId, text, dateTime, score, questionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Answer> selectAllById(int id) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT id,text,date,score FROM answers WHERE question_id = ?";
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int answerId = res.getInt("id");
                String text = res.getString("text");
                LocalDateTime dateTime = res.getTimestamp("date").toLocalDateTime();
                int score = res.getInt("score");
                int questionId = res.getInt("question_id");
                Answer answer = new Answer(answerId, text, dateTime, score, questionId);
                answers.add(answer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    @Override
    public void deleteAllById(int id) {
        String sql = "DELETE FROM answers WHERE question_id = ?";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteAnswerById(int id) {
        String sql = "DELETE FROM answers WHERE answers.id = ?";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
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
