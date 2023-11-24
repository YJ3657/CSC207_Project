package main.java.entity;

public class DefinitionFactory {
    public Definition create(String word, String definition){return new Definition(word, definition);}
}
