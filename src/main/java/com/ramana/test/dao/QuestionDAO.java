package com.ramana.test.dao;

import com.ramana.test.model.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question>  getAll();
    Question save(Question Question);
    Question get(long id);
    void delete(long id);
    Question update(long id, Question Question);
}
