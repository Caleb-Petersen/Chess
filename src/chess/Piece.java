
package Chess;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
    public int y; //the y-coordinate of the piece: 1-8 in chess notation
    public int x; //the x-coordinate of the piece: a-h in chess notation
    public boolean hasMoved; //used only for pawns
    public ArrayList<Square> possibleDestinations; //An array of the squares the piece can go to
    public ImageIcon pieceImage;
    public JLabel pieceLabel;
    
    public boolean moveLegal(int x, int y) {
        /**
         * @param a possible destination for a piece.
         * @returns a boolean indicating whether or not the piece can move to that destination
         * Note: The possible destinations array is assumed to be updated
         */
        for(int i=0; i<this.possibleDestinations.size(); i++) {
            if(this.possibleDestinations.get(i).col == x && this.possibleDestinations.get(i).row == y) {
                return true;
            }
        }
        return false;
    }
    
    public ImageIcon getImage() {
        /**
         * @param none
         * @returns ImageIcon
         * Note: This is using a testing graphical method. It will not be used in the
         * final product if that is at all possible.
         */
        ImageIcon whiteKing = new ImageIcon(getClass().getResource("Images/whiteKing.jpg"));
        ImageIcon whiteQueen = new ImageIcon(getClass().getResource("Images/whiteQueen.jpg"));
        ImageIcon whiteRook = new ImageIcon(getClass().getResource("Images/whiteRook.jpg"));
        ImageIcon whiteBishop = new ImageIcon(getClass().getResource("Images/whiteBishop.jpg"));
        ImageIcon whiteKnight = new ImageIcon(getClass().getResource("Images/whiteKnight.jpg"));
        ImageIcon whitePawn = new ImageIcon(getClass().getResource("Images/whitePawn.jpg"));
        ImageIcon blackKing = new ImageIcon(getClass().getResource("Images/blackKing.jpg"));
        ImageIcon blackQueen = new ImageIcon(getClass().getResource("Images/blackQueen.jpg"));
        ImageIcon blackRook = new ImageIcon(getClass().getResource("Images/blackRook.jpg"));
        ImageIcon blackBishop = new ImageIcon(getClass().getResource("Images/blackBishop.jpg"));
        ImageIcon blackKnight = new ImageIcon(getClass().getResource("Images/blackKnight.jpg"));
        ImageIcon blackPawn = new ImageIcon(getClass().getResource("Images/blackPawn.jpg"));
        
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
                Move move = new Move(this, x, y);

                switch(this.pieceType) {
                    case "king":
                        if(Validation.isKing(move)) { //&& !proccessCheck(this, x, y
                            Square square = new Square(x,y);              
                            possibleDestinations.add(square);
                        }
                        break;
                    case "queen":
                        if((Validation.isDiagonal(move) || Validation.isStraight(move)) ) { //&&!proccessCheck(this, x, y)
                            Square square = new Square(x,y); 
                            possibleDestinations.add(square);
                        }
                        break;
                    
                    case "rook":
                        if(Validation.isStraight(move) ) { //&& !proccessCheck(this.x, x, this.y, y, null)
                            Square square = new Square(x,y); 
                            possibleDestinations.add(square);
                        }
                        break;
                    
                        
                    case "bishop":
                        if(Validation.isDiagonal(move)) { //&&!proccessCheck(this.x, x, this.y, y, null)
                            Square square = new Square(x,y); 
                            possibleDestinations.add(square);
                        }
                        break;
                    
                    case "knight":
                        if(Validation.isKnight(move) ) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                            Square square = new Square(x,y); 
                            possibleDestinations.add(square);  
                        }
                        break;
                    
                    default:
                        if(Validation.isPawn(move)) { //&& !proccessCheck(this.x,x,this.y,y,new square[8][8])
                            Square square = new Square(x,y); 
                            possibleDestinations.add(square);  
                        }
                        break;
                }
            }
        }
    }
    
    public boolean pieceOnSquare(Square square) {
        if(this.y == square.row && this.x == square.col) {
            return true;
        }
        return false;
    }
    
    public JLabel prepareToDraw() {
        this.pieceLabel.setSize(100,100);
        this.pieceLabel.setLocation(ChessUI.xCoordinateToPixels(this.x), ChessUI.yCoordinateToPixels(y, 7));
        this.pieceLabel.setIcon(this.pieceImage);
        
        return this.pieceLabel;
    }
    public JLabel drawBlank() {
        this.pieceLabel.setIcon(null);
        
        return this.pieceLabel;
    }
}