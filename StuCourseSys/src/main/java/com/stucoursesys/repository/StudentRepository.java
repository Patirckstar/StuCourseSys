package com.stucoursesys.repository;

import com.stucoursesys.entity.Course;
import com.stucoursesys.entity.Score;
import com.stucoursesys.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student("2023010001", "张明", "计算机2301"),
            new Student("2023010002", "李婷", "计算机2301"),
            new Student("2023010003", "王浩", "计算机2301"),
            new Student("2023010004", "陈静", "计算机2302"),
            new Student("2023010005", "刘洋", "计算机2302")
    ));

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course("C001", "Java EE 程序设计", 3.0),
            new Course("C002", "数据库原理", 3.5),
            new Course("C003", "计算机网络", 2.5)
    ));

    private final List<Score> scores = new ArrayList<>(List.of(
            new Score("2023010001", "C001", 92),
            new Score("2023010001", "C002", 85),
            new Score("2023010001", "C003", 78),
            new Score("2023010002", "C001", 88),
            new Score("2023010002", "C002", 91),
            new Score("2023010002", "C003", 86),
            new Score("2023010003", "C001", 74),
            new Score("2023010003", "C002", 68),
            new Score("2023010003", "C003", 80),
            new Score("2023010004", "C001", 95),
            new Score("2023010004", "C002", 89),
            new Score("2023010004", "C003", 92),
            new Score("2023010005", "C001", 61),
            new Score("2023010005", "C002", 72),
            new Score("2023010005", "C003", 65)
    ));

    public List<Student> findAll() {
        List<Student> result = new ArrayList<>(students);
        result.sort(Comparator.comparing(Student::getStudentNo));
        return result;
    }

    public List<Course> findAllCourses() {
        List<Course> result = new ArrayList<>(courses);
        result.sort(Comparator.comparing(Course::getCourseId));
        return result;
    }

    public Student findByStudentNo(String studentNo) {
        return students.stream()
                .filter(student -> student.getStudentNo().equals(studentNo))
                .findFirst()
                .orElse(null);
    }

    public Course findCourseByCourseId(String courseId) {
        return courses.stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

    public List<Score> findScoresByStudentNo(String studentNo) {
        return scores.stream()
                .filter(score -> score.getStudentNo().equals(studentNo))
                .toList();
    }
}
