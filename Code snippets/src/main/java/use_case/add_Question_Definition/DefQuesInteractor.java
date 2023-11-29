package main.java.use_case.add_Question_Definition;

import main.java.app.Constants;

import java.util.Set;

public class DefQuesInteractor implements DefQuesInputBoundary {

    public final DefQuesDataAccessInterface defQuesDataAccessInterface;
    public final DefQuesOutputBoundary definitionPresenter;

    public DefQuesInteractor(DefQuesDataAccessInterface defQuesDataAccessInterface, DefQuesOutputBoundary definitionPresenter){
        this.defQuesDataAccessInterface = defQuesDataAccessInterface;
        this.definitionPresenter = definitionPresenter;
    }
    @Override
    public void execute(DefQuesInputData defQuesInputData) {
        String part1 = defQuesInputData.getBeforeSymbol();
        String part2 = defQuesInputData.getAfterSymbol();
        String courseId = defQuesInputData.getCourseId();
        String symbol = defQuesInputData.getSymbol();

        String prompt1 = "question";
        String prompt2 = "answer";
        if (symbol.equals(":")){
            prompt1 = "term";
            prompt2 = "definition";
        }
        DefQuesOutputData defQuesOutputData = new DefQuesOutputData("");

        if (part1.isEmpty() && part2.isEmpty()){
            defQuesOutputData.setMsg("Please highlight some text in the format <" + prompt1 + ">" + symbol + "<" + prompt2 + ">");
            definitionPresenter.prepareFailView(defQuesOutputData);
        } else if (part2.isEmpty()){
            if (prompt1.equals("term")){
                defQuesOutputData.setMsg("Please enter the term for this definition");
                definitionPresenter.prepareFailView(defQuesOutputData);
            } else{
                String msg = replace(true, courseId, part1, part2);
                defQuesOutputData.setMsg(msg);
                definitionPresenter.prepareSuccessView(defQuesOutputData);
                System.out.println("saved");
            }
        } else if (part1.isEmpty()){
            String msg = "Please enter the question for this answer";
            if (prompt1.equals("term")){
                msg = "Please enter the definition for this term";
            }
            defQuesOutputData.setMsg(msg);
            definitionPresenter.prepareFailView(defQuesOutputData);
        } else{
            boolean questionUseCase = prompt1.equals("question");
            String msg = replace(questionUseCase, courseId, part1, part2);
            defQuesOutputData.setMsg(msg);
            definitionPresenter.prepareSuccessView(defQuesOutputData);
            System.out.println("saved");
        }

    }

    private String replace(boolean questionUseCase, String courseId, String part1, String part2){
        Set<String> prevQuesOrDefs;
        String msg = "";
        if (questionUseCase){
            prevQuesOrDefs = defQuesDataAccessInterface.getQuestionQuestions(Constants.CHAPTERNO_PLACEHOLDER, courseId);
            if (prevQuesOrDefs.contains(part1)){
                msg = "The answer for \"" + part1 + "\" has been updated from \"" + defQuesDataAccessInterface.getAnswerOnly(part1, courseId) + "\" to \"" + part2 + "\".";
            }
            defQuesDataAccessInterface.saveQuestion(part1, part2, Constants.CHAPTERNO_PLACEHOLDER, courseId);
        } else{
            prevQuesOrDefs = defQuesDataAccessInterface.getDefinitionTerms(Constants.CHAPTERNO_PLACEHOLDER, courseId);
            if (prevQuesOrDefs.contains(part1)){
                msg = "The definition for \"" + part1 + "\" has been updated from \"" + defQuesDataAccessInterface.getDefinitionOnly(part1, courseId) + "\" to \"" + part2 + "\".";
            }
            defQuesDataAccessInterface.saveDefinition(part1, part2, Constants.CHAPTERNO_PLACEHOLDER, courseId);
        }
        return msg;
    }
}
