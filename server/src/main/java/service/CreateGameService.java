package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import request.CreateGameRequest;
import result.CreateGameResult;

public class CreateGameService {
    public static CreateGameResult createGame(CreateGameRequest createGameRequest) throws DataAccessException {
        String authToken = createGameRequest.authToken();
        String gameName = createGameRequest.gameName();

        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        if(gameName == null) {
            throw new DataAccessException("Error: bad request");
        }

        if(authDAO.getAuth(authToken) == null) {
            throw new DataAccessException("Error: unauthorized");
        }

        int gameID = gameDAO.createGame(gameName);
        return new CreateGameResult(gameID);
    }
}