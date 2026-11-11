package com.stucoursesys.service;

import com.stucoursesys.common.ErrorCode;
import com.stucoursesys.dto.CourseStatistics;
import com.stucoursesys.dto.ScoreCreateRequestDTO;
import com.stucoursesys.dto.ScoreUpdateRequestDTO;
import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Course;
import com.stucoursesys.entity.Score;
import com.stucoursesys.entity.Student;
import com.stucoursesys.exception.BusinessException;
import com.stucoursesys.mapper.CourseMapper;
import com.stucoursesys.mapper.ScoreMapper;
import com.stucoursesys.mapper.StudentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    private final StudentMapper studentMapper;

    private final CourseMapper courseMapper;

    private final ScoreMapper scoreMapper;

    public ScoreService(StudentMapper studentMapper, CourseMapper courseMapper, ScoreMapper scoreMapper) {
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.scoreMapper = scoreMapper;
    }

    public List<Student> listStudents() {
        return studentMapper.findAll();
    }

    public List<Course> listCourses() {
        return courseMapper.findAll();
    }

    public List<ScoreView> listScoresByStudentNo(String studentNo, String courseId, Integer minScore) {
        return scoreMapper.findByStudentNo(studentNo, courseId, minScore);
    }

    public CourseStatistics statistics(String courseId) {
        if (courseMapper.findByCourseId(courseId) == null) {
            throw new BusinessException(ErrorCode.COURSE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_COURSE_NOT_FOUND);
        }
        return scoreMapper.statistics(courseId);
    }

    public Score create(ScoreCreateRequestDTO request) {
        if (studentMapper.findByStudentNo(request.getStudentNo()) == null) {
            throw new BusinessException(ErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_STUDENT_NOT_FOUND);
        }
        if (courseMapper.findByCourseId(request.getCourseId()) == null) {
            throw new BusinessException(ErrorCode.COURSE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_COURSE_NOT_FOUND);
        }
        if (scoreMapper.countByStudentAndCourse(request.getStudentNo(), request.getCourseId()) > 0) {
            throw new BusinessException(ErrorCode.DUPLICATE_SCORE, HttpStatus.CONFLICT, ErrorCode.MSG_DUPLICATE_SCORE);
        }
        Score score = new Score();
        score.setStudentNo(request.getStudentNo());
        score.setCourseId(request.getCourseId());
        score.setScore(request.getScore());
        scoreMapper.insert(score);
        return score;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreate(List<ScoreCreateRequestDTO> requests) {
        List<Long> ids = new ArrayList<>();
        for (ScoreCreateRequestDTO request : requests) {
            Score created = create(request);
            ids.add(created.getId());
        }
        return ids;
    }

    public Score update(long id, ScoreUpdateRequestDTO request) {
        if (scoreMapper.updateScore(id, request.getScore()) == 0) {
            throw new BusinessException(ErrorCode.SCORE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_SCORE_NOT_FOUND);
        }
        return scoreMapper.findById(id);
    }

    public void delete(long id) {
        if (scoreMapper.deleteById(id) == 0) {
            throw new BusinessException(ErrorCode.SCORE_NOT_FOUND, HttpStatus.NOT_FOUND, ErrorCode.MSG_SCORE_NOT_FOUND);
        }
    }
}
