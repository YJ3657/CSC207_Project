import main.java.data_access.FileInstructionsDataAccessObject;
import main.java.use_case.instructions.InstructionsInputBoundary;
import main.java.use_case.instructions.InstructionsInteractor;
import main.java.use_case.instructions.InstructionsOutputBoundary;
import main.java.use_case.instructions.InstructionsOutputData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InstructionsInteractorTest {
    @Test
    public void SuccessTest() {
        FileInstructionsDataAccessObject fileInstructionsDataAccessObject = new FileInstructionsDataAccessObject("./instructions.txt");

        InstructionsOutputData expectedInstructionsOutputData = new InstructionsOutputData(getTestContent());
        InstructionsOutputBoundary successPresenter = new InstructionsOutputBoundary() {
            @Override
            public void prepareSuccessView(InstructionsOutputData instructionsOutputData) {
                assertEquals(instructionsOutputData.getInstructions().substring(0,61), expectedInstructionsOutputData.getInstructions().substring(0,61));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure not supposed to happen");
            }
        };

        InstructionsInputBoundary interactor = new InstructionsInteractor(fileInstructionsDataAccessObject, successPresenter);
        interactor.execute();
    }



    private String getTestContent(){
        String content = "Welcome to RetainU, the all in one educational notetaking app.";
        return content;
    }
}
