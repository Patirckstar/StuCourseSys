package com.stucoursesys.dto;

public class ScoreView {

    private String courseId;

    private String courseName;

    private double credit;

    private int score;

    public ScoreView() {
    }

    public ScoreView(String courseId, String courseName, double credit, int score) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.score = score;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
