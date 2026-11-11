package com.stucoursesys.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ScoreBatchRequestDTO {

    @NotEmpty(message = "成绩列表不能为空")
    @Size(max = 20, message = "一次最多提交 20 条成绩")
    @Valid
    private List<ScoreCreateRequestDTO> scores;

    public List<ScoreCreateRequestDTO> getScores() {
        return scores;
    }

    public void setScores(List<ScoreCreateRequestDTO> scores) {
        this.scores = scores;
    }
}
