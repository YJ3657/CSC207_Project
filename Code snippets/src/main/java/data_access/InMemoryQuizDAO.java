package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.Definition;
import main.java.use_case.quiz.QuizDataAccessInterface;

import java.util.*;

public class InMemoryQuizDAO implements QuizDataAccessInterface {
    public Map<String, String> QuestionAnswers = new LinkedHashMap<>();

    public InMemoryQuizDAO() {

    }

    @Override
    public ArrayList<String> getQuizQuestions(String courseId) {
        Set<String> wordSet = QuestionAnswers.keySet();
        ArrayList<String> questions = new ArrayList<>();
        int i = 1;
        for (String word: wordSet) {
            questions.add(String.format("%1d) The definition of %2s is:", i, word));
            i++;
        }
        return questions;
    }

    public void setQuestionAnswers() {
        Definition limit = new Definition(1,"Limit", Constants.LIMIT_DEF);
        Definition continuity = new Definition(1,"Continuity", Constants.CONTIUNUITY_DEF);
        QuestionAnswers.put(limit.getWord(), limit.getDefinition());
        QuestionAnswers.put(continuity.getWord(), continuity.getDefinition());
    }

    @Override
    public ArrayList<String> getAnswers(String courseId) {
        return new ArrayList<>(QuestionAnswers.values());
    }
}
