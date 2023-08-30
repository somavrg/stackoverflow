package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Answer(int answerId, String text, LocalDateTime dateTime, int score, int questionID) {}
