package dataaccess.memory;

import dataaccess.UserDAO;
import model.UserData;
import java.util.ArrayList;

public class MemoryUserDAO implements UserDAO {

    private static ArrayList<UserData> users = new ArrayList<>();

    @Override
    public void createUser(String username, String password, String email) {
        users.add(new UserData(username, password, email));
    }

    @Override
    public UserData getUser(String username) {
        for(UserData user : users) {
            if(user.username() == username) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        users.clear();
    }
}