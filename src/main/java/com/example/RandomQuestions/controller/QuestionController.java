package com.example.RandomQuestions.controller;


import com.example.RandomQuestions.dto.RequestDto.QuestionRequestDto;
import com.example.RandomQuestions.dto.ResponseDto.NextQuestionResponseDto;
import com.example.RandomQuestions.dto.ResponseDto.QuestionResponseDto;
import com.example.RandomQuestions.exception.QuestionNotFoundException;
import com.example.RandomQuestions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/fetch")
    public ResponseEntity fetchQuestions() {

        String ans = questionService.fetchRandomQuestions();
        return new ResponseEntity(ans, HttpStatus.ACCEPTED);
    }

    @GetMapping("/play/id/{id}")
    public ResponseEntity playQuestion(@PathVariable int id){

        try{
            QuestionResponseDto questionResponseDto = questionService.playQuestion(id);
            return new ResponseEntity(questionResponseDto, HttpStatus.FOUND);
        } catch (QuestionNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/next")
    public ResponseEntity nextQuestion(@RequestBody QuestionRequestDto questionRequestDto){

        try {
            NextQuestionResponseDto nextQuestionResponseDto = questionService.nextQuestion(questionRequestDto);
            return new ResponseEntity(nextQuestionResponseDto, HttpStatus.FOUND);
        } catch (QuestionNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
