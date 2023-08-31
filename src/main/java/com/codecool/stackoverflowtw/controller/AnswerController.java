package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    @PostMapping("/new-answer")
    public boolean addNewAnswer(@RequestBody NewAnswerDTO answerDTO){
        answerService.addNewAnswer(answerDTO);
        return true;
    }

    @GetMapping("/{questionId}/answers")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable int questionId) {
        return answerService.getAnswersById(questionId);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAnswerById(@PathVariable int id) {
        answerService.deleteAnswerById(id);
    }
}
