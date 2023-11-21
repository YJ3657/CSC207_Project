package main.java.use_case.signup;

import main.java.entity.User;

public interface SignupUserDataAccessInterface {

    boolean existsByName(String studentId);

    void save(User user);

}
