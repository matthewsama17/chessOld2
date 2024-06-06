package service;

import chess.ChessGame;
import dataaccess.DataAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.CreateGameResult;
import result.ListGamesResult;
import result.LoginResult;

import java.util.ArrayList;

public class ServiceTests {

    @BeforeEach
    public void clearTables() {
        ClearService.clear();
    }

    @Test
    public void testRegisterPositive() {
        RegisterRequest registerRequest = new RegisterRequest("matthew", "matthew's password", "mgh57@byu.edu");
        LoginResult loginResult = new LoginResult(null, null);
        try {
            loginResult = RegisterService.register(registerRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }

        Assertions.assertEquals("matthew", loginResult.username());
        Assertions.assertNotNull(loginResult.authToken());
    }

    @Test
    public void testRegisterNegative() {
        RegisterRequest registerRequest = new RegisterRequest(null, null, null);
        Assertions.assertThrows(DataAccessException.class, () -> RegisterService.register(registerRequest));
    }

    private String register() {
        RegisterRequest registerRequest = new RegisterRequest("matthew", "matthew's password", "mgh57@byu.edu");
        LoginResult loginResult = new LoginResult(null, null);
        try {
            loginResult = RegisterService.register(registerRequest);
        } catch(DataAccessException ignored) { } //This is tested in testRegisterPositive
        return loginResult.authToken();
    }

    @Test
    public void testClear() {
        String authToken = register();

        ClearService.clear();

        LogoutRequest logoutRequest = new LogoutRequest(authToken);
        Assertions.assertThrows(DataAccessException.class, () -> LogoutService.logout(logoutRequest));
    }

    @Test
    public void testLogoutPositive() {
        String authToken = register();

        LogoutRequest logoutRequest = new LogoutRequest(authToken);
        try {
            LogoutService.logout(logoutRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }
    }

    @Test
    public void testLogoutNegative() {
        String authToken = register();

        LogoutRequest logoutRequest = new LogoutRequest(authToken + "a");
        Assertions.assertThrows(DataAccessException.class, () -> LogoutService.logout(logoutRequest));
    }

    @Test
    public void testLoginPositive() {
        testLogoutPositive();

        LoginRequest loginRequest = new LoginRequest("matthew", "matthew's password");
        LoginResult loginResult = new LoginResult(null, null);
        try {
            loginResult = LoginService.login(loginRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }

        Assertions.assertEquals("matthew", loginResult.username());
        Assertions.assertNotNull(loginResult.authToken());
    }

    @Test
    public void testLoginNegative() {
        testLogoutPositive();

        LoginRequest loginRequest = new LoginRequest("matthew", "not matthew's password");
        Assertions.assertThrows(DataAccessException.class, () -> LoginService.login(loginRequest));
    }

    @Test
    public void testListGamesPositive() {
        String authToken = register();

        ListGamesRequest listGamesRequest = new ListGamesRequest(authToken);
        ListGamesResult listGamesResult = null;

        try {
            listGamesResult = ListGamesService.listGames(listGamesRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }

        ListGamesResult expected = new ListGamesResult(new ArrayList<>());
        Assertions.assertEquals(expected, listGamesResult);
    }

    @Test
    public void testListGamesNegative() {
        ListGamesRequest listGamesRequest = new ListGamesRequest("Oops! No authToken!");
        Assertions.assertThrows(DataAccessException.class,() -> ListGamesService.listGames(listGamesRequest));
    }

    @Test
    public void testCreateGamePositive() {
        String authToken = register();

        CreateGameRequest createGameRequest = new CreateGameRequest(authToken, "matthew's game");
        CreateGameResult createGameResult = null;
        try {
            createGameResult = CreateGameService.createGame(createGameRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }

        Assertions.assertNotNull(createGameResult);
    }

    @Test
    public void testCreateGameNegative() {
        String authToken = register();

        CreateGameRequest createGameRequest = new CreateGameRequest(authToken, null);
        Assertions.assertThrows(DataAccessException.class, () -> CreateGameService.createGame(createGameRequest));
    }

    @Test
    public void testJoinGamePositive() {
        String authToken = register();

        CreateGameRequest createGameRequest = new CreateGameRequest(authToken, "matthew's game");
        CreateGameResult createGameResult = null;
        try {
            createGameResult = CreateGameService.createGame(createGameRequest);
        } catch (DataAccessException ignored) { }  //This is tested in testCreateGamePositive
        int gameID = createGameResult.gameID();

        JoinGameRequest joinGameRequest = new JoinGameRequest(authToken, ChessGame.TeamColor.WHITE, gameID);
        try {
            JoinGameService.joinGame(joinGameRequest);
        } catch(DataAccessException ex) {
            Assertions.fail(ex);
        }
    }

    @Test
    public void testJoinGameNegative() {
        String authToken = register();

        JoinGameRequest joinGameRequest = new JoinGameRequest(authToken, ChessGame.TeamColor.WHITE, 0);
        Assertions.assertThrows(DataAccessException.class, () -> JoinGameService.joinGame(joinGameRequest));
    }
}
