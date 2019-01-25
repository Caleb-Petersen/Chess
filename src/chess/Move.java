/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;
/**
 * 
 * @author Caleb
 */
public class Move {
    public Piece piece;
    public Square destination;
    
    public Move(Piece initPiece, Square initSquare) {
        this.piece = initPiece;
        this.destination = initSquare;
    }
    public boolean moveIsEnpassant() {
        /**
         * @param none
         * @returns a boolean indicating whether or not a move is enpassant
         */
        
        //Algorithm to implement
        //Check to see if the piece moved was a pawn
        //check to see if the move is valid
        //Check to see if a diagonal move was made
        //Check to see if there is no piece on the destination destination
        //If all of those checks are true, return true, if not, return false
        
        int direction = 1;
        if(this.piece.pieceColour.equals("black")) {
            direction = -1;
        }
        
        if(this.piece.pieceType.equals("pawn")) {
            //check to see if the destination row is correct for the colour of piece (3rd rank for black pieces, 6th for white)
            if((this.piece.pieceColour.equals("white") && this.destination.y == 2) || (this.piece.pieceColour.equals("black") && this.destination.y == 5)) {
                //check to see that the pawn was one column removed from the destination destination (e.g the e file to the f file)
                if(Math.abs(this.piece.location.x - this.destination.x) == 1) {
                    //check to see if the piece on the destination above the destination destination contains a pawn
                    for(int i=0; i< Main.pieces.size(); i++) {
                        if(Main.pieces.get(i).location.x == this.destination.x && Main.pieces.get(i).location.y == this.destination.y + direction) {
                            
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isCastling() {
        /**
         * Function purpose: To determine if a move involves castling
         * NOTE: as of October 20, 2018, this function is incomplete, and 
         * views every king move as an attempt to castle.
         */
        
        //Algorithm to implement
        //Check to see if the piece moved is a king
        //Check to see if the king was moved two squares
        //Check to see if the move is valid
        //If all are true, return true, if not, return false
        
        if(this.piece.pieceType.equals("king")) {
            if(Math.abs(this.piece.location.x - this.destination.x) == 2) {
                if(this.isValidMove()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isValidMove() {
        /**
         * @param none
         * @returns whether or not a move is valid
         */
        switch(this.piece.pieceType) {
            case KING:
                return Validation.isKing(Main.pieces, this);
                
            case QUEEN:
                return Validation.isDiagonal(Main.pieces, this) && Validation.isStraight(Main.pieces, this);
                
            case ROOK:
                return Validation.isStraight(Main.pieces, this);
                
            case BISHOP:
                return Validation.isDiagonal(Main.pieces, this);
     
            case KNIGHT:
                return Validation.isKnight(Main.pieces, this);
                
            default:
                return Validation.isPawn(Main.pieces, this);
        }
    }
    
    public void executeMove() {
        /**
         * @param none
         * @returns nothing
         * Function purpose: To execute a move
         */
        
        //If a piece was captured, remove it from the main array, 
        //add it to the deleted pieces array for graphical purpososes
        for(int i=0; i<Main.pieces.size(); i++) {
            if(Main.pieces.get(i).location.x == this.destination.x && Main.pieces.get(i).location.y == this.destination.y) {
                Main.deletedPieces.add(Main.pieces.get(i));
                Main.pieces.remove(i);
            }
        }
        for(int i=0; i< Main.pieces.size(); i++) {
            if(Main.pieces.get(i) == this.piece) {
                Main.pieces.get(i).hasMoved = true;
                Main.pieces.get(i).location.x = this.destination.x;
                Main.pieces.get(i).location.y = this.destination.y;
                
                if(this.isCastling()) {
                    System.out.println("need to find associated rook, and move it too");
                }
            }
        }
    }
}
