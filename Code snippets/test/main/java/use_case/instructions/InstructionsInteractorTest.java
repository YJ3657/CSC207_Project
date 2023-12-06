package main.java.use_case.instructions;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsInteractorTest {

    @Test
    void execute() {

        String instructions = "";
        File txtFile = new File("instructions.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
            String row;

            while((row = reader.readLine()) != null){
                instructions = instructions + row + "\n";
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedInstructions = instructions;
//        InstructionsInputBoundary interactor = new InstructionsInteractor()
    }

}