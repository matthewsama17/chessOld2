package result;

import model.GameDataSimple;
import java.util.List;

public record ListGamesResult(List<GameDataSimple> games) { }