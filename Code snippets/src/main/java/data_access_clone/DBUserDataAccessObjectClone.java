//package main.java.data_access;
//
//import main.java.entity.*;
//import main.java.use_case.clear_users.ClearUserDataAccessInterface;
//import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
//import main.java.use_case.login.LoginUserDataAccessInterface;
//import main.java.use_case.notes.NotesDataAccessInterface;
//import main.java.use_case.signup.SignupUserDataAccessInterface;
//import main.java.use_case.update_users.UpdateUserDataAccessInterface;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
//        UpdateUserDataAccessInterface, FindUserCourseDataAccessInterface, NotesDataAccessInterface {
//
//    private final Map<String, User> accounts = new HashMap<>();
//    private UserFactory userFactory;
//    public DBUserDataAccessObject(UserFactory userFactory){
//        this.userFactory = userFactory;
//        try{
//            File f = new File("user_data.txt");
//            BufferedReader reader = new BufferedReader(new FileReader(f));
//            String row;
//
//            while((row = reader.readLine()) != null){
//                String[] info = row.split(",");
//                User user = this.userFactory.create(info[0], info[1]);
//                accounts.put(info[0], user);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public void clear() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter("user_data.txt"));
//            for (String key: accounts.keySet()){
//                bw.write("");
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Map<String, List<Notes>> getUserCourses(String userid) {
//        return accounts.get(userid).getNotes();
//    }
//
//    @Override
//    public User get(String username) {
//        return accounts.get(username);
//    }
//
//    @Override
//    public void addNotes(Notes notes, String courseId, String userId) {
//        accounts.get(userId).setNotes(notes, courseId);
//        this.save();
//    }
//
//    @Override
//    public boolean existsByName(String courseId, String title, String userId) {
//        int result = 0;
//        for (Notes i : accounts.get(userId).getNotes().get(courseId)){
//            if(i.getTitle().equals(title)){
//                result += 1;
//            }
//        }
//        return result == 0;
//    }
//
//    @Override
//    public boolean existsByName(String studentId) {
//        return accounts.containsKey(studentId);
//    }
//
//    @Override
//    public void saveUser(User user) {
//        accounts.put(user.getId(), user);
//        this.save();
//    }
//
//    @Override
//    public void update(User user) {
//        accounts.put(user.getId(), user);
//        this.save();
//    }
//
//    public void save() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter("user_data.txt"));
//            for (String key: accounts.keySet()){
//                bw.write(accounts.get(key).getId() + "," + accounts.get(key).getPassword());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
