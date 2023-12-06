package main.java.interface_adapter.chatbot;

public class ChatbotState {
    private String msgHistory;

    private String promptArea;

    public String getPromptArea() {
        return promptArea;
    }

    public void setPromptArea(String promptArea) {
        this.promptArea = promptArea;
    }

    public ChatbotState(ChatbotState copy){
        msgHistory = copy.getMsgHistory();
        promptArea = copy.getPromptArea();
    }

    public String getMsgHistory() {
        return msgHistory;
    }

    public void setMsgHistory(String msgHistory) {
        this.msgHistory = msgHistory;
    }

    public ChatbotState(){

    }
}
