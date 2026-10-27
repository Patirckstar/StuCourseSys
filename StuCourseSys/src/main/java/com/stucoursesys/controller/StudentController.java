package com.stucoursesys.controller;

import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Student;
import com.stucoursesys.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Student> listStudents() {
        return scoreService.listStudents();
    }

    @GetMapping("/{studentNo}/scores")
    public List<ScoreView> listScores(@PathVariable("studentNo") String studentNo) {
        return scoreService.listScoresByStudentNo(studentNo);
    }
}
