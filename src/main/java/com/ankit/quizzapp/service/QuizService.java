package com.ankit.quizzapp.service;

import com.ankit.quizzapp.dao.QuestionDao;
import com.ankit.quizzapp.dao.QuizDao;
import com.ankit.quizzapp.entities.Question;
import com.ankit.quizzapp.entities.QuestionWrappper;
import com.ankit.quizzapp.entities.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        // create quiz and save to the DB
        // question from database dao questiondao
        List<Question> questions =  questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrappper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz =  quizDao.findById(id);
       List<Question>  questionFromDB =quiz.get().getQuestions();
       List<QuestionWrappper> questionForUser= new ArrayList<>();
       for(Question q : questionFromDB){
           QuestionWrappper qw = new QuestionWrappper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionForUser.add(qw);
       }

       return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }
}
