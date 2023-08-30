package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Question(int questionId, String title, String description, LocalDateTime dateTime, int score) {
}
