package model;

import java.util.List;

public class GameList {

    private List<GameData> games;

    public GameList() { }

    public GameList(List<GameData> gameList) {
        this.games = gameList;
    }

    public List<GameData> getGames() {
        return games;
    }
}