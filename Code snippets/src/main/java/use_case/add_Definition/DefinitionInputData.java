package main.java.use_case.add_Definition;

public class DefinitionInputData {
    final private String term;
    final private String definition;

    public DefinitionInputData(String term, String definition){
        this.term = term;
        this.definition = definition;

    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}
