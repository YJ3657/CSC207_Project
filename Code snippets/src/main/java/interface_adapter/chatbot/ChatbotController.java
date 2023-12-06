package main.java.interface_adapter.chatbot;

import main.java.use_case.chatbot.ChatbotInputBoundary;
import main.java.use_case.chatbot.ChatbotInputData;
import main.java.use_case.quiz.QuizInputBoundary;
import main.java.use_case.quiz.QuizInputData;

public class ChatbotController {
    final ChatbotInputBoundary chatbotInteractor;

    public ChatbotController(ChatbotInputBoundary chatbotInputBoundary){
        this.chatbotInteractor = chatbotInputBoundary;
    }

    public void execute(String prompt) {
        ChatbotInputData chatbotInputData = new ChatbotInputData(prompt);
        chatbotInteractor.execute(chatbotInputData);
    }

}
