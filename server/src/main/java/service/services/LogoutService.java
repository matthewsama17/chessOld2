package service.services;

import dataaccess.AuthDAO;
import dataaccess.memory.MemoryAuthDAO;
import service.request.LogoutRequest;
import service.result.Result;

public class LogoutService {
    public static Result logout(LogoutRequest logoutRequest) {
        String authToken = logoutRequest.authToken();

        Result result = new Result();
        AuthDAO authDAO = new MemoryAuthDAO();

        if(authDAO.getAuth(authToken) == null) {
            result.setCode(401);
            result.setError("Error: unauthorized");
            return result;
        }

        authDAO.deleteAuth(authToken);

        result.setCode(200);
        return result;
    }
}