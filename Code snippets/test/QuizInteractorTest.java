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
        ChatGPTDataAccessInterface chatgptDAO = new ChatGptDAO();
        QuizInputData quizInputData = new QuizInputData("MAT137");
        QuizOutputBoundary successPresenter = new QuizOutputBoundary() {
            @Override
            public void prepareSuccessView(QuizOutputData quizOutputData) {
                ArrayList<String> questions = new ArrayList<>();
                questions.add("1) The definition of Limit is:");
                questions.add("2) The definition of Continuity is:");
                List<String> outputQuestions = quizOutputData.getQuestions();
                assert questions.size() == outputQuestions.size() - 1;
                assertEquals(questions, outputQuestions.subList(0, 2));
                ArrayList<String> answers = new ArrayList<>(List.of(Constants.LIMIT_DEF, Constants.CONTIUNUITY_DEF));
                List<String> outputAnswers = quizOutputData.getAnswers();
                assertEquals(answers, outputAnswers.subList(0, 2));
                assert answers.size() == outputAnswers.size() - 1;
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure not supposed to happen");
            }
        };
        QuizInputBoundary interactor = new QuizInteractor(quizRepo, successPresenter, chatgptDAO);
        interactor.execute(quizInputData);
    }

    @Test
    public void FailTest() {
        QuizDataAccessInterface quizRepo = new InMemoryQuizDAO();
        ChatGPTDataAccessInterface chatgptDAO = new ChatGptDAO();
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
        QuizInputBoundary interactor = new QuizInteractor(quizRepo, failPresenter, chatgptDAO);
        interactor.execute(quizInputData);
    }
}
