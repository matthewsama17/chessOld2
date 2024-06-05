package service.services;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import service.request.ListGamesRequest;
import service.result.ListGamesResult;

public class ListGamesService {
    public static ListGamesResult listGames(ListGamesRequest listGamesRequest) {
        String authToken = listGamesRequest.authToken();

        ListGamesResult result = new ListGamesResult();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        if(authDAO.getAuth(authToken) == null) {
            result.setCode(401);
            result.setError("Error: unauthorized");
            return result;
        }

        result.setCode(200);
        result.setGames(gameDAO.listGames());
        return result;
    }
}