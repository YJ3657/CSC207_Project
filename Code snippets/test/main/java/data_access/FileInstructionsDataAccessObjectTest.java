package main.java.data_access;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FileInstructionsDataAccessObjectTest {

    FileInstructionsDataAccessObject fileInstructionsDataAccessObject = new FileInstructionsDataAccessObject("./instructions.txt");

    @Test
    void getInstructions() {
        assert !fileInstructionsDataAccessObject.getInstructions().equals("");
    }

//    @Test
//    void fileNotFoundError(){
//        try {
//            findFile();
//            fail("Should've found exception");
//        } catch (FileNotFoundException e){
//
//        }
//    }
//
//    private void findFile() throws FileNotFoundException{
//        fileInstructionsDataAccessObject = new FileInstructionsDataAccessObject("./notExists.txt");
//    }
}