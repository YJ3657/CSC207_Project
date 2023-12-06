package main.java.use_case.chatbot;

public class ChatbotOutputData {
    private final String answer;

    public ChatbotOutputData(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }
}
