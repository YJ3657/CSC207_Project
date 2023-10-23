package main.java.entity;

//DONE FOR NOW
public class PasswordValidatorService{
    private boolean isValid;
    public PasswordValidatorService(){
        isValid = false;
    }

    /**
     * Check if inputted password valid for given user
     * @param user - user to check password for
     * @param password - attempted password
     * @return if password valid or not
     */
    public boolean checkPassword(User user, String password){
        return user.getPassword().equals(password);
    }

}