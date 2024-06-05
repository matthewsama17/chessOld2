package dataaccess.memory;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import model.GameList;
import java.util.ArrayList;

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
    public GameList listGames() {
        return new GameList(games);
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
        throw new DataAccessException("Game does not exist.");
    }

    @Override
    public void clear() {
        games.clear();
    }
}
