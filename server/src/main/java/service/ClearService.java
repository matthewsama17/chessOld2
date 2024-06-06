package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import dataaccess.memory.MemoryUserDAO;

public class ClearService {
    public static void clear() {
        AuthDAO authDAO = new MemoryAuthDAO();
        authDAO.clear();

        GameDAO gameDAO = new MemoryGameDAO();
        gameDAO.clear();

        UserDAO userDAO = new MemoryUserDAO();
        userDAO.clear();
    }
}