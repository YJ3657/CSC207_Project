package main.java.entity;

//as per ta suggestion, done for now
public class Question {
    private int chapterno;
    private String question;
    private String answer;

    public Question(int chapterno, String question, String answer){
        this.chapterno = chapterno;
        this.question = question;
        this.answer = answer;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
