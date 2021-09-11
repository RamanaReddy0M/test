package com.ramana.test.service;

import com.ramana.test.dao.QuestionDAO;
import com.ramana.test.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    public Question save(Question Question){
        return questionDAO.save(Question);
    }

    public Question get(long id){
        return questionDAO.get(id);
    }

    public List<Question> getAll(){
        return questionDAO.getAll();
    }

    public void delete(long id){
        questionDAO.delete(id);
    }

    public Question update(long id, Question q){
        return questionDAO.update(id, q);
    }

}
