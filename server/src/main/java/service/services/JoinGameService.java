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

public class JoinGameService {
    public static void joinGame(JoinGameRequest joinGameRequest) throws DataAccessException {
        String authToken = joinGameRequest.authToken();
        ChessGame.TeamColor playerColor = joinGameRequest.playerColor();
        int gameID = joinGameRequest.gameID();

        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        AuthData authData = authDAO.getAuth(authToken);
        if(authData == null) {
            throw new DataAccessException("Error: unauthorized");
        }
        String username = authData.username();

        GameData gameData = gameDAO.getGame(gameID);
        if(gameData == null || playerColor == null) {
            throw new DataAccessException("Error: bad request");
        }


        if(((playerColor == ChessGame.TeamColor.BLACK) && (gameData.blackUsername() != null))
                || ((playerColor == ChessGame.TeamColor.WHITE) && (gameData.whiteUsername() != null))) {
            throw new DataAccessException("Error: already taken");
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

        gameDAO.updateGame(gameID, newGameData);
    }
}