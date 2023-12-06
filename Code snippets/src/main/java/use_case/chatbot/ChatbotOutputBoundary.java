package main.java.use_case.chatbot;

import main.java.use_case.add_Question_Definition.DefQuesOutputData;

public interface ChatbotOutputBoundary {
    void prepareSuccessView(ChatbotOutputData chatbotOutputData);

    void prepareFailView(ChatbotOutputData chatbotOutputData);
}
