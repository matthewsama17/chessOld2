package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class helps to manage Castling
 */
public class Castler {

    private boolean whiteCanCastleLeft = true;
    private boolean whiteCanCastleRight = true;
    private boolean blackCanCastleLeft = true;
    private boolean blackCanCastleRight = true;

    public static ChessPosition whiteKingStart = new ChessPosition(1,5);
    public static ChessPosition blackKingStart = new ChessPosition(8,5);

    public static ChessPosition whiteLeftRookStart = new ChessPosition(1,1);
    public static ChessPosition whiteRightRookStart = new ChessPosition(1,8);
    public static ChessPosition blackLeftRookStart = new ChessPosition(8,1);
    public static ChessPosition blackRightRookStart = new ChessPosition(8,8);

    public static ChessMove whiteCastleLeft = new ChessMove(whiteKingStart,new ChessPosition(1,3),null);
    public static ChessMove whiteCastleRight = new ChessMove(whiteKingStart,new ChessPosition(1,7),null);
    public static ChessMove blackCastleLeft = new ChessMove(blackKingStart,new ChessPosition(8,3),null);
    public static ChessMove blackCastleRight = new ChessMove(blackKingStart,new ChessPosition(8,7),null);


    public Castler() { }

    public void castle(ChessBoard board, ChessMove move) {
        if(whiteCanCastleLeft && move.equals(whiteCastleLeft)) {
            board.makeMove(new ChessMove(whiteLeftRookStart,new ChessPosition(1,4),null));
        }
        if(whiteCanCastleRight && move.equals(whiteCastleRight)) {
            board.makeMove(new ChessMove(whiteRightRookStart,new ChessPosition(1,6),null));
        }
        if(blackCanCastleLeft && move.equals(blackCastleLeft)) {
            board.makeMove(new ChessMove(blackLeftRookStart,new ChessPosition(8,4),null));
        }
        if(blackCanCastleRight && move.equals(blackCastleRight)) {
            board.makeMove(new ChessMove(blackRightRookStart,new ChessPosition(8,6),null));
        }
    }

    /**
     * This method checks if a move moves a king or a rook from its
     * starting position, and updates the possible castle moves accordingly
     *
     * @param move to check
     */
    public void checkIfCastleLost(ChessMove move) {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();

        if(endPosition.equals(whiteKingStart)) {
            whiteCanCastleLeft = false;
            whiteCanCastleRight = false;
        }
        if(endPosition.equals(blackKingStart)) {
            blackCanCastleLeft = false;
            blackCanCastleRight = false;
        }
        if(endPosition.equals(whiteLeftRookStart)) {
            whiteCanCastleLeft = false;
        }
        if(endPosition.equals(whiteRightRookStart)) {
            whiteCanCastleRight = false;
        }
        if(endPosition.equals(blackLeftRookStart)) {
            blackCanCastleLeft = false;
        }
        if(endPosition.equals(blackRightRookStart)) {
            blackCanCastleRight = false;
        }
    }

    /**
     * Gets valid castle moves
     *
     * @param board the board to check
     * @param startPosition the position to check moves from
     * @return a collecttion of all of the valid castle moves on the given
     * board starting from the given position
     */
    public Collection<ChessMove> validCastleMoves(ChessBoard board,ChessPosition startPosition) {
        var moves = new HashSet<ChessMove>();

        //White castles
        if(startPosition.equals(whiteKingStart) && !board.isInCheck(ChessGame.TeamColor.WHITE)) {

            //Left castle
            if(whiteCanCastleLeft
                    && board.getPiece(whiteLeftRookStart) != null
                    && board.getPiece(new ChessPosition(1,2)) == null
                    && board.getPiece(new ChessPosition(1,3)) == null
                    && board.getPiece(new ChessPosition(1,4)) == null
                    && board.getPiece(whiteKingStart) != null) {

                ChessBoard tempBoard = board.clone();
                tempBoard.makeMove(new ChessMove(whiteKingStart,new ChessPosition(1,4),null));
                if(!tempBoard.isInCheck(ChessGame.TeamColor.WHITE)) {
                    tempBoard.makeMove(new ChessMove(new ChessPosition(1,4),new ChessPosition(1,3),null));
                    if(!tempBoard.isInCheck(ChessGame.TeamColor.WHITE)) {
                        moves.add(whiteCastleLeft);
                    }
                }
            }

            //Right castle
            if(whiteCanCastleRight
                    && board.getPiece(whiteRightRookStart) != null
                    && board.getPiece(new ChessPosition(1,7)) == null
                    && board.getPiece(new ChessPosition(1,6)) == null
                    && board.getPiece(whiteKingStart) != null) {

                ChessBoard tempBoard = board.clone();
                tempBoard.makeMove(new ChessMove(whiteKingStart,new ChessPosition(1,6),null));
                if(!tempBoard.isInCheck(ChessGame.TeamColor.WHITE)) {
                    tempBoard.makeMove(new ChessMove(new ChessPosition(1,6),new ChessPosition(1,7),null));
                    if(!tempBoard.isInCheck(ChessGame.TeamColor.WHITE)) {
                        moves.add(whiteCastleRight);
                    }
                }
            }
        }

        //Black castles
        if(startPosition.equals(blackKingStart) && !board.isInCheck(ChessGame.TeamColor.BLACK)) {

            //Left castle
            if(blackCanCastleLeft
                    && board.getPiece(blackLeftRookStart) != null
                    && board.getPiece(new ChessPosition(8,2)) == null
                    && board.getPiece(new ChessPosition(8,3)) == null
                    && board.getPiece(new ChessPosition(8,4)) == null
                    && board.getPiece(blackKingStart)  != null) {

                ChessBoard tempBoard = board.clone();
                tempBoard.makeMove(new ChessMove(blackKingStart,new ChessPosition(8,4),null));
                if(!tempBoard.isInCheck(ChessGame.TeamColor.BLACK)) {
                    tempBoard.makeMove(new ChessMove(new ChessPosition(8,4),new ChessPosition(8,3),null));
                    if(!tempBoard.isInCheck(ChessGame.TeamColor.BLACK)) {
                        moves.add(blackCastleLeft);
                    }
                }
            }

            //Right castle
            if(blackCanCastleRight
                    && board.getPiece(blackRightRookStart) != null
                    && board.getPiece(new ChessPosition(8,7)) == null
                    && board.getPiece(new ChessPosition(8,6)) == null
                    && board.getPiece(blackKingStart) != null) {

                ChessBoard tempBoard = board.clone();
                tempBoard.makeMove(new ChessMove(blackKingStart,new ChessPosition(8,6),null));
                if(!tempBoard.isInCheck(ChessGame.TeamColor.BLACK)) {
                    tempBoard.makeMove(new ChessMove(new ChessPosition(8,6),new ChessPosition(8,7),null));
                    if(!tempBoard.isInCheck(ChessGame.TeamColor.BLACK)) {
                        moves.add(blackCastleRight);
                    }
                }
            }
        }

        return moves;
    }
}
