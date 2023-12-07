package main.java.use_case.chatbot;

import main.java.use_case.login.LoginInputData;

public interface ChatbotInputBoundary {
    void execute(ChatbotInputData chatbotInputData);
}
