package main.java.use_case.chatbot;

import main.java.app.Constants;
import main.java.entity.User;
import main.java.use_case.login.LoginOutputData;

public class ChatbotInteractor implements ChatbotInputBoundary{

    final ChatbotDataAccessInterface chatbotDataAccessObject;
    final ChatbotOutputBoundary chatbotPresenter;

    public ChatbotInteractor(ChatbotDataAccessInterface chatbotDataAccessObject, ChatbotOutputBoundary chatbotPresenter) {
        this.chatbotDataAccessObject = chatbotDataAccessObject;
        this.chatbotPresenter = chatbotPresenter;
    }

    @Override
    public void execute(ChatbotInputData chatbotInputData) {
        String prompt = chatbotInputData.getPrompt();

        chatbotPresenter.prepareSuccessView();
    }
}
