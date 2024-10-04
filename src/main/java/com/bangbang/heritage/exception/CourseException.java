package com.bangbang.heritage.exception;

public class CourseException extends Exception {

    public CourseException(String message) {
        super(message);
    }

    public static final CourseException COURSE_NOT_FOUND = new CourseException("존재하지 않는 코스입니다.");
}
