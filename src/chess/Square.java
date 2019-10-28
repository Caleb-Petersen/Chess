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
public class Square {
    public int y;
    public int x;
    
    public Square(int initX,int initY) {
        y = initY;
        x = initX;
    }
    
    public boolean isPieceOnSquare(Position position) {
        /**
         * @param position an array of pieces showing the current state of the position
         * @returns boolean indicating whether or not the piece is on a square
         */
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).location.x == this.x && position.boardPosition.get(i).location.y == this.y) {
                return true;
            }
        }
        return false;
    }
    public Piece.TYPE pieceOnSquare(Position position) {
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).location.x == this.x && position.boardPosition.get(i).location.y == this.y) {
                return position.boardPosition.get(i).pieceType;
            }
        }
        //TODO: Get a return type that is not null (empty piece type in enum maybe?)
        return null; //shouldn't get here if called, always call isPieceOnSquare first or use in boolean check
    }
    public boolean squareControlled(Position position, Piece.COLOUR playerColour) {
        /**
         * @param position contains the current state of the board
         * @param playerColour is the string containing the colour of the player's pieces
         * @returns boolean indicating whether or not the opposing team controls a given square
         */
        
        Move move = null;
        for(int i=0; i<position.boardPosition.size(); i++) {
            move = new Move(position.boardPosition.get(i), this);
            //the piece must be of opposite colour
            if(move.piece.pieceColour.equals(playerColour) == false) {
                if(move.isValidMove(position)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
