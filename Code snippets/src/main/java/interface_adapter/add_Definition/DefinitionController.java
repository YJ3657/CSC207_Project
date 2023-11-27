package main.java.interface_adapter.add_Definition;

import main.java.use_case.add_Definition.DefinitionInputBoundary;
import main.java.use_case.add_Definition.DefinitionInputData;
import main.java.use_case.add_Definition.DefinitionInteractor;

public class DefinitionController {
    private final DefinitionInputBoundary definitionInteractor;

    public DefinitionController(DefinitionInputBoundary definitionInputBoundary){
        this.definitionInteractor = definitionInputBoundary;
    }

    public void execute(String term, String definition, String courseId){
        DefinitionInputData definitionInputData = new DefinitionInputData(term, definition, courseId);
        definitionInteractor.execute(definitionInputData);
    }

}
