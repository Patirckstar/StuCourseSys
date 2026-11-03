package com.stucoursesys.service;

import com.stucoursesys.common.ErrorCode;
import com.stucoursesys.dto.ScoreCreateRequestDTO;
import com.stucoursesys.dto.ScoreUpdateRequestDTO;
import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Course;
import com.stucoursesys.entity.Score;
import com.stucoursesys.entity.Student;
import com.stucoursesys.exception.BusinessException;
import com.stucoursesys.repository.StudentRepository;
import org.springframework.http.HttpStatus;
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

    public Score create(ScoreCreateRequestDTO request) {
//        throw new RuntimeException("临时测试 500，验证后立即删除");

        if (studentRepository.findByStudentNo(request.getStudentNo()) == null) {
            throw new BusinessException(ErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_STUDENT_NOT_FOUND);
        }
        if (studentRepository.findCourseByCourseId(request.getCourseId()) == null) {
            throw new BusinessException(ErrorCode.COURSE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_COURSE_NOT_FOUND);
        }
        if (studentRepository.findScoreByStudentNoAndCourseId(request.getStudentNo(), request.getCourseId()) != null) {
            throw new BusinessException(ErrorCode.DUPLICATE_SCORE, HttpStatus.CONFLICT, ErrorCode.MSG_DUPLICATE_SCORE);
        }
        return studentRepository.save(request.getStudentNo(), request.getCourseId(), request.getScore());
    }

    public Score update(long id, ScoreUpdateRequestDTO request) {
        Score updated = studentRepository.updateScore(id, request.getScore());
        if (updated == null) {
            throw new BusinessException(ErrorCode.SCORE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_SCORE_NOT_FOUND);
        }
        return updated;
    }

    public void delete(long id) {
        if (!studentRepository.deleteScore(id)) {
            throw new BusinessException(ErrorCode.SCORE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_SCORE_NOT_FOUND);
        }
    }
}
