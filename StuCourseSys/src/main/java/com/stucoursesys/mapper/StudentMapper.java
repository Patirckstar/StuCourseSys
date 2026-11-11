package com.stucoursesys.mapper;

import com.stucoursesys.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    List<Student> findAll();

    Student findByStudentNo(@Param("studentNo") String studentNo);
}
