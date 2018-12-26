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
    
    public boolean pieceOnSquare(ArrayList<Piece> boardPosition) {
        /**
         * @param boardPostion an array of pieces showing the current state of the position
         * @returns boolean indicating whether or not the piece is on a square
         */
        for(int i=0; i<boardPosition.size(); i++) {
            if(boardPosition.get(i).location.x == this.x && boardPosition.get(i).location.y == this.y) {
                return true;
            }
        }
        return false;
    }
    public boolean squareControlled(ArrayList<Piece> boardPosition, String playerColour) {
        /**
         * @param boardPosition contains the current state of the board
         * @param playerColour is the string containing the colour of the player's pieces
         * @returns boolean indicating whether or not the opposing team controls a given square
         */
        
        Move move = null;
        for(int i=0; i<boardPosition.size(); i++) {
            move = new Move(boardPosition.get(i), this);
            //the piece must be of opposite colour
            if(move.piece.pieceColour.equals(playerColour) == false) {
                if(move.isValidMove()) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
