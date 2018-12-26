/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Caleb
 */
public class FEN {
    public String FEN;
    
    public FEN (String initFEN) {
        this.FEN = initFEN;
    }
    public ArrayList<Piece> fenToBoardPosition() {
        ArrayList<Piece> boardPosition = new ArrayList<Piece>();
        
        
        if(this.isValid()) {
            //boardRows is of size 8 if the FEN is valid
            String[] boardRows = this.FEN.split("/");
            for(int i=0; i<8; i++) {
                char [] squares = boardRows[i].toCharArray();
                //NOTE:: this is somewhat messy, and hard to understand -> to be refactored in the future
                //NOTE:: need to add a constructor to the Piece class
                for(int j=0; (j<boardRows[i].length() && (Character.getType(squares[j])) != 12); j++) {
                    if(!Character.isDigit(squares[j])) {
                        //j is the x coordinate of the piece
                        //7-i is the y coordinate of the piece
                        //the string value of the character array index is a letter indicating the type and colour of the piece
                        //The image is added in the constructor
                        boardPosition.add(charToPiece(String.valueOf(squares[j]), j, 7-i));
                    }
                }
            }
        }else {
            //throw an InvalidFEN exception
            //NOTE: Need to create an InvalidFEN exception so that it can be thrown
        }
        return boardPosition;
    }
    
    public boolean isValid() {
        /**
         * @params none
         * @returns boolean indicating whether or not an FEN is valid
         */
        
        //Currently it is assumed that the FEN is valid. This function will be updated
        //in the future to rigorously validate a given FEN. 
        return true;
    }
    public Piece charToPiece(String character, int x, int y) {
        String pieceType = "";
        String pieceColour = "";
        switch(character) {
            case "k":
                pieceType = "king";
                pieceColour = "black";
                break;
            case "q":
                pieceType = "queen";
                pieceColour = "black";
                break;
            case "r":
                pieceType = "rook";
                pieceColour = "black";
                break;
             
            case "b":
                pieceType = "bishop"; 
                pieceColour = "black";
                break;
                
            case "n":
                pieceType = "knight";
                pieceColour = "black";
                break;
                
            case "p":
                pieceType = "pawn";
                pieceColour = "black";
                break;
            case "K":
                pieceType = "king";
                pieceColour = "white";
                break;
            case "Q":
                pieceType = "queen";
                pieceColour = "white";
                break;
            case "R":
                pieceType = "rook";
                pieceColour = "white";
                break;
             
            case "B":
                pieceType = "bishop"; 
                pieceColour = "white";
                break;
                
            case "N":
                pieceType = "knight";
                pieceColour = "white";
                break;
                
            case "P":
                pieceType = "pawn";
                pieceColour = "white";
                break;
        }
        
        return new Piece(pieceType, pieceColour, x,y);
    }
}
