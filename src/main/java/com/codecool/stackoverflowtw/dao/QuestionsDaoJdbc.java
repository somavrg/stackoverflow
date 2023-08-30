package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private final java.sql.Connection connection;

    public QuestionsDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }

    @Override
    public List<Question> selectAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT id, title, description, date, score FROM questions";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int questionId = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                LocalDateTime dateTime = res.getTimestamp("date").toLocalDateTime();
                int score = res.getInt("score");
                Question question = new Question(questionId, title, description, dateTime, score);

                questions.add(question);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return questions;
    }


    @Override
    public Question selectById(int id) {
        String sql = "SELECT id, title, description, date FROM questions WHERE id= ?";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                int questionId = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                LocalDateTime dateTime = res.getTimestamp("date").toLocalDateTime();
                int score = res.getInt("score");

                return new Question(questionId, title, description, dateTime, score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean deleteQuestionById(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);


                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void addNewQuestion(NewQuestionDTO question) {
        String sql = "INSERT INTO questions (title, description, date) VALUES (?, ?, ?)";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, question.title());
                pstmt.setString(2, question.description());
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
