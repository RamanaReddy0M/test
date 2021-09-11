package com.ramana.test.dao;

import com.ramana.test.model.Question;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Question save(Question Question) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(Question);
        return Question;
    }

    @Override
    @Transactional
    public Question get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Question.class, id);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        if(currentSession.get(Question.class,id) != null)
            currentSession.delete(id);
         }

    @Override
    @Transactional
    public Question update(long id, Question Question) {
        Session currentSession = entityManager.unwrap(Session.class);
        Question q = currentSession.load(Question.class, id);
        q.setQuestion(Question.getQuestion());
        q.setOptions(Question.getOptions());
        q.setAnswer(Question.getAnswer());
        currentSession.update(q);
        return Question;
    }

    @Override
    @Transactional
    public List<Question> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.
                createNativeQuery("select * from question", Question.class)
                .getResultList();
    }
}
