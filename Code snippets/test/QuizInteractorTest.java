import main.java.app.Constants;
import main.java.data_access.InMemoryQuizDAO;
import main.java.use_case.notes.AddCourseInputData;
import main.java.use_case.notes.AddCourseOutputBoundary;
import main.java.use_case.quiz.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizInteractorTest {

    @Test
    public void SuccessTest() {
        InMemoryQuizDAO quizRepo = new InMemoryQuizDAO();
        quizRepo.setQuestionAnswers();
        QuizInputData quizInputData = new QuizInputData("MAT137");
        QuizOutputBoundary successPresenter = new QuizOutputBoundary() {
            @Override
            public void prepareSuccessView(QuizOutputData quizOutputData) {
                ArrayList<String> questions = new ArrayList<>();
                questions.add("1) The definition of Limit is:");
                questions.add("2) The definition of Continuity is:");
                assertEquals(questions, quizOutputData.getQuestions());
                ArrayList<String> answers = new ArrayList<>(List.of(Constants.LIMIT_DEF, Constants.CONTIUNUITY_DEF));
                assertEquals(answers, quizOutputData.getAnswers());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure not supposed to happen");
            }
        };
//        QuizInputBoundary interactor = new QuizInteractor(quizRepo, successPresenter);
//        interactor.execute(quizInputData);
    }

    @Test
    public void FailTest() {
        QuizDataAccessInterface quizRepo = new InMemoryQuizDAO();
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
//        QuizInputBoundary interactor = new QuizInteractor(quizRepo, failPresenter);
//        interactor.execute(quizInputData);
    }
}
