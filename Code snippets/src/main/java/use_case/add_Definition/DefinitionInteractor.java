package main.java.use_case.add_Definition;

import main.java.app.Constants;
import main.java.entity.Definition;

import java.util.List;
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
        String term = definitionInputData.getTerm();
        String definition = definitionInputData.getDefinition();
        String courseId = definitionInputData.getCourseId();

        if (term == "" && definition == ""){
            definitionPresenter.prepareFailView("Please highlight some text in the format <term>:<definition> to mark as a definition");
        } else if (definition == ""){
            definitionPresenter.prepareFailView("Please enter the definition for this term");
        } else if (term == ""){
            definitionPresenter.prepareFailView("Please enter the term for this definition");
        } else{
            Set<String> prevDefinitions = definitionDataAccessInterface.getDefinitionTerms(Constants.CHAPTERNO_PLACEHOLDER, courseId);
            String msg = "";
            if (prevDefinitions.contains(term)){
                msg = "The definition for \"" + term + "\" has been updated from \"" + definitionDataAccessInterface.getDefinition(term, courseId) + "\" to \"" + definition + "\".";
            }
            System.out.println("saved");
            definitionPresenter.prepareSuccessView(msg);
            definitionDataAccessInterface.saveDefinition(term, definition, Constants.CHAPTERNO_PLACEHOLDER, courseId);

        }

    }
}
