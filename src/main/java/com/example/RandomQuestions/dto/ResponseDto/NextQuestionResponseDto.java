package com.example.RandomQuestions.dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NextQuestionResponseDto {

    String correctAnswer;

    QuestionResponseDto nextQuestion;
}
