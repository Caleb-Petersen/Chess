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
    public int x;
    public int y;
    
    public Move(Piece initPiece, int initX, int initY) {
        this.piece = initPiece;
        this.x = initX;
        this.y = initY;
    }
    public boolean moveIsEnpassant() {
        /**
         * @param none
         * @returns a boolean indicating whether or not a move is enpassant
         */
        
        int direction = 1;
        if(this.piece.pieceColour.equals("black")) {
            direction = -1;
        }
        
        if(this.piece.pieceType.equals("pawn")) {
            //check to see if the destination row is correct for the colour of piece (3rd rank for black pieces, 6th for white)
            if((this.piece.pieceColour.equals("white") && this.y == 2) || (this.piece.pieceColour.equals("black") && this.y == 5)) {
                //check to see that the pawn was one column removed from the destination square (e.g the e file to the f file)
                if(Math.abs(this.piece.x - this.x) == 1) {
                    //check to see if the piece on the square above the destination square contains a pawn
                    for(int i=0; i< Main.pieces.size(); i++) {
                        if(Main.pieces.get(i).x == x && Main.pieces.get(i).y == y + direction) {
                            
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean moveIsCastling() {
        /**
         * Function purpose: To determine if a move involves castling
         * NOTE: as of October 20, 2018, this function is incomplete, and 
         * views every king move as an attempt to castle.
         */
        if(this.piece.pieceType.equals("king")) {
            return true;
        }
        return false;
    }
    public boolean isValidMove() {
        /**
         * @param none
         * @returns whether or not a move is valid
         */
        switch(this.piece.pieceType) {
            case "king":
                return Validation.isKing(this);
                
            case "queen":
                return Validation.isDiagonal(this) && Validation.isStraight(this);
                
            case "rook":
                return Validation.isStraight(this);
                
            case "bishop":
                return Validation.isDiagonal(this);
     
            case "knight":
                return Validation.isKnight(this);
                
            default:
                return Validation.isPawn(this);
        }
    }
    
    public void executeMove() {
        /**
         * @param none
         * @returns nothing
         * Function purpose: To execute a move
         */
        //If a piece was captured, remove it.
        for(int i=0; i<Main.pieces.size(); i++) {
            if(Main.pieces.get(i).x == this.x && Main.pieces.get(i).y == y) {
                Main.pieces.remove(i);
            }
        }
        for(int i=0; i< Main.pieces.size(); i++) {
            if(Main.pieces.get(i) == this.piece) {
                System.out.println("CHANGING MAIN PIECE");
                System.out.println("X is: " + this.x);
                System.out.println("Y is: " + this.y);
                Main.pieces.get(i).hasMoved = true;
                Main.pieces.get(i).x = this.x;
                Main.pieces.get(i).y = this.y;
                System.out.println(Main.pieces.get(i).y);
            }
        }
        
        
    }
}
