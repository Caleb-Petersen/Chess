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
                for(int i=0; i<boardPosition.size(); i++) {
                    Square square = new Square(move.destination.x,move.destination.y);
               
                    if(boardPosition.get(i).pieceOnSquare(square)) {
                        if(boardPosition.get(i).pieceColour.equals(move.piece.pieceColour)){
                            return false;
                        }
                    }
                } 
                return true;
            }
        }
        return false;
    }

    public static boolean isStraight(ArrayList<Piece> boardPosition, Move move) {
        //This function checks to see if the move is straight, and if the path is clear
        
        
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

    public static boolean isKnight(ArrayList<Piece> boardPosition, Move move) {
        // this function will check to see if a knight can move to a given destination
        boolean validLocation = false;

        /*Check to see if the knight moved up/down one y, and over two columns. The
        absolute values allow it to occur in any direction (as a knight should be
        able to move).
        */
        if ((Math.abs(move.destination.y - move.piece.y) == 1) && (Math.abs(move.destination.x - move.piece.x) == 2)) {
            validLocation = true;
        /*Check to see if the knight moved up/down two rows, and over one column. 
        The absolute values allow it to occur in any direction. 
        */
        } else if ((Math.abs(move.destination.y - move.piece.y) == 2) && (Math.abs(move.destination.x - move.piece.x) == 1)) {
            validLocation = true;
        }
        if(validLocation) {
            //Check to make sure that the destination destination doesn't have a piece of the same colour on it
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
    
    public static boolean isKing(ArrayList<Piece> boardPosition, Move move) {
        //This function checks to see if the king can move to any destination
        boolean validLocation = false;
        String kingColour;
        
        /*The king can move one destination in any direction if the destination is empty or
        occupied by an opponent's piece
        */
        if (((Math.abs(move.destination.y - move.piece.y) == 0 && (Math.abs(move.destination.x - move.piece.x)) == 1)
                || (Math.abs(move.destination.y - move.piece.y) == 1 && (Math.abs(move.destination.x - move.piece.x)) == 0)
                || (Math.abs(move.destination.y - move.piece.y) == 1 && (Math.abs(move.destination.x - move.piece.x)) == 1))) {
            for(int i=0; i<boardPosition.size(); i++) {
                Square square = new Square(move.destination.x, move.destination.y);
                
                if(boardPosition.get(i).pieceOnSquare(square)) {
                    if(boardPosition.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }
            }
            validLocation = true;
        //this allows for castling to occur
        } else if (Math.abs(move.destination.x - move.piece.x) == 2 && move.destination.y - move.piece.x == 0) {
            //the attempt was to castle queenside
            if ((move.destination.x - move.piece.x) == -2) {
                //In order for castling to be legal neither the king or rook can have moved
                /*
                if (A[y1][x1 - 4].pieceType.equals("rook")
                        && A[y1][x1 - 4].hasMoved == false
                        && A[y1][x1].hasMoved == false
                        //check the squares between the king and rook. They must be empty
                        && A[y1][x1 - 1].pieceType.equals("null")
                        && A[y1][x1 - 2].pieceType.equals("null")
                        && A[y1][x1 - 3].pieceType.equals("null")) {
                    kingColour = A[y1][x1].pieceColour;
                    /*Check to see if the king is attacked, or if the destination beside
                    the king is controlled by an opponent's piece. If either is true, 
                    the king cannot castle.
                    */
                /*
                    if (!isCheck(x1, y1, kingColour, A) &&
                        !isCheck(x1-1, y1, kingColour, A)) {
                        //move the rook, the king will be moved in the usual way
                        resetArray(A, x1 - 4, x1 - 1, y1, y2);
                        A[y1][x1 - 1].hasMoved = true;
                        isKing = true;
                    }
                }
            /* the attempt was to castle kingside. All the same elements need to be 
            checked, only there is one less destination involved when castling kingside
            */
            } else if ((move.destination.x - move.piece.x) == 2) {
                /*
                if (A[y1][x1 + 3].pieceType.equals("rook")
                        && A[y1][x1 + 3].hasMoved == false
                        && A[y1][x1].hasMoved == false
                        && A[y1][x1 + 1].pieceType.equals("null")
                        && A[y1][x1 + 2].pieceType.equals("null")) {
                    kingColour = A[y1][x1].pieceColour;
                    
                    if (!isCheck(x1, y1, kingColour, A) &&
                        !isCheck(x1+1, y1, kingColour, A)) {

                        resetArray(A, x1 + 3, x1 + 1, y1, y1);
                        A[y1][x2 + 1].hasMoved = true;
                        isKing = true;
                    }
                }*/
            }
        }
        return validLocation; //castling currently commented out
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
}
