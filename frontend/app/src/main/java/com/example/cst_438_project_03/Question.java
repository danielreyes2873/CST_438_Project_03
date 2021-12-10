package com.example.cst_438_project_03;

public class Question {
    private String question;

    private String answer;

    private String quizname;

    public Question(String question, String answer, String quizName) {
        this.question = question;
        this.answer = answer;
        this.quizname = quizName;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuizName() {
        return quizname;
    }
}
