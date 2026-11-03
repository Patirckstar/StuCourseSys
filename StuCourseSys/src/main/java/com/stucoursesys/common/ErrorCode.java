package com.stucoursesys.common;

public final class ErrorCode {

    public static final int VALIDATION_FAILED = 40001;

    public static final int STUDENT_NOT_FOUND = 40401;

    public static final int COURSE_NOT_FOUND = 40402;

    public static final int SCORE_NOT_FOUND = 40403;

    public static final int DUPLICATE_SCORE = 40901;

    public static final int INTERNAL_ERROR = 50000;

    public static final String MSG_VALIDATION_FAILED = "请求参数不合法";

    public static final String MSG_STUDENT_NOT_FOUND = "学生不存在";

    public static final String MSG_COURSE_NOT_FOUND = "课程不存在";

    public static final String MSG_SCORE_NOT_FOUND = "成绩不存在";

    public static final String MSG_DUPLICATE_SCORE = "该学生这门课程已录入成绩";

    public static final String MSG_INTERNAL_ERROR = "服务器内部错误";

    private ErrorCode() {
    }
}
