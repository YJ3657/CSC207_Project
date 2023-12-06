package main.java.use_case.quiz;

import main.java.app.Constants;
import main.java.data_access.ChatGPTDataAccessInterface;
import main.java.data_access.ChatGptDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class QuizInteractor implements QuizInputBoundary{
    QuizDataAccessInterface quizDAO;

    ChatGPTDataAccessInterface chatGPTDAO;
    QuizOutputBoundary quizPresenter;
    public QuizInteractor(QuizDataAccessInterface quizDAO, QuizOutputBoundary quizPresenter, ChatGPTDataAccessInterface chatGPTDAO) {
        this.quizDAO = quizDAO;
        this.quizPresenter = quizPresenter;
        this.chatGPTDAO = chatGPTDAO;
    }
    public void execute(QuizInputData quizInputData) {
        String courseId = quizInputData.getCourseId();
        ArrayList<String> questions = quizDAO.getQuizQuestions(courseId);
        ArrayList<String> answers = quizDAO.getAnswers(courseId);

        assert questions.size() == answers.size();
        List<Integer> selectedIndices = buildQuiz(questions);
        ArrayList<String> selectedQuestions = new ArrayList<>(), selectedAnswers = new ArrayList<>();

        for (Integer i: selectedIndices){
            selectedQuestions.add(questions.get(i));
            selectedAnswers.add(answers.get(i));
        }

        String[] lastQuestion = chatGPTQuestion(questions, answers);
        selectedQuestions.add(lastQuestion[0]);
        selectedAnswers.add(lastQuestion[1]);

        QuizOutputData quizOutputData = new QuizOutputData(selectedQuestions, selectedAnswers);

        if (!quizOutputData.answers.isEmpty()) {
            quizPresenter.prepareSuccessView(quizOutputData);
        } else {
            quizPresenter.prepareFailView(Constants.QUIZ_ERROR);
        }
    }

    private String[] chatGPTQuestion(ArrayList<String> questions, ArrayList<String> answers){
        String prompt = "Create ONLY ONE summary question under 30 words, along with an answer, based on the provided questions: ";
        // each definition can be thought as a question for chatgpt purposes
        for (int i = 0; i < questions.size(); i++){
            prompt += questions.get(i) + "?" + answers.get(i) + "  ";
        }
        String response = chatGPTDAO.execute(prompt);
        int breakIndex = response.indexOf("?");
        String question = response.substring(0,breakIndex+1);
        String answer = response.substring(breakIndex+3); // skips over the "\n" in the response
        return new String[]{question, answer};
    }

    private List<Integer> buildQuiz(ArrayList<String> questions){
        int numQuestions = questions.size();
        List<Integer> questionNumbers = new ArrayList<Integer>();
        for (int i = 0; i < numQuestions; i++){
            questionNumbers.add(i);
        }

        List<Integer> selectedQuestions = new ArrayList<>();

        if (numQuestions <= 5){
            return questionNumbers;
        } else{
            Random random = new Random();
            while (selectedQuestions.size() < 5){
                int randomIndex = random.nextInt(questionNumbers.size());
                selectedQuestions.add(questionNumbers.get(randomIndex));
                questionNumbers.remove(randomIndex);
            }
        }
        return selectedQuestions;
    }
}
