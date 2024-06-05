package service.services;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import service.request.CreateGameRequest;
import service.result.CreateGameResult;

public class CreateGameService {
    public static CreateGameResult createGame(CreateGameRequest createGameRequest) {
        String authToken = createGameRequest.authToken();
        String gameName = createGameRequest.gameName();

        CreateGameResult result = new CreateGameResult();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        if(authDAO.getAuth(authToken) == null) {
            result.setCode(401);
            result.setError("Error: unauthorized");
            return result;
        }

        int gameID = gameDAO.createGame(gameName);
        result.setCode(200);
        result.setGameID(gameID);
        return result;
    }
}