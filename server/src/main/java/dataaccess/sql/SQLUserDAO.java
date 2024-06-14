package dataaccess.sql;

import dataaccess.UserDAO;
import model.UserData;

public class SQLUserDAO implements UserDAO {
    @Override
    public void createUser(String username, String password, String email) {

    }

    @Override
    public UserData getUser(String username) {
        return null;
    }

    @Override
    public void clear() {

    }
}
