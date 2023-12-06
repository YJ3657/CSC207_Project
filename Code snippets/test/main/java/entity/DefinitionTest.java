package main.java.entity;

import main.java.app.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefinitionTest {

    private final DefinitionFactory definitionFactory = new DefinitionFactory();
    private Definition testDefinition;

    @BeforeEach
    void init(){
        testDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER, Constants.LIMIT_TERM, Constants.LIMIT_DEF);
    }

    @Test
    void setChapterNumber(){
        int oldChapterNumber = testDefinition.getChapterno();
        int newChapterNumber = testDefinition.getChapterno() + 2;
        testDefinition.setChapterno(newChapterNumber);
        assert testDefinition.getChapterno() == oldChapterNumber + 2;
    }

    @Test
    void setWord(){
        String oldWord = testDefinition.getWord();
        String newWord = testDefinition.getWord() + " changes were made.";
        testDefinition.setWord(newWord);
        assert testDefinition.getWord().equals(oldWord + " changes were made.");
    }

    @Test
    void setDefinition(){
        String oldDefinition = testDefinition.getDefinition();
        String newDefinition = testDefinition.getDefinition() + " changes to def were made.";
        testDefinition.setDefinition(newDefinition);
        assert testDefinition.getDefinition().equals(oldDefinition + " changes to def were made.");
    }

}
