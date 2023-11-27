package main.java.use_case.add_Definition;

import main.java.app.Constants;

import java.util.Set;

public class DefinitionInteractor implements DefinitionInputBoundary{

    public final DefinitionDataAccessInterface definitionDataAccessInterface;
    public final DefinitionOutputBoundary definitionPresenter;

    public DefinitionInteractor(DefinitionDataAccessInterface definitionDataAccessInterface, DefinitionOutputBoundary definitionPresenter){
        this.definitionDataAccessInterface = definitionDataAccessInterface;
        this.definitionPresenter = definitionPresenter;
    }
    @Override
    public void execute(DefinitionInputData definitionInputData) {
        String part1 = definitionInputData.getTerm();
        String part2 = definitionInputData.getDefinition();
        String courseId = definitionInputData.getCourseId();
        String symbol = definitionInputData.getSymbol();

        String prompt1 = "question";
        String prompt2 = "answer";
        if (symbol.equals(":")){
            prompt1 = "term";
            prompt2 = "definition";
        }

        if (part1.isEmpty() && part2.isEmpty()){
            definitionPresenter.prepareFailView("Please highlight some text in the format <" + prompt1 + ">" + symbol + "<" + prompt2 + ">");
        } else if (part2.isEmpty()){
            String msg = "Please enter the question for this answer";
            if (prompt1.equals("term")){
                msg = "Please enter the definition for this term";
            }
            definitionPresenter.prepareFailView(msg);
        } else if (part1.isEmpty()){
            if (prompt1.equals("term")){
                definitionPresenter.prepareFailView("Please enter the term for this definition");
            } else{
                String msg = replace(true, courseId, part1, part2);
                definitionPresenter.prepareSuccessView(msg);
                System.out.println("saved");
            }

        } else{
            boolean questionUseCase = prompt1.equals("question");
            String msg = replace(questionUseCase, courseId, part1, part2);
            definitionPresenter.prepareSuccessView(msg);
            System.out.println("saved");
        }

    }

    private String replace(boolean questionUseCase, String courseId, String part1, String part2){
        Set<String> prevQuesOrDefs;
        String msg = "";
        if (questionUseCase){
            prevQuesOrDefs = definitionDataAccessInterface.getQuestionQuestions(Constants.CHAPTERNO_PLACEHOLDER, courseId);
            if (prevQuesOrDefs.contains(part1)){
                msg = "The answer for \"" + part1 + "\" has been updated from \"" + definitionDataAccessInterface.getAnswerOnly(part1, courseId) + "\" to \"" + part2 + "\".";
            }
            definitionDataAccessInterface.saveQuestion(part1, part2, Constants.CHAPTERNO_PLACEHOLDER, courseId);
        } else{
            prevQuesOrDefs = definitionDataAccessInterface.getDefinitionTerms(Constants.CHAPTERNO_PLACEHOLDER, courseId);
            if (prevQuesOrDefs.contains(part1)){
                msg = "The definition for \"" + part1 + "\" has been updated from \"" + definitionDataAccessInterface.getDefinitionOnly(part1, courseId) + "\" to \"" + part2 + "\".";
            }
            definitionDataAccessInterface.saveDefinition(part1, part2, Constants.CHAPTERNO_PLACEHOLDER, courseId);
        }
        return msg;
    }
}
