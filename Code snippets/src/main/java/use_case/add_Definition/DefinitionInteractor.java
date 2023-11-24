package main.java.use_case.add_Definition;

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

        if (term == "" && definition == ""){
            definitionPresenter.prepareFailView("Please highlight some text to mark as a definition");
        } else if (definition == ""){
            definitionPresenter.prepareFailView("Please enter the definition for this term");
        } else if (term == ""){
            definitionPresenter.prepareFailView("Please highlight the format <term>:<definition>");
        } else{
            definitionPresenter.prepareSuccessView();
        }

    }
}
