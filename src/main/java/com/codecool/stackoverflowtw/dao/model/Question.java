package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public record Question(int questionId, String title, String description, LocalDate date) {}
