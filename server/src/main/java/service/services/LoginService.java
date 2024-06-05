package service.services;

import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryUserDAO;
import model.UserData;
import request.LoginRequest;
import result.LoginResult;

public class LoginService {
    public static LoginResult login(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        LoginResult result = new LoginResult();
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

        UserData userData = userDAO.getUser(username);

        if(userData.password() != password) {
            result.setCode(401);
            result.setError("Error: unauthorized");
            return result;
        }

        String authToken = authDAO.createAuth(username);
        result.setCode(200);
        result.setAuthToken(authToken);
        result.setUsername(username);
        return result;
    }
}