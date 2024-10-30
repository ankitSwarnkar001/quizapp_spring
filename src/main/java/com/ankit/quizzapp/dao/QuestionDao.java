package com.ankit.quizzapp.dao;

import com.ankit.quizzapp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> { // class of table , prim key type

    List<Question> findByCategory(String category);

    @Query(value = "select * from question where q.category =:category order by random() limit=:numQ ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
}
