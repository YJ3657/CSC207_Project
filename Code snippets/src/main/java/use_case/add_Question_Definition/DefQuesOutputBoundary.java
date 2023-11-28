package main.java.use_case.add_Question_Definition;

public interface DefQuesOutputBoundary {
    void prepareSuccessView(DefQuesOutputData defQuesOutputData);

    void prepareFailView(DefQuesOutputData defQuesOutputData);
}
