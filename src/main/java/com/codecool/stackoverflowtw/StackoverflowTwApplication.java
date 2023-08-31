package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswerDAO;
import com.codecool.stackoverflowtw.dao.AnswerDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.JdbcConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {
    private static final String USER_NAME = "USER_NAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String DATABASE_NAME = "DATABASE_NAME";


    public static void main(String[] args) {

        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    private static JdbcConnection getConnection() {
        String dbName = System.getenv(DATABASE_NAME);
        String userName = System.getenv(USER_NAME);
        String password = System.getenv(PASSWORD);
        String URL = "jdbc:postgresql://localhost:5432/" + dbName;

        JdbcConnection connection = new JdbcConnection(dbName, userName, password, URL);
        return connection;
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        JdbcConnection databaseConnection = getConnection();

        return new QuestionsDaoJdbc(databaseConnection);
    }

    @Bean
    public AnswerDAO answerDAO() {
        JdbcConnection databaseConnection = getConnection();
        return new AnswerDaoJdbc(databaseConnection);
    }
}
