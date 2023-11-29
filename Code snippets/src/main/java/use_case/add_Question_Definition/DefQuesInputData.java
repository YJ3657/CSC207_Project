package main.java.use_case.add_Question_Definition;

public class DefQuesInputData {
    final private String beforeSymbol;

    final private String symbol;
    final private String afterSymbol;
    private final String courseId;

    public DefQuesInputData(String beforeSymbol, String afterSymbol, String courseId, String symbol){
        this.beforeSymbol = beforeSymbol;
        this.afterSymbol = afterSymbol;
        this.courseId = courseId;
        this.symbol = symbol;

    }

    public String getBeforeSymbol() {
        return beforeSymbol;
    }

    public String getAfterSymbol() {
        return afterSymbol;
    }

    public String getCourseId() {return courseId;}

    public String getSymbol(){return symbol;}
}
