package main.java.entity;

public class DefaultUserFactory implements UserFactory{
    public User create(String id, String password){
        return new User(id, password);
    }
}
