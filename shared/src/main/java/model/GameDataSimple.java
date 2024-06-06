package model;

public record GameDataSimple(int gameID, String whiteUsername, String blackUsername, String gameName) {
    public GameDataSimple(GameData gameData) {
        this(gameData.gameID(), gameData.whiteUsername(), gameData.blackUsername(), gameData.gameName());
    }
}