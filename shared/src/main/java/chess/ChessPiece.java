package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() { return pieceColor; }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() { return type; }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(type == PieceType.PAWN && pieceColor == ChessGame.TeamColor.WHITE) {
            return MoveFinder.whitePawnMoves(board,myPosition);
        }
        else if(type == PieceType.PAWN && pieceColor == ChessGame.TeamColor.BLACK) {
            return MoveFinder.blackPawnMoves(board,myPosition);
        }
        else if(type == PieceType.ROOK) {
            return MoveFinder.rookMoves(board,myPosition,pieceColor);
        }
        else if(type == PieceType.KNIGHT) {
            return MoveFinder.knightMoves(board,myPosition,pieceColor);
        }
        else if(type == PieceType.BISHOP) {
            return MoveFinder.bishopMoves(board,myPosition,pieceColor);
        }
        else if(type == PieceType.QUEEN) {
            return MoveFinder.queenMoves(board,myPosition,pieceColor);
        }
        else if(type == PieceType.KING) {
            return MoveFinder.kingMoves(board,myPosition,pieceColor);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;

        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(pieceColor);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }
}
