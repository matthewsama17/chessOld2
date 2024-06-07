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
        return "begins a session";
    }

    public static String logout(Request req, Response res) {
        return "ends a session";
    }

    public static String listGames(Request req, Response res) {
        return "returns a list of all the games";
    }

    public static String createGame(Request req, Response res) {
        return "creates a new game";
    }

    public static String joinGame(Request req, Response res) {
        return "adds the user as a player in a game";
    }
}
