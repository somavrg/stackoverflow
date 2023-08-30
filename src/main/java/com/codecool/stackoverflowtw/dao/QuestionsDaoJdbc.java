package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        String sql = "SELECT id, title, description, date FROM questions";

        try (Connection conn = connection;

             PreparedStatement pstmt = conn.prepareStatement(sql)

        ) {

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int questionId = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                LocalDate date = res.getDate("date").toLocalDate();
                Question question = new Question(questionId, title, description, date);

                questions.add(question);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }


    @Override
    public Question selectById(int id) {
        String sql = "SELECT id, title, description, date FROM questions WHERE id= ?";

        try (java.sql.Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);


            ResultSet res = pstmt.executeQuery();


            if (res.next()) {

                int questionId = res.getInt("id");
                String title = res.getString("title");
                String description = res.getString("description");
                LocalDate date = res.getDate("date").toLocalDate();
                return new Question(questionId, title, description, date);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    @Override
    public void deleteQuestionById(int id) {

    }

    @Override
    public void addNewQuestion(NewQuestionDTO question) {

        String sql = "INSERT INTO questions (title, description,date) VALUES (?, ?, ?)";

        try (Connection conn = connection) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, question.title());
                pstmt.setString(2, question.description());
                pstmt.setString(3, question.description());


                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
        }
    }
}
