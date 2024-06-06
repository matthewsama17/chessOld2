package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.memory.MemoryAuthDAO;
import request.LogoutRequest;

public class LogoutService {
    public static void logout(LogoutRequest logoutRequest) throws DataAccessException {
        String authToken = logoutRequest.authToken();

        AuthDAO authDAO = new MemoryAuthDAO();

        if(authDAO.getAuth(authToken) == null) {
            throw new DataAccessException("Error: unauthorized");
        }

        authDAO.deleteAuth(authToken);
    }
}