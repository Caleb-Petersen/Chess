
package Chess;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 */
public class Piece {
    public String pieceType; //always lowercase used
    public String pieceColour; 
    public Square location; //The square the piece is on
    public boolean hasMoved; //for pawns and castling
    public ArrayList<Square> possibleDestinations; //An array of the squares the piece can go to
    public BufferedImage pieceImage;
    
    public Piece(String initPieceType, String initPieceColour, int x, int y) {
        this.pieceType = initPieceType;
        this.pieceColour = initPieceColour;
        this.location = new Square(x,y);
        this.hasMoved = false;
        this.possibleDestinations = null;
        this.pieceImage = this.getImage();
    }
    public boolean moveLegal(int x, int y) {
        /**
         * @param a possible destination for a piece.
         * @returns a boolean indicating whether or not the piece can move to that destination
         * Note: The possible destinations array is assumed to be updated
         */
        for(int i=0; i<this.possibleDestinations.size(); i++) {
            if(this.possibleDestinations.get(i).x == x && this.possibleDestinations.get(i).y == y) {
                return true;
            }
        }
        return false;
    }
    
    public BufferedImage getImage() {
        /**
         * @param none
         * @returns BufferedImage
         */
        BufferedImage whiteKing = null, whiteQueen = null, whiteRook = null, whiteBishop = null, whiteKnight = null, whitePawn = null;
        BufferedImage blackKing = null, blackQueen = null, blackRook = null, blackBishop = null, blackKnight = null, blackPawn = null;
        try {
            whiteKing = ImageIO.read(getClass().getResource("Images/whiteKing.jpg"));
            whiteQueen = ImageIO.read(getClass().getResource("Images/whiteQueen.jpg"));
            whiteRook = ImageIO.read(getClass().getResource("Images/whiteRook.jpg"));
            whiteBishop = ImageIO.read(getClass().getResource("Images/whiteBishop.jpg"));
            whiteKnight = ImageIO.read(getClass().getResource("Images/whiteKnight.jpg"));
            whitePawn = ImageIO.read(getClass().getResource("Images/whitePawn.jpg"));
            blackKing = ImageIO.read(getClass().getResource("Images/blackKing.jpg"));
            blackQueen = ImageIO.read(getClass().getResource("Images/blackQueen.jpg"));
            blackRook = ImageIO.read(getClass().getResource("Images/blackRook.jpg"));
            blackBishop = ImageIO.read(getClass().getResource("Images/blackBishop.jpg"));
            blackKnight = ImageIO.read(getClass().getResource("Images/blackKnight.jpg"));
            blackPawn = ImageIO.read(getClass().getResource("Images/blackPawn.jpg"));
        } catch (IOException e) {
            Main.infoBox("Could not read in the image files: " + e.toString(), "IOException");
        }
        
        if(this.pieceColour.equals("white")) {
            switch (this.pieceType) {
                case "king":
                    return whiteKing;
                case "queen":
                    return whiteQueen;
                case "rook":
                    return whiteRook;
                case "bishop":
                    return whiteBishop;
                case "knight":
                    return whiteKnight;
                default:
                    return whitePawn;
            }
        }else {
            switch (this.pieceType) {
                case "king":
                    return blackKing;
                case "queen":
                    return blackQueen;
                case "rook":
                    return blackRook;
                case "bishop":
                    return blackBishop;
                case "knight":
                    return blackKnight;
                default:
                    return blackPawn;
            }
        }
    }
    
    public void updatePossibleDestinations() {
        /*
        *Function purpose: to update the array of locations that the piece can move to
        */
        if(this.possibleDestinations != null) {
            this.possibleDestinations.clear();
        }else {
            this.possibleDestinations = new ArrayList<Square>();
        }
            
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = new Square(x,y);
                Move move = new Move(this, square);

                switch(this.pieceType) {
                    case "king":
                        if(Validation.isKing(Main.pieces, move)) { //&& !proccessCheck(this, x, y            
                            possibleDestinations.add(square);
                        }
                        break;
                    case "queen":
                        if((Validation.isDiagonal(Main.pieces, move) || Validation.isStraight(Main.pieces, move)) ) { //&&!proccessCheck(this, x, y)
                            possibleDestinations.add(square);
                        }
                        break;
                    
                    case "rook":
                        if(Validation.isStraight(Main.pieces, move) ) { //&& !proccessCheck(this.x, x, this.y, y, null)
                            possibleDestinations.add(square);
                        }
                        break;
                    
                        
                    case "bishop":
                        if(Validation.isDiagonal(Main.pieces, move)) { //&&!proccessCheck(this.x, x, this.y, y, null)
                            possibleDestinations.add(square);
                        }
                        break;
                    
                    case "knight":
                        if(Validation.isKnight(Main.pieces, move) ) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                            possibleDestinations.add(square);  
                        }
                        break;
                    
                    default:
                        if(Validation.isPawn(Main.pieces, move)) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                            possibleDestinations.add(square);  
                        }
                        break;
                }
            }
        }
    }
    
    public boolean controlsSquare(ArrayList<Piece> boardPosition, Square square) {
        
        Move move = new Move(this, square);
        
        switch(this.pieceType) {
            case "king":
                return Validation.isKing(boardPosition, move);
                
            case "queen":
                return (Validation.isDiagonal(boardPosition, move) || Validation.isStraight(boardPosition, move)); 

            case "rook":
                return Validation.isStraight(boardPosition, move);

            case "bishop":
                return Validation.isDiagonal(boardPosition, move);

            case "knight":
                return Validation.isKnight(boardPosition, move);

            default:
                return Validation.isPawn(boardPosition, move);
    
        }
    }
    
    public boolean isInCheck(ArrayList<Piece> boardPosition, Square kingLocation) {
        if(this.pieceType.equals("king")) {
            Piece piece = null;
            for(int i=0; i<boardPosition.size(); i++) {
                piece = boardPosition.get(i);
                
                //the piece must be of opposite colour
                if(piece.pieceColour.equals(this.pieceColour) == false) {
                    if(piece.controlsSquare(boardPosition, kingLocation)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean pieceOnSquare(Square square) {
        if(this.location.y == square.y && this.location.x == square.x) {
            return true;
        }
        return false;
    }
}
