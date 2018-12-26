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
public class Validation {
     public static boolean isDiagonal(ArrayList<Piece> boardPosition, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Does the piece travel diagonally?
         *      2. Are there any pieces in the way of the path of that piece?
         *      3. Is the destination square occupied by a piece of the same colour?
         */
        

        //If a move is diagonal, it will follow a linear path with a slope of 1
        if ((Math.abs(move.destination.y - move.piece.y)) == (Math.abs(move.destination.x - move.piece.x))) {
           
            //The piece cannot move to the square that it itself occupies
            if ((move.destination.x - move.piece.x) != 0) {
                
                //It is known that the piece travels diagonally, however there also need to be no other pieces in its way
                //This loops through all of the squares in between the piece, and it's destination square

                int maxX = java.lang.Math.max(move.piece.x, move.destination.x);
                int minX = java.lang.Math.min(move.piece.x, move.destination.x);
                int yLocation = move.piece.y;
                
                //The slope variable indicates whether or not the y value increases or decreases as the x value increases
                //It is either -1, or +1, and is used to adjust the y value as the squares are checked
                int slope = (move.destination.y - move.piece.y) / (move.destination.x - move.piece.x);

                //Loop through all of the squares on the diagonal and check if a piece is on those squares
                for (int i = minX + 1; i < maxX; i++) {
                   
                    yLocation = yLocation + slope;
                    
                    for(int j=0; j<boardPosition.size(); j++) {
                        Square square = new Square(i, yLocation);
                        if(boardPosition.get(j).pieceOnSquare(square)) {
                            return false;
                        }
                    }
                }
                //The piece colours must be different, or the destination must be empty
                return isValidDestination(boardPosition, move);
            }
        }
        return false;
    }

    public static boolean isStraight(ArrayList<Piece> boardPosition, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Does the piece travel horizontally or vertically?
         *      2. Are there any pieces in the way of the path of that piece?
         *      3. Is the destination square occupied by a piece of the same colour?
         */     
        
        if (move.destination.y == move.piece.y) {
            //a horizontal move was atempted
            int maxX = java.lang.Math.max(move.piece.y, move.destination.x);
            int minX = java.lang.Math.min(move.piece.y, move.destination.x);
            //Check to ensure that all of the squares between minX and maxX are empty
            for (int i = (minX + 1); i < maxX; i++) {
                for(int j=0; j<boardPosition.size(); j++) {
                    Square square = new Square(i, move.piece.y);
                    
                    if(boardPosition.get(j).pieceOnSquare(square)) {
                        return false;
                    }
                }
            }
        } else if (move.destination.x == move.piece.x) {
            //a vertical move was attempted
            int maxY = java.lang.Math.max(move.piece.y, move.destination.y);
            int minY = java.lang.Math.min(move.piece.y, move.destination.y);
            //Check to ensure that all of the squares between minY and maxY are empty
            for (int i = (minY + 1); i < maxY; i++) {
                for(int j=0; j<boardPosition.size(); j++) {
                    Square square = new Square(move.piece.y, i );
                    
                    if(boardPosition.get(j).pieceOnSquare(square)) {
                        return false;
                    }
                }
            }
        }else {
            //neither a vertical or horizontal move was attempted, so the move cannot be straight
            return false;
        }
        //Checking to see that the piece on the destination destination doesn't have a piece of the same colour on it
        return isValidDestination(boardPosition, move);
    }

    public static boolean isKnight(ArrayList<Piece> boardPosition, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         */  
        boolean validLocation = false;

        //Check to see if the knight moved up/down one y, and over two columns. The
        //absolute values allow it to occur in any direction 
        if ((Math.abs(move.destination.y - move.piece.y) == 1) && (Math.abs(move.destination.x - move.piece.x) == 2)) {
            validLocation = true;
            
        //Check to see if the knight moved up/down two rows, and over one column. 
        //The absolute values allow it to occur in any direction. 
        } else if ((Math.abs(move.destination.y - move.piece.y) == 2) && (Math.abs(move.destination.x - move.piece.x) == 1)) {
            validLocation = true;
        }
        if(validLocation) {
            //Check to make sure that the destination destination doesn't have a piece of the same colour on it
            return isValidDestination(boardPosition, move);
        }
        return false;
    }
    
