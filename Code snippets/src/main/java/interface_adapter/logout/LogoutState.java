package main.java.interface_adapter.logout;

public class LogoutState {
    private String username = "";

    public LogoutState(LogoutState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LogoutState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
