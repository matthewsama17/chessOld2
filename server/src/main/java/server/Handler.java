package server;

import com.google.gson.Gson;
import service.*;
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
        return "creates a new user";
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
