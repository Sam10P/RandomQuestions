package com.example.RandomQuestions.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="question")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {

    @Id
    int id;

    @Column(name = "question")
    String question;

    @Column(name = "answer")
    String answer;

}
