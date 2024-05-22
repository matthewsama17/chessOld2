package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class helps to manage En Passants
 */

public class EnPassanter {
    Collection<ChessMove> enPassantMoves = new HashSet<ChessMove>();

    Collection<ChessMove> getEnPassantMoves() {
        return enPassantMoves;
    }

    void updateEnPassantMoves(ChessBoard board, ChessMove move) {
        enPassantMoves = new HashSet<ChessMove>();

        int startRow = move.getStartPosition().getRow();
        int startCol = move.getStartPosition().getColumn();
        int endRow = move.getEndPosition().getRow();
        int endCol = move.getEndPosition().getColumn();

        if(isDoubleJump(board,move)) {

            ChessPosition otherPosition = new ChessPosition(endRow,endCol+1);
            if(otherPosition.onBoard()
                    && board.getPiece(otherPosition) != null
                    && board.getPiece(otherPosition).getPieceType() == ChessPiece.PieceType.PAWN
                    && board.getPieceColor(otherPosition) != board.getPieceColor(move.getEndPosition())) {
                ChessPosition target = new ChessPosition((startRow+endRow)/2,endCol);
                enPassantMoves.add(new ChessMove(otherPosition,target,null));
            }

            otherPosition = new ChessPosition(endRow,endCol-1);
            if(otherPosition.onBoard()
                    && board.getPiece(otherPosition) != null
                    && board.getPiece(otherPosition).getPieceType() == ChessPiece.PieceType.PAWN
                    && board.getPieceColor(otherPosition) != board.getPieceColor(move.getEndPosition())) {
                ChessPosition target = new ChessPosition((startRow+endRow)/2,endCol);
                enPassantMoves.add(new ChessMove(otherPosition,target,null));
            }
        }
    }

    private boolean isDoubleJump(ChessBoard board, ChessMove move) {
        ChessPiece piece = board.getPiece(move.getEndPosition());
        if(piece != null && piece.getPieceType() == ChessPiece.PieceType.PAWN) {
            int startRow = move.getStartPosition().getRow();
            int endRow = move.getEndPosition().getRow();

            if(startRow == 2 && endRow == 4) {
                return true;
            }

            if(startRow == 7 && endRow == 5) {
                return true;
            }
        }
        return false;
    }

    void performEnPassant(ChessBoard board, ChessMove move) {
        if(!enPassantMoves.contains(move)) {
            return;
        }
        int row = move.getStartPosition().getRow();
        int col = move.getEndPosition().getColumn();
        board.addPiece(new ChessPosition(row,col),null);
    }
}
