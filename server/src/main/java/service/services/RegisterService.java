package service.services;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryUserDAO;
import request.RegisterRequest;
import result.LoginResult;

public class RegisterService {
    public static LoginResult register(RegisterRequest registerRequest) throws DataAccessException {
        String username = registerRequest.username();
        String password = registerRequest.password();
        String email = registerRequest.email();

        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();

        if(username == null || password == null || email == null) {
            throw new DataAccessException("Error: bad request");
        }

        if(userDAO.getUser(username) != null) {
            throw new DataAccessException("Error: already taken");
        }

        userDAO.createUser(username, password, email);
        String authToken = authDAO.createAuth(username);

        return new LoginResult(username, authToken);
    }
}