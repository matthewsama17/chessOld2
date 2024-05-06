package chess;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
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
        Set<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //onward
        otherPosition = new ChessPosition(row+1,col);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) == null) {
            if (row == 7){
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.ROOK));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition,otherPosition, ChessPiece.PieceType.KNIGHT));
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
        Set<ChessMove> moves = new HashSet<ChessMove>();
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
        Set<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //forward
        for(otherPosition = new ChessPosition(row+1,col); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()+1,otherPosition.getColumn())) {
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

        //backward
        for(otherPosition = new ChessPosition(row-1,col); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()-1,otherPosition.getColumn())) {
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

        //right
        for(otherPosition = new ChessPosition(row,col+1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow(),otherPosition.getColumn()+1)) {
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

        //left
        for(otherPosition = new ChessPosition(row,col-1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow(),otherPosition.getColumn()-1)) {
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
     * Calculates all the positions a knight can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public static Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor myColor) {
        Set<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //forward right
        otherPosition = new ChessPosition(row+2, col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //right forward
        otherPosition = new ChessPosition(row+1, col+2);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //right backward
        otherPosition = new ChessPosition(row-1, col+2);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //backward right
        otherPosition = new ChessPosition(row-2, col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //backward left
        otherPosition = new ChessPosition(row-2, col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //left backward
        otherPosition = new ChessPosition(row-1, col-2);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //left forward
        otherPosition = new ChessPosition(row+1, col-2);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //forward left
        otherPosition = new ChessPosition(row+2, col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

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
        Set<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //forward right
        for(otherPosition = new ChessPosition(row+1,col+1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()+1,otherPosition.getColumn()+1)) {
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

        //backward right
        for(otherPosition = new ChessPosition(row-1,col+1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()-1,otherPosition.getColumn()+1)) {
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

        //backward left
        for(otherPosition = new ChessPosition(row-1,col-1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()-1,otherPosition.getColumn()-1)) {
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

        //forward left
        for(otherPosition = new ChessPosition(row+1,col-1); otherPosition.onBoard(); otherPosition = new ChessPosition(otherPosition.getRow()+1,otherPosition.getColumn()-1)) {
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
        Set<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition otherPosition;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //forward
        otherPosition = new ChessPosition(row+1, col);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //backward
        otherPosition = new ChessPosition(row-1, col);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //right
        otherPosition = new ChessPosition(row, col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //left
        otherPosition = new ChessPosition(row, col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //forward right
        otherPosition = new ChessPosition(row+1, col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //backward right
        otherPosition = new ChessPosition(row-1, col+1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //backward left
        otherPosition = new ChessPosition(row-1, col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        //forward left
        otherPosition = new ChessPosition(row+1, col-1);
        if(otherPosition.onBoard() && board.getPieceColor(otherPosition) != myColor) {
            moves.add(new ChessMove(myPosition,otherPosition,null));
        }

        return moves;
    }
}