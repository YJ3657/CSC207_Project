package main.java;

import main.java.InMemoryQuizDAO;
import main.java.app.Constants;
import main.java.data_access.ChatGPTDataAccessInterface;
import main.java.data_access.ChatGptDAO;
import main.java.use_case.quiz.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizInteractorTest {

    @Test
    public void SuccessTest() {
        InMemoryQuizDAO quizRepo = new InMemoryQuizDAO();
        quizRepo.setQuestionAnswers();
        QuizInputData quizInputData = new QuizInputData("MAT137");
        ChatGPTDataAccessInterface chatGptDAO = new ChatGptDAO();
        QuizOutputBoundary successPresenter = new QuizOutputBoundary() {
            @Override
            public void prepareSuccessView(QuizOutputData quizOutputData) {
                ArrayList<String> questions = new ArrayList<>();
                questions.add("1) The definition of Limit is:");
                questions.add("2) The definition of Continuity is:");
                assertEquals(questions, quizOutputData.getQuestions().subList(0, 2));
                ArrayList<String> answers = new ArrayList<>(List.of(Constants.LIMIT_DEF, Constants.CONTIUNUITY_DEF));
                assertEquals(answers, quizOutputData.getAnswers().subList(0, 2));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure not supposed to happen");
            }
        };
        QuizInputBoundary interactor = new QuizInteractor(quizRepo, successPresenter, chatGptDAO);
        interactor.execute(quizInputData);
    }

    @Test
    public void FailTest() {
        QuizDataAccessInterface quizRepo = new InMemoryQuizDAO();
        ChatGPTDataAccessInterface chatGptDAO = new ChatGptDAO();
        QuizInputData quizInputData = new QuizInputData("MAT137");
        QuizOutputBoundary failPresenter = new QuizOutputBoundary() {
            @Override
            public void prepareSuccessView(QuizOutputData quizOutputData) {
                fail("SuccessView is not expected to occur");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(Constants.QUIZ_ERROR, error);
            }
        };
        QuizInputBoundary interactor = new QuizInteractor(quizRepo, failPresenter, chatGptDAO);
        interactor.execute(quizInputData);
    }
}
