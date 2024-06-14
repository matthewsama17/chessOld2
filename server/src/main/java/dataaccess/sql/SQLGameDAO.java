package dataaccess.sql;

import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import model.GameDataSimple;

import java.util.List;

public class SQLGameDAO implements GameDAO {
    @Override
    public int createGame(String gameName) {
        return 0;
    }

    @Override
    public GameData getGame(int gameID) {
        return null;
    }

    @Override
    public List<GameDataSimple> listGames() {
        return List.of();
    }

    @Override
    public void updateGame(int gameID, GameData game) throws DataAccessException {

    }

    @Override
    public void clear() {

    }
}
