package dataaccess.sql;

import dataaccess.AuthDAO;
import model.AuthData;

public class SQLAuthDAO implements AuthDAO {

    @Override
    public String createAuth(String username) {
        return "";
    }

    @Override
    public AuthData getAuth(String authToken) {
        return null;
    }

    @Override
    public void deleteAuth(String authToken) {

    }

    @Override
    public void clear() {

    }
}
