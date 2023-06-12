package com.example.RandomQuestions.service;

import com.example.RandomQuestions.dto.RequestDto.QuestionRequestDto;
import com.example.RandomQuestions.dto.ResponseDto.NextQuestionResponseDto;
import com.example.RandomQuestions.dto.ResponseDto.QuestionResponseDto;
import com.example.RandomQuestions.exception.QuestionNotFoundException;
import com.example.RandomQuestions.model.Question;

import java.util.List;

public interface QuestionService {

    public String fetchRandomQuestions();

    public QuestionResponseDto playQuestion(int id) throws QuestionNotFoundException;

    public NextQuestionResponseDto nextQuestion(QuestionRequestDto questionRequestDto) throws QuestionNotFoundException;
}
