package main.java.entity;


import java.util.HashMap;
import java.util.Map;

public class Contents{

    private Map<String, Definition> definitions;

    private Map<String, Question> questions;

    public Contents(){
        definitions = new HashMap<String, Definition>();
        questions = new HashMap<String, Question>();
    }

    public Definition getDefinition(String name){
        return definitions.get(name); //return undefined if no key in map
    }

    public Question getQuestion(String name){
        return questions.get(name);
    }

    public void addDefinition(String name, Definition definition){
        definitions.put(name, definition);
    }

    public void addQuestion(String name, Question question){
        questions.put(name, question);
    }


}