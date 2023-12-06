package main.java.interface_adapter.quiz;

import main.java.app.Constants;
import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.quiz.QuizOutputBoundary;
import main.java.use_case.quiz.QuizOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.Arrays;


public class QuizPresenter implements QuizOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private final QuizViewModel quizViewModel;

    public QuizPresenter(ViewManagerModel viewManagerModel, QuizViewModel quizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.quizViewModel = quizViewModel;
    }

    @Override
    public void prepareSuccessView(QuizOutputData quizOutputData) {
        QuizState quizState = quizViewModel.getState();

        quizState.setQuestions(quizOutputData.getQuestions());
        quizState.setAnswers(quizOutputData.getAnswers());

        quizViewModel.setState(quizState);
        quizViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(quizViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
