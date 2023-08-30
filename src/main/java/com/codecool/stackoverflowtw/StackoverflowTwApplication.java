package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswerDAO;
import com.codecool.stackoverflowtw.dao.AnswerDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.jdbcConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;

@SpringBootApplication
public class StackoverflowTwApplication {
    private static final String USER_NAME = "USER_NAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String DATABASE_NAME = "DATABASE_NAME";


    public static void main(String[] args) {

        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    private static Connection getConnection() {
        String dbName = System.getenv(DATABASE_NAME);
        String userName = System.getenv(USER_NAME);
        String password = System.getenv(PASSWORD);
        String URL = "jdbc:postgresql://localhost:5432/" + dbName;

        jdbcConnection connection = new jdbcConnection(dbName, userName, password, URL);
        return connection.getConnection();
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        Connection databaseConnection = getConnection();

        return new QuestionsDaoJdbc(databaseConnection);
    }

    @Bean
    public AnswerDAO answerDAO() {
        Connection databaseConnection = getConnection();
        return new AnswerDaoJdbc(databaseConnection);
    }
}
