package main.java;

import main.java.data_access.DBDataAccessObject;
import main.java.entity.*;
import main.java.use_case.add_Question_Definition.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DefQuesInteractorTest {
    @Test
    public void SuccessTest() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                fail("Failure not supposed to happen");
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("Before","After", "CSC236", ":");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void SuccessTestDifferentSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                fail("Failure not supposed to happen");
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("Before","After", "CSC236", ";");
        interactor.execute(defQuesInputData);
    }
    @Test
    public void FailTest_EmptyBeforeSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("Please enter the definition for this term");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("","After", "CSC236", ":");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void FailTest_EmptyBeforeSymbol_DifferentSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("Please enter the question for this answer");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("","After", "CSC236", ";");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void FailTest_EmptyAfterSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("Please enter the term for this definition");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("Before","", "CSC236", ":");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void FailTest_EmptyAfterSymbol_DifferentSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("Before","", "CSC236", ";");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void FailTest_EmptyBeforeAndAfterSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("Please highlight some text in the format <term>:<definition>");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("","", "CSC236", ":");
        interactor.execute(defQuesInputData);
    }

    @Test
    public void FailTest_EmptyBeforeAndAfterSymbol_DifferentSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("Please highlight some text in the format <question>;<answer>");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("","", "CSC236", ";");
        interactor.execute(defQuesInputData);
    }
    @Test
    public void FailTest_StructuralInductionBeforeSymbol_EmptyAfterSymbol2_DifferentSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("The answer for \"What’s the structural induction?\" has been updated from \"special type of induction\" to \"\".");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("What’s the structural induction?","", "CSC236", ";");
        interactor.execute(defQuesInputData);
    }
    @Test
    public void SuccessTest_InductionBeforeSymbol() {
        DBDataAccessObject dbDataAccessObject = new DBDataAccessObject(new DefaultUserFactory(), new NotesFactory(),
                new CourseFactory(), new StudentFactory(), new QuestionFactory(), new DefinitionFactory(), new ReminderFactory());
        DefQuesOutputData expectedDefQuesOutputData = new DefQuesOutputData("The definition for \"Induction\" has been updated from \"Induction\" to \"After\".");
        DefQuesOutputBoundary successPresenter = new DefQuesOutputBoundary() {
            @Override
            public void prepareSuccessView(DefQuesOutputData defQuesOutputData) {
                assertEquals(defQuesOutputData.getMsg(), expectedDefQuesOutputData.getMsg());
            }
            @Override
            public void prepareFailView(DefQuesOutputData defQuesOutputData) {
                fail("Failure not supposed to happen");
            }
        };
        DefQuesInputBoundary interactor = new DefQuesInteractor(dbDataAccessObject, successPresenter);
        DefQuesInputData defQuesInputData = new DefQuesInputData("Induction","After", "CSC236", ":");
        interactor.execute(defQuesInputData);
    }
}
