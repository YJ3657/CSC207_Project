package main.java.interface_adapter.home;

public class HomeState {
    private String username = "";

    public HomeState(HomeState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HomeState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
