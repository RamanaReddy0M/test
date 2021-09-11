package com.ramana.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="question", columnDefinition = "text")
    private String question;

    @Column(name="answer", columnDefinition = "text")
    private String answer;

    @ElementCollection
    @CollectionTable(name = "question_options")
    private List<String> options = new ArrayList<>(4);

    private String selected;

    public Question(){

    }

    //constructor
    public Question(String question, List<String> options, String answer){
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
