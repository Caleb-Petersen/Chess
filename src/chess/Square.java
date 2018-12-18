/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

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
    
    boolean pieceOnSquare(ArrayList<Piece> boardPosition) {
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
}
