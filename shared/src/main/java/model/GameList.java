package model;

import java.util.List;

public class GameList {

    private List<GameData> gameList;

    public GameList() { }

    public GameList(List<GameData> gameList) {
        this.gameList = gameList;
    }

    public List<GameData> getGames() {
        return gameList;
    }
}
