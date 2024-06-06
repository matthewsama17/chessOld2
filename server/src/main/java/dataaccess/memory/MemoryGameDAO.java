package dataaccess.memory;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import model.GameDataSimple;

import java.util.ArrayList;
import java.util.List;

public class MemoryGameDAO implements GameDAO {

    private static int lastGameID = 0;

    private static ArrayList<GameData> games = new ArrayList<>();

    @Override
    public int createGame(String gameName) {
        lastGameID += 1;
        games.add(new GameData(lastGameID, null, null, gameName,new ChessGame()));
        return lastGameID;
    }

    @Override
    public GameData getGame(int gameID) {
        for(GameData game : games) {
            if(game.gameID() == gameID) {
                return game;
            }
        }
        return null;
    }

    @Override
    public List<GameDataSimple> listGames() {
        List<GameDataSimple> result = new ArrayList<>();

        for(GameData game : games) {
            result.add(new GameDataSimple(game));
        }

        return result;
    }

    @Override
    public void updateGame(int gameID, GameData game) throws DataAccessException {
        for(GameData gameData : games) {
            if(gameData.gameID() == gameID) {
                games.remove(gameData);
                games.add(game);
                return;
            }
        }
        throw new DataAccessException("Error: bad request");
    }

    @Override
    public void clear() {
        games.clear();
    }
}
