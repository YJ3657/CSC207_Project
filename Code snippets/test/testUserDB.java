import main.java.entity.DefaultUserFactory;
import main.java.entity.NotesFactory;
import main.java.entity.User;
public class testUserDB {

    public static void main(String[] args) {
        User user = new User("newUser1","abc123");
        user.getCourseId().add("CSC299");
        user.getCourseId().add("CSC258");
        user.getGroupId().add("group1");
        user.getGroupId().add("group2");

        DBUserDataAccessObject db = new DBUserDataAccessObject(new DefaultUserFactory(), new NotesFactory());
        db.save(user);
    }

}
