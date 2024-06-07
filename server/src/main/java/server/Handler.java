package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import service.*;
import request.*;
import result.*;
import spark.Request;
import spark.Response;

public class Handler {

    private static Gson gson = new Gson();

    public static String clear(Request req, Response res) {
        try {
            ClearService.clear();
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return "{}";
    }

    public static String register(Request req, Response res) {
        RegisterRequest registerRequest = gson.fromJson(req.body(), RegisterRequest.class);
        LoginResult loginResult;

        try {
            loginResult = RegisterService.register(registerRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return gson.toJson(loginResult);
    }

    public static String login(Request req, Response res) {
        LoginRequest loginRequest = gson.fromJson(req.body(), LoginRequest.class);
        LoginResult loginResult;

        try {
            loginResult = LoginService.login(loginRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return gson.toJson(loginResult);
    }

    public static String logout(Request req, Response res) {
        String authToken = req.headers("authorization");
        LogoutRequest logoutRequest = new LogoutRequest(authToken);

        try {
            LogoutService.logout(logoutRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return "{}";
    }

    public static String listGames(Request req, Response res) {
        String authToken = req.headers("authorization");
        ListGamesRequest listGamesRequest = new ListGamesRequest(authToken);
        ListGamesResult listGamesResult;

        try {
            listGamesResult = ListGamesService.listGames(listGamesRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return gson.toJson(listGamesResult);
    }

    public static String createGame(Request req, Response res) {
        String authToken = req.headers("authorization");
        CreateGameRequest createGameRequest = gson.fromJson(req.body(), CreateGameRequest.class);
        createGameRequest = new CreateGameRequest(authToken,createGameRequest.gameName());
        CreateGameResult createGameResult;

        try {
            createGameResult = CreateGameService.createGame(createGameRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return gson.toJson(createGameResult);
    }

    public static String joinGame(Request req, Response res) {
        String authToken = req.headers("authorization");
        JoinGameRequest joinGameRequest = gson.fromJson(req.body(), JoinGameRequest.class);
        joinGameRequest = new JoinGameRequest(authToken, joinGameRequest.playerColor(), joinGameRequest.gameID());

        try {
            JoinGameService.joinGame(joinGameRequest);
        } catch(DataAccessException ex) {
            ErrorMessage errorMessage = generateErrorMessage(ex, res);
            return gson.toJson(errorMessage);

        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return "{}";
    }

    private static ErrorMessage generateErrorMessage(DataAccessException ex, Response res) {
        String message = ex.getMessage();

        if(message.equals("Error: bad request")) {
            res.status(400);
        }
        else if(message.equals("Error: unauthorized")) {
            res.status(401);
        }
        else if(message.equals("Error: already taken")) {
            res.status(403);
        }

        return new ErrorMessage(message);
    }
}
