package com.stucoursesys.controller;

import com.stucoursesys.common.ApiResponse;
import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Student;
import com.stucoursesys.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final ScoreService scoreService;

    public StudentController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public ApiResponse<List<Student>> listStudents() {
        return ApiResponse.ok(scoreService.listStudents());
    }

    @GetMapping("/{studentNo}/scores")
    public ApiResponse<List<ScoreView>> listScores(
            @PathVariable("studentNo") String studentNo,
            @RequestParam(value = "courseId", required = false) String courseId,
            @RequestParam(value = "minScore", required = false) Integer minScore) {
        return ApiResponse.ok(scoreService.listScoresByStudentNo(studentNo, courseId, minScore));
    }
}
