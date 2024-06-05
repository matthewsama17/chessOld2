package service.services;

import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryUserDAO;
import request.RegisterRequest;
import result.LoginResult;

public class RegisterService {
    public static LoginResult register(RegisterRequest registerRequest) {
        String username = registerRequest.username();
        String password = registerRequest.password();
        String email = registerRequest.email();

        LoginResult result = new LoginResult();
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

        if(username == null || password == null || email == null) {
            result.setCode(400);
            result.setError("Error: bad request");
            return result;
        }

        if(userDAO.getUser(username) != null) {
            result.setCode(403);
            result.setError("Error: already taken");
        }

        userDAO.createUser(username, password, email);
        String authToken = authDAO.createAuth(username);

        result.setCode(200);
        result.setAuthToken(authToken);
        result.setUsername(username);
        return result;
    }
}