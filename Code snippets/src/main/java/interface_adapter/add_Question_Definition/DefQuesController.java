package main.java.interface_adapter.add_Question_Definition;

import main.java.use_case.add_Question_Definition.DefQuesInputBoundary;
import main.java.use_case.add_Question_Definition.DefQuesInputData;

public class DefQuesController {
    private final DefQuesInputBoundary definitionInteractor;

    public DefQuesController(DefQuesInputBoundary defQuesInputBoundary){
        this.definitionInteractor = defQuesInputBoundary;
    }

    public void execute(String term, String definition, String courseId, String symbol){
        DefQuesInputData defQuesInputData = new DefQuesInputData(term, definition, courseId, symbol);
        definitionInteractor.execute(defQuesInputData);
    }

}
