package dataaccess;

import model.GameData;
import model.GameDataSimple;

import java.util.List;

public interface GameDAO {
    int createGame(String gameName);
    GameData getGame(int gameID);
    List<GameDataSimple> listGames();
    void updateGame(int gameID, GameData game) throws DataAccessException;
    void clear();
}
