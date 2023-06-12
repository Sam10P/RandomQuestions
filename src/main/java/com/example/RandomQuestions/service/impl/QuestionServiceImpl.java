package com.example.RandomQuestions.service.impl;


import com.example.RandomQuestions.dto.RequestDto.QuestionRequestDto;
import com.example.RandomQuestions.dto.ResponseDto.NextQuestionResponseDto;
import com.example.RandomQuestions.dto.ResponseDto.QuestionResponseDto;
import com.example.RandomQuestions.exception.QuestionNotFoundException;
import com.example.RandomQuestions.model.Question;
import com.example.RandomQuestions.repository.QuestionRepository;
import com.example.RandomQuestions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    @Override
    public String fetchRandomQuestions() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jservice.io/api/random";

        for(int i=0; i<5; i++){
            Question[] question = restTemplate.getForObject(url, Question[].class);
            Question newQuestion = new Question();
            newQuestion.setId(question[0].getId());
            newQuestion.setQuestion(question[0].getQuestion());
            newQuestion.setAnswer(question[0].getAnswer());
            questionRepository.save(newQuestion);
        }

        return "Questions has been added successfully";
    }

    @Override
    public QuestionResponseDto playQuestion(int id) throws QuestionNotFoundException {

        Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isEmpty()){
            throw new QuestionNotFoundException("Question does not exist!!!");
        }
        Question question = questionOptional.get();

        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .question_id(question.getId())
                .question(question.getQuestion())
                .build();

        return questionResponseDto;
    }

    @Override
    public NextQuestionResponseDto nextQuestion(QuestionRequestDto questionRequestDto) throws QuestionNotFoundException {

        Optional<Question> questionOptional = questionRepository.findById(questionRequestDto.getId());
        if(questionOptional.isEmpty()){
            throw new QuestionNotFoundException("Question does not exist!!!");
        }
        Question question = questionOptional.get();

        String answer = question.getAnswer();

        Question nextQuestion = new Question();
        List<Question> questions = questionRepository.findAll();

        for(int i=0; i<questions.size(); i++){
//            if(i == questions.size()-1){
//                nextQuestion = questions.get(0);
//            }
            if(questions.get(i).getId() == question.getId() && i < questions.size()-1){
                nextQuestion = questions.get(i+1);
            }
            if(i == questions.size()-1){
                nextQuestion = questions.get(0);
            }
        }

        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .question_id(nextQuestion.getId())
                .question(nextQuestion.getQuestion())
                .build();

        NextQuestionResponseDto nextQuestionResponseDto = NextQuestionResponseDto.builder()
                .correctAnswer(answer)
                .nextQuestion(questionResponseDto)
                .build();

        return nextQuestionResponseDto;
    }
}
