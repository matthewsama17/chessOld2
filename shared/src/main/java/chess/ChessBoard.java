package chess;

import java.util.Arrays;
import java.util.Collection;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard implements Cloneable {

    private ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() { }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) { squares[position.getRow()-1][position.getColumn()-1] = piece; }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) { return squares[position.getRow()-1][position.getColumn()-1]; }

    /**
     * Gets the color of a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the color of the piece at the position, or null if no
     * piece is at that position
     */
    public ChessGame.TeamColor getPieceColor(ChessPosition position) {
        ChessPiece piece = getPiece(position);
        if(piece == null) { return null; }
        return piece.getTeamColor();
    }

    /**
     * makes a move
     *
     * @param move the move to be made
     */
    public void makeMove(ChessMove move) {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        ChessPiece.PieceType pieceType = move.getPromotionPiece();
        if(pieceType == null) { pieceType = getPiece(startPosition).getPieceType(); }
        ChessGame.TeamColor teamColor = getPieceColor(startPosition);

        addPiece(startPosition, null);
        addPiece(endPosition, new ChessPiece(teamColor,pieceType));
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(ChessGame.TeamColor teamColor) {
        ChessPosition kingPosition = findKing(teamColor);
        if(kingPosition == null) { return false; }

        for(int r = 1; r <= 8; r++) {
            for(int c = 1; c <= 8; c++) {
                ChessPosition startPosition = new ChessPosition(r,c);
                if(getPiece(startPosition) == null) { continue; }

                Collection<ChessMove> moves = getPiece(startPosition).pieceMoves(this,startPosition);
                for(ChessMove move : moves) {
                    if(move.getEndPosition().equals(kingPosition)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Finds the king of one of the teams
     *
     * @param teamColor which team to find the king of
     * @return ChessPosition the position of the king
     */
    public ChessPosition findKing(ChessGame.TeamColor teamColor) {
        for(int r = 1; r <= 8; r++) {
            for(int c = 1; c <= 8; c++) {
                ChessPosition square = new ChessPosition(r,c);
                ChessPiece piece = getPiece(square);
                if(getPieceColor(square) == teamColor && piece.getPieceType() == ChessPiece.PieceType.KING) { return square; }
            }
        }
        return null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //White Back Row
        squares[0][0] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        squares[0][1] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        squares[0][2] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        squares[0][3] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        squares[0][4] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        squares[0][5] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        squares[0][6] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        squares[0][7] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);

        //Black Back Row
        squares[7][0] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        squares[7][1] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        squares[7][2] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        squares[7][3] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        squares[7][4] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        squares[7][5] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        squares[7][6] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        squares[7][7] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);

        //Pawns
        for(int c = 0; c < 8; c++) {
            squares[1][c] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            squares[6][c] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        }

        //No Man's Land
        for(int c = 0; c < 8; c++) {
            for(int r = 2; r < 6; r++) {
                squares[r][c] = null;
            }
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;

        return Arrays.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

    @Override
    public ChessBoard clone() {
        ChessBoard result = new ChessBoard();

        for(int r = 0; r <= 7; r++) {
            for(int c = 0; c <= 7; c++) {
                result.squares[r][c] = squares[r][c];
            }
        }

        return result;
    }
}
