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
            if(ex.equals(new DataAccessException("Error: already taken"))) {
                res.status(403);
                ErrorMessage errorMessage = new ErrorMessage("Error: already taken");
                return gson.toJson(errorMessage);
            }
            else {
                res.status(400);
                ErrorMessage errorMessage = new ErrorMessage("Error: bad request");
                return gson.toJson(errorMessage);
            }
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
            res.status(401);
            ErrorMessage errorMessage = new ErrorMessage("Error: unauthorized");
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
        LogoutRequest logoutRequest = gson.fromJson(req.body(), LogoutRequest.class);

        try {
            LogoutService.logout(logoutRequest);
        } catch(DataAccessException ex) {
            res.status(401);
            ErrorMessage errorMessage = new ErrorMessage("Error: unauthorized");
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
        ListGamesRequest listGamesRequest = gson.fromJson(req.body(), ListGamesRequest.class);
        ListGamesResult listGamesResult;

        try {
            listGamesResult = ListGamesService.listGames(listGamesRequest);
        } catch(DataAccessException ex) {
            res.status(401);
            ErrorMessage errorMessage = new ErrorMessage("Error: unauthorized");
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
        CreateGameRequest createGameRequest = gson.fromJson(req.body(), CreateGameRequest.class);
        CreateGameResult createGameResult;

        try {
            createGameResult = CreateGameService.createGame(createGameRequest);
        } catch(DataAccessException ex) {
            if(ex.equals(new DataAccessException("Error: unauthorized"))) {
                res.status(401);
                ErrorMessage errorMessage = new ErrorMessage("Error: unauthorized");
                return gson.toJson(errorMessage);
            }
            else {
                res.status(400);
                ErrorMessage errorMessage = new ErrorMessage("Error: bad request");
                return gson.toJson(errorMessage);
            }
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return gson.toJson(createGameResult);
    }

    public static String joinGame(Request req, Response res) {
        JoinGameRequest joinGameRequest = gson.fromJson(req.body(), JoinGameRequest.class);

        try {
            JoinGameService.joinGame(joinGameRequest);
        } catch(DataAccessException ex) {
            if(ex.equals(new DataAccessException("Error: unauthorized"))) {
                res.status(401);
                ErrorMessage errorMessage = new ErrorMessage("Error: unauthorized");
                return gson.toJson(errorMessage);
            }
            else if(ex.equals(new DataAccessException("Error: already taken"))) {
                res.status(403);
                ErrorMessage errorMessage = new ErrorMessage("Error: already taken");
                return gson.toJson(errorMessage);
            }
            else {
                res.status(400);
                ErrorMessage errorMessage = new ErrorMessage("Error: bad request");
                return gson.toJson(errorMessage);
            }
        } catch(Error error) {
            res.status(500);
            ErrorMessage errorMessage = new ErrorMessage("Error: " + error.getMessage());
            return gson.toJson(errorMessage);
        }

        res.status(200);
        return "{}";
    }
}
