package chess;

import java.util.Collection;
import java.util.HashSet;

public class MoveFinder {

    /**
     * Calculates all the positions a pawn can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        if(myColor == ChessGame.TeamColor.WHITE) {
            return whitePawnMoves(board,myPosition);
        }
        else if(myColor == ChessGame.TeamColor.BLACK) {
            return blackPawnMoves(board,myPosition);
        }
        return null;
    }

    /**
     * Calculates all the positions a white pawn can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> whitePawnMoves(ChessBoard board, ChessPosition myPosition) {
        var moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //onward
        otherPosition = new ChessPosition(row+1,col);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == null) {
            if (row == 7){
                moves.addAll(ChessMove.allPromotions(myPosition,otherPosition));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }

            otherPosition = new ChessPosition(row+2,col);
            if(row == 2 && board.getPieceColor(otherPosition) == null) {
                moves.add(new ChessMove(myPosition,otherPosition,null));
            }
        }

        //right capture
        otherPosition = new ChessPosition(row+1,col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == ChessGame.TeamColor.BLACK) {
            if (row == 7){
                moves.addAll(ChessMove.allPromotions(myPosition,otherPosition));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }
        }

        //left capture
        otherPosition = new ChessPosition(row+1,col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == ChessGame.TeamColor.BLACK) {
            if (row == 7){
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.KNIGHT));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }
        }

        return moves;
    }

    /**
     * Calculates all the positions a black pawn can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> blackPawnMoves(ChessBoard board, ChessPosition myPosition) {
        var moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //onward
        otherPosition = new ChessPosition(row-1,col);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == null) {
            if (row == 2){
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.KNIGHT));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }

            otherPosition = new ChessPosition(row-2,col);
            if(row == 7 && board.getPieceColor(otherPosition) == null) {
                moves.add(new ChessMove(myPosition,otherPosition,null));
            }
        }

        //right capture
        otherPosition = new ChessPosition(row-1,col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == ChessGame.TeamColor.WHITE) {
            if (row == 2){
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.KNIGHT));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }
        }

        //left capture
        otherPosition = new ChessPosition(row-1,col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == ChessGame.TeamColor.WHITE) {
            if (row == 2){
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.KNIGHT));
            }
            else {
                moves.add(new ChessMove(myPosition, otherPosition, null));
            }
        }

        return moves;
    }

    /**
     * Calculates all the positions a rook can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();

        //forward
        moves.addAll(slideMoves(1,0,board,myPosition,myColor));

        //backward
        moves.addAll(slideMoves(-1,0,board,myPosition,myColor));

        //right
        moves.addAll(slideMoves(0,1,board,myPosition,myColor));

        //left
        moves.addAll(slideMoves(0,-1,board,myPosition,myColor));

        return moves;
    }

    /**
     * Calculates all the positions a knight can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();

        //forward right
        moves.addAll(jumpMoves(2,1,board,myPosition,myColor));

        //right forward
        moves.addAll(jumpMoves(1,2,board,myPosition,myColor));

        //right backward
        moves.addAll(jumpMoves(-1,2,board,myPosition,myColor));

        //backward right
        moves.addAll(jumpMoves(-2,1,board,myPosition,myColor));

        //backward left
        moves.addAll(jumpMoves(-2,-1,board,myPosition,myColor));

        //left backward
        moves.addAll(jumpMoves(-1,-2,board,myPosition,myColor));

        //left forward
        moves.addAll(jumpMoves(1,-2,board,myPosition,myColor));

        //forward left
        moves.addAll(jumpMoves(2,-1,board,myPosition,myColor));

        return moves;
    }

    /**
     * Calculates all the positions a bishop can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();

        //forward right
        moves.addAll(slideMoves(1,1,board,myPosition,myColor));

        //backward right
        moves.addAll(slideMoves(-1,1,board,myPosition,myColor));

        //backward left
        moves.addAll(slideMoves(-1,-1,board,myPosition,myColor));

        //forward left
        moves.addAll(slideMoves(1,-1,board,myPosition,myColor));

        return moves;
    }

    /**
     * Calculates all the positions a queen can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();
        moves.addAll(rookMoves(board,myPosition,myColor));
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
        var moves = new HashSet<ChessMove>();

        //forward
        moves.addAll(jumpMoves(1,0,board,myPosition,myColor));

        //backward
        moves.addAll(jumpMoves(-1,0,board,myPosition,myColor));

        //right
        moves.addAll(jumpMoves(0,1,board,myPosition,myColor));

        //left
        moves.addAll(jumpMoves(0,-1,board,myPosition,myColor));

        //forward right
        moves.addAll(jumpMoves(1,1,board,myPosition,myColor));

        //backward right
        moves.addAll(jumpMoves(-1,1,board,myPosition,myColor));

        //backward left
        moves.addAll(jumpMoves(-1,-1,board,myPosition,myColor));

        //forward left
        moves.addAll(jumpMoves(1,-1,board,myPosition,myColor));

        return moves;
    }

    /**
     * Calculates the positions a piece can move to by sliding in a straight or
     * diagonal line
     * Used by rookMoves, bishopMoves, and queenMoves
     *
     * @return Collection of moves
     */
    private static Collection<ChessMove> slideMoves(int rowStep, int colStep, ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        for(otherPosition = new ChessPosition(row+rowStep,col+colStep); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()+rowStep,otherPosition.getColumn()+colStep)) {
            ChessGame.TeamColor otherColor = board.getPieceColor(otherPosition);
            if(otherColor == myColor) {
                break;
            }
            else if(otherColor == null) {
                moves.add(new ChessMove(myPosition,otherPosition,null));
            }
            else {
                moves.add(new ChessMove(myPosition,otherPosition,null));
                break;
            }
        }

        return moves;
    }

    /**
     * Calculates if a piece can move by jumping
     * Used by kingMoves and knightMoves
     *
     * @return Collection of moves
     */
    private static Collection<ChessMove> jumpMoves(int rowStep, int colStep, ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        var moves = new HashSet<ChessMove>();
        ChessPosition otherPosition = new ChessPosition(myPosition.getRow()+rowStep, myPosition.getColumn()+colStep);

        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        return moves;
    }
}