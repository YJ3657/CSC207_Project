package main.java.entity;

public class QuestionFactory {
    public Question create( int chapterno, String question, String answer) {
        return new Question(chapterno, question, answer);
    }
}
