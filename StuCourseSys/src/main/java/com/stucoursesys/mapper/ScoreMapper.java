package com.stucoursesys.mapper;

import com.stucoursesys.dto.CourseStatistics;
import com.stucoursesys.dto.ScoreView;
import com.stucoursesys.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {

    List<ScoreView> findByStudentNo(@Param("studentNo") String studentNo,
                                    @Param("courseId") String courseId,
                                    @Param("minScore") Integer minScore);

    Score findById(@Param("id") long id);

    int countByStudentAndCourse(@Param("studentNo") String studentNo,
                                @Param("courseId") String courseId);

    int insert(Score score);

    int updateScore(@Param("id") long id, @Param("score") int score);

    int deleteById(@Param("id") long id);

    CourseStatistics statistics(@Param("courseId") String courseId);
}
