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
                        boardPosition.add(charToPiece(String.valueOf(squares[j])));
                        int size = boardPosition.size() -1;
                        boardPosition.get(size).y = 7-i;
                        boardPosition.get(size).x = j;
                        boardPosition.get(size).hasMoved = false;
                        boardPosition.get(size).pieceImage = boardPosition.get(size).getImage();
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
    public Piece charToPiece(String character) {
        Piece piece = new Piece();
        switch(character) {
            case "k":
                piece.pieceType = "king";
                piece.pieceColour = "black";
                break;
            case "q":
                piece.pieceType = "queen";
                piece.pieceColour = "black";
                break;
            case "r":
                piece.pieceType = "rook";
               piece.pieceColour = "black";
                break;
             
            case "b":
                piece.pieceType = "bishop"; 
                piece.pieceColour = "black";
                break;
                
            case "n":
                piece.pieceType = "knight";
                piece.pieceColour = "black";
                break;
                
            case "p":
                piece.pieceType = "pawn";
                piece.pieceColour = "black";
                break;
            case "K":
                piece.pieceType = "king";
                piece.pieceColour = "white";
                break;
            case "Q":
                piece.pieceType = "queen";
                piece.pieceColour = "white";
                break;
            case "R":
                piece.pieceType = "rook";
               piece.pieceColour = "white";
                break;
             
            case "B":
                piece.pieceType = "bishop"; 
                piece.pieceColour = "white";
                break;
                
            case "N":
                piece.pieceType = "knight";
                piece.pieceColour = "white";
                break;
                
            case "P":
                piece.pieceType = "pawn";
                piece.pieceColour = "white";
                break;
        }
        return piece;
    }
}
