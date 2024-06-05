package service.services;

import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryUserDAO;
import service.request.RegisterRequest;
import service.result.LoginResult;

public class RegisterService {
    public static LoginResult register(RegisterRequest registerRequest) {
        String username = registerRequest.username();
        String password = registerRequest.password();
        String email = registerRequest.email();

        LoginResult result = new LoginResult();
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

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