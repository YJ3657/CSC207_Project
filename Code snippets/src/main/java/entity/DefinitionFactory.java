package main.java.entity;

public class DefinitionFactory {
    public Definition create(int chapterno, String word, String definition) {
        return new Definition(chapterno, word, definition);
    }
}
