package main.java.app;

import main.java.data_access.ChatGptDAO;
import main.java.data_access.DBDataAccessObject;
import main.java.data_access.FileInstructionsDataAccessObject;
import main.java.interface_adapter.chatbot.ChatbotPresenter;
import main.java.interface_adapter.instructions.InstructionsController;
import main.java.interface_adapter.instructions.InstructionsPresenter;
import main.java.interface_adapter.instructions.InstructionsViewModel;
import main.java.interface_adapter.logout.LogoutController;
import main.java.interface_adapter.logout.LogoutPresenter;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.reminder.ReminderController;
import main.java.interface_adapter.reminder.ReminderPresenter;
import main.java.interface_adapter.reminder.ReminderViewModel;
import main.java.interface_adapter.chatbot.ChatbotController;
import main.java.interface_adapter.chatbot.ChatbotViewModel;
import main.java.use_case.chatbot.ChatbotInputBoundary;
import main.java.use_case.chatbot.ChatbotInteractor;
import main.java.use_case.chatbot.ChatbotOutputBoundary;
import main.java.use_case.instructions.InstructionsInputBoundary;
import main.java.use_case.instructions.InstructionsInteractor;
import main.java.use_case.instructions.InstructionsOutputBoundary;
import main.java.use_case.logout.LogoutInputBoundary;
import main.java.use_case.logout.LogoutInteractor;
import main.java.use_case.logout.LogoutOutputBoundary;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.interface_adapter.*;
import main.java.interface_adapter.home.HomeViewModel;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.interface_adapter.notes.OpenNotesController;
import main.java.interface_adapter.notes.OpenNotesPresenter;
import main.java.use_case.notes.OpenNotesInputBoundary;
//import main.java.use_case.notes.OpenNotesInteractor;
import main.java.use_case.notes.OpenNotesInteractor;
import main.java.use_case.notes.OpenNotesOutputBoundary;
import main.java.use_case.reminder.ReminderInputBoundary;
import main.java.use_case.reminder.ReminderInteractor;
import main.java.use_case.reminder.ReminderOutputBoundary;
import main.java.view.ChatbotPanel;
import main.java.view.HomeView;

public class HomeUseCaseFactory {

    private HomeUseCaseFactory() {}

    public static HomeView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                                  NotesViewModel notesViewModel, NotesDataAccessInterface notesDataAcessObject,
                                  LoginViewModel loginViewModel, InstructionsViewModel instructionsViewModel, FileInstructionsDataAccessObject fileInstructionsDataAccessObject,
                                  DBDataAccessObject dbReminderDataAccessObject, ReminderViewModel reminderViewModel, DBDataAccessObject userDataAccessObject, ChatbotViewModel chatbotViewModel) throws InterruptedException {
        OpenNotesController openNotesController = createOpenNotesUseCase(viewManagerModel, notesViewModel, userDataAccessObject);
        LogoutController logoutController = createLogoutUseCase(viewManagerModel,loginViewModel);
        InstructionsController instructionsController = createInstructionsUseCase(instructionsViewModel, viewManagerModel, fileInstructionsDataAccessObject);
        ReminderController reminderController = createReminderUseCase(reminderViewModel, viewManagerModel, dbReminderDataAccessObject);
        ChatbotController chatbotController = createChatbotUseCase(chatbotViewModel, new ChatGptDAO());
        return new HomeView(homeViewModel, openNotesController, logoutController, instructionsController, reminderController, chatbotController);
    }


    private static ChatbotController createChatbotUseCase(ChatbotViewModel chatbotViewModel, ChatGptDAO chatGptDAO){
        ChatbotOutputBoundary chatbotPresenter = new ChatbotPresenter(chatbotViewModel);
        ChatbotInputBoundary chatbotInteractor = new ChatbotInteractor(chatGptDAO, chatbotPresenter);
        return new ChatbotController(chatbotInteractor);
    }
    private static ReminderController createReminderUseCase(ReminderViewModel reminderViewModel, ViewManagerModel viewManagerModel,
                                                            DBDataAccessObject dbReminderDataAccessObject) {
        ReminderOutputBoundary reminderPresenter = new ReminderPresenter(viewManagerModel, reminderViewModel);
        ReminderInputBoundary reminderInteractor = new ReminderInteractor(dbReminderDataAccessObject, reminderPresenter);
        return new ReminderController(reminderInteractor);
    }

    private static InstructionsController createInstructionsUseCase(InstructionsViewModel instructionsViewModel, ViewManagerModel viewManagerModel, FileInstructionsDataAccessObject dataAccessObject) {
        InstructionsOutputBoundary instructionPresenter = new InstructionsPresenter(instructionsViewModel, viewManagerModel);
        InstructionsInputBoundary instructionsInteractor = new InstructionsInteractor(dataAccessObject, instructionPresenter);
        return new InstructionsController(instructionsInteractor);
    }



    private static OpenNotesController createOpenNotesUseCase(ViewManagerModel viewManagerModel, NotesViewModel
        notesViewModel, DBDataAccessObject notesDataAccessObject) {
        OpenNotesOutputBoundary openNotesPresenter = new OpenNotesPresenter(viewManagerModel, notesViewModel);


//        made a small change to make my code work
        OpenNotesInputBoundary clearInteractor = new OpenNotesInteractor((FindUserCourseDataAccessInterface) notesDataAccessObject, openNotesPresenter);
        return new OpenNotesController(clearInteractor);
    }
    private static LogoutController createLogoutUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel){
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loginViewModel,viewManagerModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);
        return new LogoutController(logoutInteractor);
    }
}
