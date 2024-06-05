package dataaccess;

import model.GameData;
import model.GameList;

public interface GameDAO {
    int createGame(String gameName);
    GameData getGame(int gameID);
    GameList listGames();
    void updateGame(int gameID, GameData game) throws DataAccessException;
    void clear();
}
