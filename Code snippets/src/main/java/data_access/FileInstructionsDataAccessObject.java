package main.java.data_access;

import main.java.entity.Course;
import main.java.use_case.instructions.InstructionsUserDataAccessInterface;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileInstructionsDataAccessObject implements InstructionsUserDataAccessInterface {
    private final File txtFile;
    private String instructions = "";
    public FileInstructionsDataAccessObject(String txtPath){
        txtFile = new File(txtPath);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
            String row;

            while((row = reader.readLine()) != null){
                instructions = instructions + row;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getInstructions() {
        return instructions;
    }
}
