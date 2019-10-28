/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Caleb
 */
public class Move {
    public Piece piece;
    public Square destination;
    
    public Move(Piece p, Square s) {
        this.piece = p;
        this.destination = s;
    }
    public boolean moveIsEnpassant(Position position) {
        /**
         * @param none
         * @returns a boolean indicating whether or not a move is enpassant
         * @NOTE: Function doesn't care about the validity of the move, it only gives information about the nature of the move
         */
        
        //Algorithm to implement
        //Check to see if the piece moved was a pawn
        //check to see if the move is valid
        //Check to see if a diagonal move was made
        //Check to see that the destination square is empty
        //Check to see that the pawn being captured (via enpassant) is actually there
        //If all of those checks are true, return true, if not, return false
        
        int direction = 1;
        if(this.piece.pieceColour == Piece.COLOUR.BLACK) {
            direction = -1;
        }
        
        if(this.piece.pieceType == Piece.TYPE.PAWN) {
            //check to see if the destination row is correct for the colour of piece (3rd rank for black pieces, 6th for white)
            if((this.piece.pieceColour == Piece.COLOUR.WHITE && this.destination.y == 2) || (this.piece.pieceColour == Piece.COLOUR.BLACK && this.destination.y == 5)) {
                //check to see that the pawn was one column removed from the destination destination (e.g the e file to the f file)
                if(Math.abs(this.piece.location.x - this.destination.x) == 1) {
                    //Check to see if the piece on the destination above the destination destination contains a pawn
                    if(this.destination.isPieceOnSquare(position) == false) { //Check may not be necessary as we don't need to know about validity
                        //Check to ensure that the enpassant is actually capturing a piece
                        Square square = new Square(this.destination.x, (this.destination.y - direction));
                        if(square.pieceOnSquare(position) == Piece.TYPE.PAWN) {
                            return true; //Assume that this move is a valid move
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isCastling(Position position) {
        /**
         * Function purpose: To determine if a move involves castling
         * NOTE:this function must be used alongside the isValidMove function
         * as it does not check if a move is legal. If a move is legal and
         * isCastling returns true, it is know that a move is castling
         */
        
        //Algorithm to implement
        //Check to see if the piece moved is a king
        //Check to see if the king was moved two squares
        //If all are true, return true, if not, return false
        
        if(this.piece.pieceType == Piece.TYPE.KING) {
            if(Math.abs(this.piece.location.x - this.destination.x) == 2) {
                if(this.isValidMove(position)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isValidMove(Position position) {
        /**
         * @param none
         * @returns whether or not a move is valid
         */
        System.out.println("CHecking to see if a move is valid for piece " + this.piece.pieceType);
        switch(this.piece.pieceType) {
            case KING:
                return Validation.isKing(position, this);
                
            case QUEEN:
                return Validation.isDiagonal(position, this) && Validation.isStraight(position, this);
                
            case ROOK:
                return Validation.isStraight(position, this);
                
            case BISHOP:
                return Validation.isDiagonal(position, this);
     
            case KNIGHT:
                return Validation.isKnight(position, this);
                
            default:
                return Validation.isPawn(position, this);
        }
    }
    
    public void executeMove(Position position) {
        /**
         * @param none
         * @returns nothing
         * Function purpose: To execute a move
         */
        
        //If a piece was captured, remove it from the main array, 
        //add it to the deleted pieces array for graphical purpososes
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).location.x == this.destination.x && position.boardPosition.get(i).location.y == this.destination.y) {
                position.boardPosition.remove(i);
            }
        }
        for(int i=0; i< position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i) == this.piece) {
                position.boardPosition.get(i).hasMoved = true;
                position.boardPosition.get(i).location.x = this.destination.x;
                position.boardPosition.get(i).location.y = this.destination.y;
                
                if(this.isCastling(position)) {
                    System.out.println("need to find associated rook, and move it too");
                }
            }
        }
    }
}
