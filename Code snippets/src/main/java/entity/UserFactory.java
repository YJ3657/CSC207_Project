package main.java.entity;
import main.java.entity.User;
import java.time.LocalDateTime;

public interface UserFactory {
    /**
     * Requires: password is valid.
     */
    User create(String userId, String password);
}
