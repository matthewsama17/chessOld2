package chess;

import java.util.Objects;
import java.util.Collection;
import java.util.HashSet;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }

    /**
     * @return Collection of ChessMoves with all possible pawn
     * promotions with a given startPosition and endPosition;
     */
    public static Collection<ChessMove> allPromotions(ChessPosition startPosition, ChessPosition endPosition) {
        var moves = new HashSet<ChessMove>();
        moves.add(new ChessMove(startPosition,endPosition, ChessPiece.PieceType.QUEEN));
        moves.add(new ChessMove(startPosition,endPosition, ChessPiece.PieceType.ROOK));
        moves.add(new ChessMove(startPosition,endPosition, ChessPiece.PieceType.KNIGHT));
        moves.add(new ChessMove(startPosition,endPosition, ChessPiece.PieceType.BISHOP));
        return moves;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() { return startPosition; }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() { return endPosition; }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() { return promotionPiece; }

    @Override
    public String toString() {
        if(promotionPiece == null) {
            return "ChessMove{" +
                    "startPosition=" + startPosition +
                    ", endPosition=" + endPosition +
                    '}';
        }
        else {
            return "ChessMove{" +
                    "startPosition=" + startPosition +
                    ", endPosition=" + endPosition +
                    ", promotionPiece=" + promotionPiece +
                    '}';
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessMove chessMove)) return false;

        return Objects.equals(startPosition, chessMove.startPosition) && Objects.equals(endPosition, chessMove.endPosition) && promotionPiece == chessMove.promotionPiece;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(startPosition);
        result = 31 * result + Objects.hashCode(endPosition);
        result = 31 * result + Objects.hashCode(promotionPiece);
        return result;
    }
}
