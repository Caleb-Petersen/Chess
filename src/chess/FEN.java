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
public class FEN {
    public String position;
    public char turnColour; //either 'w' or 'b'
    public String castlingAvailability; //KQkq for kingside and queenside castling availability, "-" for nothing is available
    public String enpassantAvailability; //either "-" or the square behind the pawn (e.g. e3)
    
    public FEN (String initFEN) {
        this.position = initFEN;
        this.castlingAvailability = "";
        this.enpassantAvailability = "";
    }

    public ArrayList<Piece> fenToBoardPosition() {
        ArrayList<Piece> boardPosition = new ArrayList<Piece>();
        //rnbqk2r/1pp1b1p1/3p4/p3p1np/4Pp2/2PPBN1P/PPQNBPP1/R3K2R
        if(this.isValid()) {
            //boardRows is of size 8 if the position is valid
            String[] boardRows = this.position.split("/");
            for(int i=0; i<8; i++) {
                char [] squares = boardRows[i].toCharArray();
                //NOTE:: this is somewhat messy, and hard to understand -> to be refactored in the future
                int xCoordinate = 0; //This coordinate is necessary as j may not reach 7 due to the nature of position strings (e.g. 3P4 j will only reach 2)
                for(int j=0; (j<boardRows[i].length() && (Character.getType(squares[j])) != 12); j++) {
                    if(Character.isDigit(squares[j]) == false) {
                        //j is the x coordinate of the piece
                        //7-i is the y coordinate of the piece
                        //the string value of the character array index is a letter indicating the type and colour of the piece
                        //The image is added in the constructor
                        
                        boardPosition.add(charToPiece(String.valueOf(squares[j]), xCoordinate, 7-i));
                        xCoordinate++;
                    }else {
                        //Increment the index by the digit minus one
                        xCoordinate += Integer.parseInt(Character.toString(squares[j]));
                    }
                }
            }
        }else {
            //throw an InvalidFEN exception
            //NOTE: Need to create an InvalidFEN exception so that it can be thrown
        }
        /*
        for(int i=0; i<boardPosition.size(); i++) {
            Piece piece = boardPosition.get(i);
            System.out.println(piece.pieceColour + " " + piece.pieceType);
            System.out.println("at X: " + piece.location.x);
            System.out.println("at Y: " + piece.location.y);
        }*/
        return boardPosition;
    }

    public void createFEN(ArrayList<Piece> position) {
        /**
         * @param position is the position that is being put into an FEN string
         * @updates the FEN member variable using the position array
         */
        this.position = ""; //ensure that the string is empty before updating it
        String [][] fenArray = new String [8][8];
        
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                fenArray[i][j] = "1";
            }
        }
        //Add the elements of the piece array into their respective locations (using string identifiers)
        for(int i=0; i<position.size(); i++) {
            fenArray[position.get(i).location.x][position.get(i).location.y] = position.get(i).identifier();
        }
        for(int i=7; i>=0; i--) {
            for(int j=0; j<8; j++) {
                System.out.print(fenArray[j][i] + " ");
            }
            System.out.println();
        }
        //Add the char array to the position string
        for(int i=7; i>=0; i--) {
            boolean counting = false;
            int sum = 0;
            
            for(int j=0; j<8; j++) {
                if(fenArray[j][i].equals("1")) {
                    sum += 1;
                    counting = true;
                }else {
                    if(counting) {
                        this.position += String.valueOf(sum);
                    }
                    this.position += fenArray[j][i];
                    sum = 0;
                    counting = false;
                }
            }
            
            if(counting) {
                this.position += String.valueOf(sum);
            }
            if(i > 0) {
                this.position += "/";
            }
        }
    }
    
    public boolean isValid() {
        /**
         * @params none
         * @returns boolean indicating whether or not an FEN is valid
         */
        
        //Currently it is assumed that the position is valid. This function will be updated
        //in the future to rigorously validate a given position. 
        return true;
    }
    public Piece charToPiece(String character, int x, int y) {
        Piece.TYPE pieceType = Piece.TYPE.KING;
        Piece.COLOUR pieceColour = Piece.COLOUR.WHITE;
        
        switch(character) {
            case "k":
                pieceType = Piece.TYPE.KING;
                pieceColour = Piece.COLOUR.BLACK;
                break;
            case "q":
                pieceType = Piece.TYPE.QUEEN;
                pieceColour = Piece.COLOUR.BLACK;
                break;
            case "r":
                pieceType = Piece.TYPE.ROOK;
                pieceColour = Piece.COLOUR.BLACK;
                break;
             
            case "b":
                pieceType = Piece.TYPE.BISHOP;
                pieceColour = Piece.COLOUR.BLACK;
                break;
                
            case "n":
                pieceType = Piece.TYPE.KNIGHT;
                pieceColour = Piece.COLOUR.BLACK;
                break;
                
            case "p":
                pieceType = Piece.TYPE.PAWN;
                pieceColour = Piece.COLOUR.BLACK;
                break;
            case "K":
                pieceType = Piece.TYPE.KING; //The colour has already been set to be white
                break;
            case "Q":
                pieceType = Piece.TYPE.QUEEN;//The colour has already been set to be white
                break;
            case "R":
                pieceType = Piece.TYPE.ROOK; //The colour has already been set to be white
                break;
             
            case "B":
                pieceType = Piece.TYPE.BISHOP; //The colour has already been set to be white
                break;
                
            case "N":
                pieceType = Piece.TYPE.KNIGHT; //The colour has already been set to be white
                break;
                
            case "P":
                pieceType = Piece.TYPE.PAWN; //The colour has already been set to be white
                break;
        }
        
        return new Piece(pieceType, pieceColour, x,y);
    }
}
