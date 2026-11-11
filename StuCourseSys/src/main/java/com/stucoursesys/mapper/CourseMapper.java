package com.stucoursesys.mapper;

import com.stucoursesys.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    List<Course> findAll();

    Course findByCourseId(@Param("courseId") String courseId);
}
