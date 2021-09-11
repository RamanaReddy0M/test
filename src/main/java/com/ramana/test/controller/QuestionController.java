package com.ramana.test.controller;

import com.ramana.test.model.Question;
import com.ramana.test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class QuestionController {

    private static int counter = 0;
    String selected = "";
    private long idForUpdate;
    private Random random = new Random();

    @Autowired
    private QuestionService questionService;

    private Question question = null;

    private List<Question> questions;
    private List<Integer> numbers = new ArrayList<>();
    int max, min = 1, id=0;

    @GetMapping("/")
    public String homePage() {
        questions = questionService.getAll();
        max = questions.size();
        getRandomNumbers(max, max, 1);
        id=0;
        return "homePage";
    }

    @RequestMapping("/form")
    public String showForm(Model model) {
        if(id==max){
            model.addAttribute("score", counter);
            model.addAttribute("total", max);
            counter = 0;
            id=0;
            return "success";
        }
            question = questionService.get(numbers.get(id++));
            model.addAttribute("question", question);
            selected = question.getSelected();
            return "questionForm";

    }

        @PostMapping("/processForm")
        public String result (@ModelAttribute("question") Question q){

        if(q.getSelected()==null || q.getSelected().isEmpty()){
            id--;
            return "redirect:/form";
        }
            if (q.getSelected().equalsIgnoreCase(question.getAnswer())){
                counter++;
            }
            return "redirect:/form";
        }

        @GetMapping("/add")
        public String addQuestion (Model model){
            model.addAttribute("question", new Question());
            return "createQuestion";
        }
        @PostMapping("/save")
        public String saveInDb (@ModelAttribute("question") Question q, Model model){
        model.addAttribute("question", q);
        if(q.getQuestion() == null || q.getAnswer() == null || q.getQuestion().isEmpty() || q.getAnswer().isEmpty()){
            return "createQuestion";
        }
        for (String o: q.getOptions()) {
            if (o.isEmpty() || o == null)
                return "createQuestion";
        }
        questionService.save(q);
            return "redirect:/";
        }

        @RequestMapping("/update")
        public String update(@RequestParam int id, Model model){
        idForUpdate = id;
        model.addAttribute("question", questionService.get(id));
        return "updateQuestion";
        }

        @PostMapping("/updateQuestion")
        public String updateQuestion(@ModelAttribute("question") Question q){
            questionService.update(idForUpdate, q);
            return "redirect:/";
        }

        public void getRandomNumbers (int size, int max, int min) {

            while(numbers.size() < size){
                int n = random.nextInt((max - min) + 1) + min;
                if (!numbers.contains(n)) {
                    numbers.add(n);
                }
            }

        }
    }


