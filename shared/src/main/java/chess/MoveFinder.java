package chess;

import java.util.Collection;
import java.util.Objects;

public class MoveFinder {

    /**
     * Calculates all the positions a white pawn can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> whitePawnMoves(ChessBoard board, ChessPosition myPosition) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a black pawn can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> blackPawnMoves(ChessBoard board, ChessPosition myPosition) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a rook can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a knight can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a bishop can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a queen can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        Collection<ChessMove> moves = rookMoves(board,myPosition,myColor);
        moves.addAll(bishopMoves(board,myPosition,myColor));
        return moves;
    }

    /**
     * Calculates all the positions a king can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        throw new RuntimeException("Not implemented");
    }
}
