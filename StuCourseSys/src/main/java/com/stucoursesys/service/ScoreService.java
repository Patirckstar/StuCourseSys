package com.stucoursesys.service;

import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Course;
import com.stucoursesys.entity.Score;
import com.stucoursesys.entity.Student;
import com.stucoursesys.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    private final StudentRepository studentRepository;

    public ScoreService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public List<Course> listCourses() {
        return studentRepository.findAllCourses();
    }

    public List<ScoreView> listScoresByStudentNo(String studentNo) {
        List<ScoreView> result = new ArrayList<>();
        for (Score score : studentRepository.findScoresByStudentNo(studentNo)) {
            Course course = studentRepository.findCourseByCourseId(score.getCourseId());
            if (course != null) {
                result.add(new ScoreView(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getCredit(),
                        score.getScore()
                ));
            }
        }
        return result;
    }
}