    public static boolean isKing(ArrayList<Piece> boardPosition, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Does the piece travel to a location one away, and is that location free?
         *      2. Was castling attempted? If so, castling is split up into kingside and queenside cases
         *      3. Is the destination square occupied by a piece of the same colour?
         */   
        
        if (((Math.abs(move.destination.y - move.piece.y) == 0 && (Math.abs(move.destination.x - move.piece.x)) == 1)
                || (Math.abs(move.destination.y - move.piece.y) == 1 && (Math.abs(move.destination.x - move.piece.x)) == 0)
                || (Math.abs(move.destination.y - move.piece.y) == 1 && (Math.abs(move.destination.x - move.piece.x)) == 1))) {
            
            return isValidDestination(boardPosition, move);
        } else if (Math.abs(move.destination.x - move.piece.x) == 2 && move.destination.y - move.piece.y == 0) {
            
            if ((move.destination.x - move.piece.x) == -2) {
                //NOTE:: Queenside castling was attempted
                
                //Find the rook that matches the king
                Piece rook = null;
                for(int i=0; i<boardPosition.size(); i++) {
                    //For queenside castling, the x position of the rook will be zero, and the y will match that of the king
                    if(boardPosition.get(i).x == 0 && boardPosition.get(i).y == move.piece.y) {
                        rook = boardPosition.get(i);
                    }
                } 
                
                if(move.piece.hasMoved == false && rook.hasMoved == false) {
                    //check the path between the rook and the king for checks and pieces
                    //get all of the squares between the king and the rook and check that
                    //      1. The square isn't controlled by an opponent's piece
                    //      2. The square isn't occupied
                    
                    if(move.piece.isInCheck(boardPosition) == false) {
                        //NOTE:: could convert this to an array to avoid having the three variables
                        Square oneLeft = new Square(move.piece.x - 1, move.piece.y);
                        Square twoLeft = new Square(move.piece.x - 2, move.piece.y);
                        Square threeLeft = new Square(move.piece.x - 3, move.piece.y);

                        if(oneLeft.squareControlled(boardPosition, move.piece.pieceColour) == false &&
                                oneLeft.pieceOnSquare(boardPosition) == false &&
                                twoLeft.squareControlled(boardPosition, move.piece.pieceColour) == false &&
                                twoLeft.pieceOnSquare(boardPosition) == false &&
                                threeLeft.squareControlled(boardPosition, move.piece.pieceColour) == false &&
                                threeLeft.pieceOnSquare(boardPosition) == false) {

                            return true;
                        }
                    }
                }
            } else if ((move.destination.x - move.piece.x) == 2) {
                //NOTE:: castling kingside was attempted
                
                //Find the rook that matches the king
                Piece rook = null;
                for(int i=0; i<boardPosition.size(); i++) {
                    //For queenside castling, the x position of the rook will be 7, and the y will match that of the king
                    if(boardPosition.get(i).x == 7 && boardPosition.get(i).y == move.piece.y) {
                        rook = boardPosition.get(i);
                    }
                } 
                
                if(move.piece.hasMoved == false && rook.hasMoved == false) {
                    //check the path between the rook and the king for checks and pieces
                    //get all of the squares between the king and the rook and check that
                    //      1. The square isn't controlled by an opponent's piece
                    //      2. The square isn't occupied
                    
                    if(move.piece.isInCheck(boardPosition) == false) {
                        Square oneLeft = new Square(move.piece.x + 1, move.piece.y);
                        Square twoLeft = new Square(move.piece.x + 2, move.piece.y);

                        if(oneLeft.squareControlled(boardPosition, move.piece.pieceColour) == false &&
                                oneLeft.pieceOnSquare(boardPosition) == false &&
                                twoLeft.squareControlled(boardPosition, move.piece.pieceColour) == false &&
                                twoLeft.pieceOnSquare(boardPosition) == false) {

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isPawn(ArrayList<Piece> boardPosition, Move move) {
        boolean validLocation = false;
        int direction;
        //the black pawn
        if (move.piece.pieceColour.equals("black")) {
            direction = -1;
        //the white pawn
        }else {
            direction = 1;
        }
        
        //This is if the pawn is attempted to have be moved forward once
        if ((move.destination.x == move.piece.x) && ((move.destination.y - move.piece.y) == direction)) {
            validLocation = true;
            //This is if the pawn has been attempted to have been moved two forward
            //The pawn can't have moved
        } else if ((move.destination.x == move.piece.x) && ((move.destination.y - move.piece.y) == (direction*2)) && move.piece.hasMoved == false) {
            validLocation = true;
            //This is for the capture of pawns
        } else if (move.destination.y == (move.piece.y + direction) && (move.destination.x == (move.piece.x + 1) || move.destination.x == (move.piece.x - 1))) {
            
            for(int i=0; i<boardPosition.size(); i++) {
                if(boardPosition.get(i).x == move.destination.x && boardPosition.get(i).y == move.destination.y) {
                    Square square = new Square(move.destination.x, move.destination.y);
                
                    if(boardPosition.get(i).pieceOnSquare(square)) {
                        if(boardPosition.get(i).pieceColour.equals(move.piece.pieceColour)){
                            return false;
                        }
                        validLocation = true;
                    }else {
                        /*This is for if en passant is played
                        Firstly, the destination destination needs to be empty (already verified). Secondly, the 
                        piece beside the pawn needs to be a pawn, and of the opposite colour */
                        return false; //for now leave enpassant until later
                    }
                }
                
            }
        }

        if(validLocation) {
            //check to see if there is a piece on the destination destination
            //if there is, verify that it is not of the same colour as the piece
            //that is being moved
            for(int i=0; i<boardPosition.size(); i++) {
                Square square = new Square(move.destination.x, move.destination.y);
                
                if(boardPosition.get(i).pieceOnSquare(square)) {
                    if(boardPosition.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    
    }
    
    public static boolean isValidDestination(ArrayList<Piece> boardPosition, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the destination square contains a piece of the same colour as the one being moved
         */  
      
        for(int i=0; i<boardPosition.size(); i++) {
            Square square = new Square(move.destination.x, move.destination.y);
            
            if(boardPosition.get(i).pieceOnSquare(square)) {
                if(boardPosition.get(i).pieceColour.equals(move.piece.pieceColour)){
                    return false;
                }
            }
        } 
        return true;
    }
}
