package service.result;

import model.GameList;

public class ListGamesResult extends Result {

    private GameList games;

    public void setGames(GameList gameList) {
        this.games = gameList;
    }

    public GameList getGames() {
        return games;
    }
}
