package com.ta2khu75.quiz.enviroment;

public class FolderEnvironment {
    private FolderEnvironment() {
        throw new IllegalStateException("Environment class");
    }
    public static final String USER_FOLDER = "user";
    public static final String EXAM_FOLDER = "exam";
    public static final String ANSWER_FOLDER = "answer";
}
