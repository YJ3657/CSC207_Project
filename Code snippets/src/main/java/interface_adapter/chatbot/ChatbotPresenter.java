package main.java.interface_adapter.chatbot;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.quiz.QuizViewModel;
import main.java.use_case.chatbot.ChatbotOutputBoundary;
import main.java.use_case.chatbot.ChatbotOutputData;

public class ChatbotPresenter implements ChatbotOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final ChatbotViewModel chatbotViewModel;
    public ChatbotPresenter(ChatbotViewModel chatbotViewModel){
        this.chatbotViewModel = chatbotViewModel;

    }

    @Override
    public void prepareSuccessView(ChatbotOutputData chatbotOutputData) {
        ChatbotState chatbotState = chatbotViewModel.getState();
        chatbotViewModel.setState(chatbotState);
        chatbotViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(chatbotViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(ChatbotOutputData chatbotOutputData) {

    }
}
