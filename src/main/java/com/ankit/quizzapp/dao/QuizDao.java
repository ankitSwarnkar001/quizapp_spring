package com.ankit.quizzapp.dao;

import com.ankit.quizzapp.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {// class of entity ya name of table, prim key type
}
