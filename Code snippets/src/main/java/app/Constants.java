package main.java.app;

import main.java.entity.User;

public final class Constants {

    // For app:
    public static final String HOME_VIEWNAME = "home";
    public static final String NOTES_VIEWNAME = "Notes";
    public static final String QUIZ_VIEWNAME = "Quiz";

    public static final String COURSES_PROPNAME = "courses";

    public static final String STATE_PROPNAME = "state";

    public static final String VIEW_PROPNAME = "view";

    public static final double NOTE_TOPICS_SIZE = 1.0 / 6; // occupies 1/6 of the ScrollPane

    public static final String ADD_COURSE_ERROR = "Course already exists";

    public static final String QUIZ_ERROR = "Quiz Cannot be setup. Please input course materials in notes.";
    public static final String QUIZ_STATE = "Quiz state";

    public static String CURRENT_USER;

    public static User CURRENT_USER_OBJ;

    public static int FRAME_WIDTH;

    public static int FRAME_HEIGHT;

    // For testing:
    public static String LIMIT_DEF = "As x approaches some point, f(x) approaches some point";

    public static String CONTIUNUITY_DEF = "f(x) is continuous at a if lim x -> a f(x) = f(a)";
    private Constants() {}


}
