
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
    public TYPE pieceType; 
    public COLOUR pieceColour; 
    public Square location; //The square the piece is on
    public boolean hasMoved; //for pawns and castling
    public ArrayList<Square> possibleDestinations; //An array of the squares the piece can go to
    
    public enum TYPE {
        KING,
        QUEEN,
        ROOK,
        KNIGHT,
        BISHOP,
        PAWN
    }
    public enum COLOUR {
        BLACK,
        WHITE
    }
   
    public Piece(TYPE type, COLOUR colour, int x, int y) {
        
        this.pieceType = type;
        this.pieceColour = colour;
        this.location = new Square(x,y);
        this.hasMoved = false;
        this.possibleDestinations = null;
    }
    
    public Piece(Piece p) {
        this.pieceType = p.pieceType;
        this.pieceColour = p.pieceColour;
        this.location = new Square(p.location.x, p.location.y);
        this.hasMoved = false;
        this.possibleDestinations = null;
    }
    
    public void updatePossibleDestinations(Position position) {
        /*
        *Function purpose: to update the array of locations that the piece can move to
        */
        if(this.possibleDestinations != null) {
            this.possibleDestinations.clear();
        }else {
            this.possibleDestinations = new ArrayList<>();
        }
            
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = new Square(x,y);
                Move move = new Move(this, square);
                
                //don't let the square be the one the piece is currently on
                if(!(this.location.x == x && this.location.y == y)) {
                    switch(this.pieceType) {
                        case KING:
                            if(Validation.isKing(position, move)) { //&& !proccessCheck(this, x, y            
                                possibleDestinations.add(square);
                            }
                            break;
                        case QUEEN:
                            if((Validation.isDiagonal(position, move) || Validation.isStraight(position, move)) ) { //&&!proccessCheck(this, x, y)
                                possibleDestinations.add(square);
                            }
                            break;

                        case ROOK:
                            if(Validation.isStraight(position, move) ) { //&& !proccessCheck(this.x, x, this.y, y, null)
                                possibleDestinations.add(square);
                            }
                            break;


                        case BISHOP:
                            if(Validation.isDiagonal(position, move)) { //&&!proccessCheck(this.x, x, this.y, y, null)
                                possibleDestinations.add(square);
                            }
                            break;

                        case KNIGHT:
                            if(Validation.isKnight(position, move) ) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                                possibleDestinations.add(square);  
                            }
                            break;

                        default:
                            if(Validation.isPawn(position, move)) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                                possibleDestinations.add(square);  
                            }
                            break;
                    }
                }
            }
        }
    }
    
    public boolean controlsSquare(Position position, Square square) {
        
        Move move = new Move(this, square);
        
        switch(this.pieceType) {
            case KING:
                return Validation.isKing(position, move);
                
            case QUEEN:
                return (Validation.isDiagonal(position, move) || Validation.isStraight(position, move)); 

            case ROOK:
                return Validation.isStraight(position, move);

            case BISHOP:
                return Validation.isDiagonal(position, move);

            case KNIGHT:
                return Validation.isKnight(position, move);

            default:
                return Validation.isPawn(position, move);
    
        }
    }
    
    public boolean isAttacked(Position boardPosition) {
        for(Piece piece : boardPosition.boardPosition) {
            if(piece.pieceColour != this.pieceColour) {
                if(piece.controlsSquare(boardPosition, this.location)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean pieceOnSquare(Square square) {
        return this.location.y == square.y && this.location.x == square.x;
    }
    
    public int getValue() {
        int value = 0;
        switch(this.pieceType) {
            case KING:
                value += Constants.KING_VALUE;
                break;
            case QUEEN:
                value += Constants.QUEEN_VALUE;
                break;
            case ROOK:
                value += Constants.ROOK_VALUE;
                break;
            case BISHOP:
                value += Constants.BISHOP_VALUE;
                break;
            case KNIGHT:
                value += Constants.KNIGHT_VALUE;
                break;
            case PAWN:
                value += Constants.PAWN_VALUE;
                break;
            default:
                break;
        }
        
        if(this.pieceColour == COLOUR.BLACK) {
            value *= -1;
        }
        
        return value;
    }
    
    public String identifier() {
        
        char identifier;
        
        switch(this.pieceType) {
            
            case KING:
                identifier = 'k';
                break;
                
            case QUEEN:
                identifier = 'q';
                break;
                
            case ROOK:
                identifier = 'r';
                break;
            
            case BISHOP:
                identifier = 'b';
                break;
            
            case KNIGHT:
                identifier = 'n';
                break;
                
            default:
                identifier = 'p';
                break;
        }
        
        if(this.pieceColour == COLOUR.WHITE) {
            //black pieces are represented by upper case characters
            identifier = Character.toUpperCase(identifier);
        }
        return Character.toString(identifier);
    }
    
    public BufferedImage getImage() {
        /**
         * @param none
         * @returns BufferedImage
         */
        try {
            if(this.pieceColour == COLOUR.WHITE) {
                switch (this.pieceType) {
                    case KING:
                        return ImageIO.read(getClass().getResource("Images/whiteKing.jpg"));
                    case QUEEN:
                        return ImageIO.read(getClass().getResource("Images/whiteQueen.jpg"));
                    case ROOK:
                        return ImageIO.read(getClass().getResource("Images/whiteRook.jpg"));
                    case BISHOP:
                        return ImageIO.read(getClass().getResource("Images/whiteBishop.jpg"));
                    case KNIGHT:
                        return ImageIO.read(getClass().getResource("Images/whiteKnight.jpg"));
                    default:
                        return ImageIO.read(getClass().getResource("Images/whitePawn.jpg"));
                }
            }else {
                switch (this.pieceType) {
                    case KING:
                        return ImageIO.read(getClass().getResource("Images/blackKing.jpg"));
                    case QUEEN:
                        return ImageIO.read(getClass().getResource("Images/blackQueen.jpg"));
                    case ROOK:
                        return ImageIO.read(getClass().getResource("Images/blackRook.jpg"));
                    case BISHOP:
                        return ImageIO.read(getClass().getResource("Images/blackBishop.jpg"));
                    case KNIGHT:
                        return ImageIO.read(getClass().getResource("Images/blackKnight.jpg"));
                    default:
                        return ImageIO.read(getClass().getResource("Images/blackPawn.jpg"));
                }
            }
        } catch (IOException e) {
            Main.infoBox("Could not read in the image files: " + e.toString(), "IOException");
        }
        
        return null;
    }
}
