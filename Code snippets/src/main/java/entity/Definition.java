package main.java.entity;

//as per ta suggestion, done for now
public class Definition {
    private int chapterno;
    private String word;
    private String definition;

    public Definition(int chapterno, String word, String definition){
        this.chapterno = chapterno;
        this.word = word;
        this.definition = definition;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
