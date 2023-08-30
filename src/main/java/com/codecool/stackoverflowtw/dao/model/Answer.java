package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public record Answer(int answerId, String text, LocalDate date,int score,int questionID) {}
