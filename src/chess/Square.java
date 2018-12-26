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
            if(boardPosition.get(i).x == this.x && boardPosition.get(i).y == this.y) {
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
        
        Piece piece = null;
        for(int i=0; i<boardPosition.size(); i++) {
            piece = boardPosition.get(i);
            //the piece must be of opposite colour
            if(piece.pieceColour.equals(playerColour) == false) {
                //check all of the destinations that the pieces have to see if they match the square 
                for(int j=0; j<piece.possibleDestinations.size(); j++) {
                    if(piece.possibleDestinations.get(j).x == this.x && piece.possibleDestinations.get(j).y == this.y) {
                        return true;
                    }
                }
            }

        }
            
        
        return false;
    }
}
