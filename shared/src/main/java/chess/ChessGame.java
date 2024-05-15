package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor turn = TeamColor.WHITE;
    private ChessBoard gameBoard;
    private Castler castler;

    public ChessGame() {
        gameBoard = new ChessBoard();
        gameBoard.resetBoard();
        castler = new Castler();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() { return turn; }

    /**
     * Switches which teams turn it is
     */
    public void updateTeamTurn() {
        if(turn == TeamColor.WHITE) {
            turn = TeamColor.BLACK;
        }
        else if(turn == TeamColor.BLACK) {
            turn = TeamColor.WHITE;
        }
    }

    /**
     * Set's which teams turn it is
     * Use updateTeamTurn instead
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) { turn = team; }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        TeamColor pieceColor = gameBoard.getPieceColor(startPosition);
        if(pieceColor == null) {
            return null;
        }

        Collection<ChessMove> moves = gameBoard.getPiece(startPosition).pieceMoves(gameBoard,startPosition);
        Collection<ChessMove> movesToBeRemoved = new HashSet<ChessMove>();

        for(ChessMove move : moves) {
            ChessBoard testBoard = gameBoard.clone();
            testBoard.makeMove(move);
            if(testBoard.isInCheck(pieceColor)) {
                movesToBeRemoved.add(move);
            }
        }

        moves.removeAll(movesToBeRemoved);
        moves.addAll(castler.validCastleMoves(gameBoard,startPosition));
        return moves;
    }

    /**
     * Determines if the current team has a valid move
     *
     * @return True if the current team has no valid move
     */
    public boolean currentTeamHasNoMoves() { return hasNoMoves(turn); }

    /**
     * Determines if the given team has a valid move
     *
     * @param teamColor which team to check for moves
     * @return True if the specified team has no valid move
     */
    public boolean hasNoMoves(TeamColor teamColor) {
        for(int r = 1; r <= 8; r++) {
            for(int c = 1; c <= 8; c++) {
                if(gameBoard.getPieceColor(new ChessPosition(r,c)) != teamColor) { continue; }
                Collection<ChessMove> moves = validMoves(new ChessPosition(r,c));
                if(!moves.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPosition = move.getStartPosition();
        if(gameBoard.getPieceColor(startPosition) != turn) {
            throw new InvalidMoveException();
        }
        Collection<ChessMove> validMoves = validMoves(startPosition);
        if(!validMoves.contains(move)) {
            throw new InvalidMoveException();
        }
        else {
            castler.castle(gameBoard,move);
            gameBoard.makeMove(move);
            updateTeamTurn();
            castler.checkIfCastleLost(move);
        }
    }

    /**
     * Determines if the current team is in check
     *
     * @return True if the current team is in check
     */
    public boolean currentTeamIsInCheck() { return gameBoard.isInCheck(turn); }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) { return gameBoard.isInCheck(teamColor); }

    /**
     * Determines if the current team is in checkmate
     *
     * @return True if the current team is in checkmate
     */
    public boolean currentTeamIsInCheckmate() { return currentTeamIsInCheck() && currentTeamHasNoMoves(); }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) { return isInCheck(teamColor) && hasNoMoves(teamColor); }

    /**
     * Determines if the current team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @return True if the current team is in stalemate, otherwise false
     */
    public boolean currentTeamIsInStalemate() { return !currentTeamIsInCheck() && currentTeamHasNoMoves(); }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        return !isInCheck(teamColor) && hasNoMoves(teamColor);
    }

    /**
     * Sets this game's chessboard with a given board
     * This is only used for testing
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) { gameBoard = board; }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() { return gameBoard; }
}
