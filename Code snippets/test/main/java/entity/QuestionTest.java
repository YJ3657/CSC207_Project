package main.java.entity;

import main.java.app.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    private final QuestionFactory questionFactory = new QuestionFactory();
    private Question testQuestion;

    @BeforeEach
    void init(){
        testQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER, Constants.DERIVATIVES_QUES, Constants.DERIVATIVES_DEF);
    }

    @Test
    void setChapterNumber(){
        int oldChapterNumber = testQuestion.getChapterno();
        int newChapterNumber = testQuestion.getChapterno() + 2;
        testQuestion.setChapterno(newChapterNumber);
        assert testQuestion.getChapterno() == oldChapterNumber + 2;
    }

    @Test
    void setAnswer(){
        String oldAnswer = testQuestion.getAnswer();
        String newAnswer = testQuestion.getAnswer() + " changes to answer were made.";
        testQuestion.setAnswer(newAnswer);
        assert testQuestion.getAnswer().equals(oldAnswer + " changes to answer were made.");
    }

    @Test
    void setQuestion(){
        String oldQuestion = testQuestion.getQuestion();
        String newQuestion = testQuestion.getQuestion() + " changes to question were made.";
        testQuestion.setQuestion(newQuestion);
        assert testQuestion.getQuestion().equals(oldQuestion + " changes to question were made.");
    }
}
