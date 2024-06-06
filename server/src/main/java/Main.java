import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataaccess.DataAccessException;
import request.*;
import result.*;
import service.*;


public class Main {
    public static void main(String[] args) throws DataAccessException {

        LoginResult loginResult = RegisterService.register(new RegisterRequest("matthew","matthew's password","mgh57@byu.edu"));
        String authToken = loginResult.authToken();
        LogoutService.logout(new LogoutRequest(authToken));
        loginResult = LoginService.login(new LoginRequest("matthew","matthew's password"));
        authToken = loginResult.authToken();

        ListGamesResult listGamesResult = ListGamesService.listGames(new ListGamesRequest(authToken));
        Gson gson = new Gson();
        System.out.println(gson.toJson(listGamesResult));

        CreateGameResult createGameResult = CreateGameService.createGame(new CreateGameRequest(authToken,"game1"));
        int gameID = createGameResult.gameID();
        createGameResult = CreateGameService.createGame(new CreateGameRequest(authToken,"game2"));
        int gameID2 = createGameResult.gameID();

        JoinGameService.joinGame(new JoinGameRequest(authToken, ChessGame.TeamColor.BLACK, gameID));
        JoinGameService.joinGame(new JoinGameRequest(authToken, ChessGame.TeamColor.WHITE, gameID));
        JoinGameService.joinGame(new JoinGameRequest(authToken, ChessGame.TeamColor.WHITE, gameID2));

        listGamesResult = ListGamesService.listGames(new ListGamesRequest(authToken));
        System.out.println(gson.toJson(listGamesResult));

        ClearService.clear();

        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);
    }
}