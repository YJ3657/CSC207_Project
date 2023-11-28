package main.java.use_case.add_Question_Definition;

import java.util.Set;

public interface DefQuesDataAccessInterface {

    Set<String> getDefinitionTerms(int chapterNumber, String courseId);

    void saveDefinition(String term, String definition, int chapterNumber, String courseId);

    String getDefinitionOnly(String term, String courseId);

    Set<String> getQuestionQuestions(int chapterNumber, String courseId);

    void saveQuestion(String question, String answer, int chapterNumber, String courseId);

    String getAnswerOnly(String question, String courseId);

}
