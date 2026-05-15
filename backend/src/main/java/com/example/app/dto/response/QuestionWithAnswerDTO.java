package com.example.app.dto.response;

import com.example.app.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithAnswerDTO {

    private Question question;

    private String userAnswer;

    private Integer isCorrect;

    private Integer attemptCount;

}