package com.ankit.quizzapp.controller;

import com.ankit.quizzapp.entities.QuestionWrappper;
import com.ankit.quizzapp.entities.Response;
import com.ankit.quizzapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title){
       return quizService.createQuiz(category,numQ,title);
    }

    // To fetch the data from backend
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrappper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestions(id);
    }

    // calculate the result
    // a post request having question id and response
    @PostMapping("submit/{id")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }







}
