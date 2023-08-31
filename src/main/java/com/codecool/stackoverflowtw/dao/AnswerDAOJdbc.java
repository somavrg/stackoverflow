package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.database.JdbcConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AnswerDAOJdbc implements AnswerDAO {
    private final JdbcConnection jdbcConnection;


    public AnswerDAOJdbc(JdbcConnection connection) {
        this.jdbcConnection = connection;
    }

    @Override
    public Answer selectAnswerById(int id) {
        String sql = "SELECT id,text,date,score FROM answers WHERE id = ?";

        try (Connection conn = jdbcConnection.getConnection()) {
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
        String sql = "SELECT id,text,date,score,question_id FROM answers WHERE question_id = ?";
        try (Connection conn = jdbcConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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

        try (Connection conn = jdbcConnection.getConnection()) {
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

        try (Connection conn = jdbcConnection.getConnection()) {
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

        try (Connection conn = jdbcConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, answerDTO.text());
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(3, answerDTO.questionID());
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int getNumberOfAnswers() {
        int numberOfAnswers = 0;
        String sql = "SELECT id FROM answers";

        try (Connection conn = jdbcConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int answerId = res.getInt("id");
                numberOfAnswers++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return numberOfAnswers;
    }

    @Override
    public void voteAnswer(int id, int voteValue) {
        String sql = "UPDATE answers SET score = score + voteValue";

        try (Connection conn = jdbcConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int questionId = res.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
