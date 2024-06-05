package service.services;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import model.AuthData;
import model.GameData;
import request.JoinGameRequest;
import result.Result;

public class JoinGameService {
    public static Result joinGame(JoinGameRequest joinGameRequest) {
        String authToken = joinGameRequest.authToken();
        ChessGame.TeamColor playerColor = joinGameRequest.playerColor();
        int gameID = joinGameRequest.gameID();

        Result result = new Result();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        AuthData authData = authDAO.getAuth(authToken);
        if(authData == null) {
            result.setCode(401);
            result.setError("Error: unauthorized");
            return result;
        }
        String username = authData.username();

        GameData gameData = gameDAO.getGame(gameID);
        if(gameData == null || playerColor == null) {
            result.setCode(400);
            result.setError("Error: bad request");
            return result;
        }


        if(((playerColor == ChessGame.TeamColor.BLACK) && (gameData.blackUsername() != null))
                || ((playerColor == ChessGame.TeamColor.WHITE) && (gameData.whiteUsername() != null))) {
            result.setCode(403);
            result.setError("Error: already taken");
            return result;
        }

        String whiteUsername = gameData.whiteUsername();
        String blackUsername = gameData.blackUsername();
        if(playerColor == ChessGame.TeamColor.WHITE) {
            whiteUsername = username;
        }
        else if(playerColor == ChessGame.TeamColor.BLACK) {
            blackUsername = username;
        }
        String gameName = gameData.gameName();
        ChessGame game = gameData.game();
        GameData newGameData = new GameData(gameID,whiteUsername,blackUsername,gameName,game);

        try {
            gameDAO.updateGame(gameID, newGameData);
        } catch(DataAccessException ex) {
            result.setCode(400);
            result.setError("Error: bad request");
            return result;
        }

        result.setCode(200);
        return result;
    }
}