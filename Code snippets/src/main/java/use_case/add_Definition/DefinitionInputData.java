package main.java.use_case.add_Definition;

public class DefinitionInputData {
    final private String term;
    final private String definition;
    private final String courseId;

    public DefinitionInputData(String term, String definition, String courseId){
        this.term = term;
        this.definition = definition;
        this.courseId = courseId;

    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public String getCourseId() {return courseId;}
}
