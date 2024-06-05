package service.services;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryUserDAO;
import model.UserData;
import request.LoginRequest;
import result.LoginResult;

public class LoginService {
    public static LoginResult login(LoginRequest loginRequest) throws DataAccessException {
        String username = loginRequest.username();
        String password = loginRequest.password();

        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

        UserData userData = userDAO.getUser(username);

        if(userData.password() != password) {
            throw new DataAccessException("Error: unauthorized");
        }

        String authToken = authDAO.createAuth(username);

        return new LoginResult(authToken, username);
    }
}