package main.java.use_case.login;
import main.java.entity.User;

public interface LoginUserDataAccessInterface {

    boolean existsByName(String studentId);

    void save(User user);

    User get(String username);
}
