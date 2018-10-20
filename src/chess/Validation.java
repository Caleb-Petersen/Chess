/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;
/**
 *
 * @author Caleb
 */
public class Validation {
     public static boolean isDiagonal(Move move) {
        //This function checks to see if the move is diagonal, and if the path is clear
        int loopTracker = 0;

        //perform slope calculation to see if the moves are diagonal. 
        if ((Math.abs(move.y - move.piece.y)) == (Math.abs(move.x - move.piece.x))) {
            //return the max and min values x and x2
            int maxX = java.lang.Math.max(move.piece.x, move.x);
            int minX = java.lang.Math.min(move.piece.x, move.x);
            
            /*this is the default definition for yStart and yEnd. Later, if a different
            senario is true, the other option for the values of yStart and yEnd will 
            bee set to be true. These are the y values if minX==x1.
            */
            int yStart = move.piece.y;
            int yEnd = move.y;
            //The piece travels in a diagonal; This checks to see if the path is clear
            if ((move.x - move.piece.x) != 0) {
                int slope = (move.y - move.piece.y) / (move.x - move.piece.x);
                if (minX == move.x) {
                    yStart = move.y;
                    yEnd = move.piece.y;
                }

                //Loop through all of the squares on the diagonal and check 
                for (int i = minX + 1; i < maxX; i++) {
                    loopTracker = loopTracker + 1;
                    yStart = yStart + slope;
                    
                    for(int j=0; j<Main.pieces.size(); j++) {
                        Square square = new Square(i, yStart);
                        if(Main.pieces.get(j).pieceOnSquare(square)) {
                            return false;
                        }
                    }
                }
                //The piece colours must be different, or the square must be empty
                for(int i=0; i<Main.pieces.size(); i++) {
                    Square square = new Square(move.x,move.y);
               
                    if(Main.pieces.get(i).pieceOnSquare(square)) {
                        if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                            return false;
                        }
                    }
                }    
            }
        }
        return true;
    }

    public static boolean isStraight(Move move) {
        //This function checks to see if the move is straight, and if the path is clear
        
        //the rook travels across a y if y1 is eqal to y2
        if (move.y == move.piece.y) {
            //return the minX and maxX values
            int maxX = java.lang.Math.max(move.piece.y, move.x);
            int minX = java.lang.Math.min(move.piece.y, move.x);
            //if all the squares between minY and maxY are empty ifTracker will equal loopTracker
            for (int i = (minX + 1); i < maxX; i++) {
                for(int j=0; j<Main.pieces.size(); j++) {
                    Square square = new Square(i, move.piece.y);
                    
                    if(Main.pieces.get(j).pieceOnSquare(square)) {
                        return false;
                    }
                }
            }
        //the rook travels in a column if x1 is equal to x2
        } else if (move.x == move.piece.x) {
            int maxY = java.lang.Math.max(move.piece.y, move.y);
            int minY = java.lang.Math.min(move.piece.y, move.y);
            //if all the squares between minY and maxY are empty ifTracker will equal loopTracker
            for (int i = (minY + 1); i < maxY; i++) {
                for(int j=0; j<Main.pieces.size(); j++) {
                    Square square = new Square(i, move.piece.y);
                    
                    if(Main.pieces.get(j).pieceOnSquare(square)) {
                        return false;
                    }
                }
            }
        }
        for(int i=0; i<Main.pieces.size(); i++) {
            Square square = new Square(move.x, move.y);
            
            if(Main.pieces.get(i).pieceOnSquare(square)) {
                if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                    return false;
                }
            }
        }   
        return true;
    }

    public static boolean isKnight(Move move) {
        // this function will check to see if a knight can move to a given square
        boolean validLocation = false;

        /*Check to see if the knight moved up/down one y, and over two columns. The
        absolute values allow it to occur in any direction (as a knight should be
        able to move).
        */
        if ((Math.abs(move.y - move.piece.y) == 1) && (Math.abs(move.x - move.piece.x) == 2)) {
            validLocation = true;
        /*Check to see if the knight moved up/down two rows, and over one column. 
        The absolute values allow it to occur in any direction. 
        */
        } else if ((Math.abs(move.y - move.piece.y) == 2) && (Math.abs(move.x - move.piece.x) == 1)) {
            validLocation = true;
        }
        if(validLocation) {
            /*The location must be null or a piece of a different colour for it 
            to be a valid move
            */
            
            for(int i=0; i<Main.pieces.size(); i++) {
                Square square = new Square(move.x, move.y);
                
                if(Main.pieces.get(i).pieceOnSquare(square)) {
                    if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean isKing(Move move) {
        //This function checks to see if the king can move to any square
        boolean validLocation = false;
        String kingColour;
        
        /*The king can move one square in any direction if the square is empty or
        occupied by an opponent's piece
        */
        if (((Math.abs(move.y - move.piece.y) == 0 && (Math.abs(move.x - move.piece.x)) == 1)
                || (Math.abs(move.y - move.piece.y) == 1 && (Math.abs(move.x - move.piece.x)) == 0)
                || (Math.abs(move.y - move.piece.y) == 1 && (Math.abs(move.x - move.piece.x)) == 1))) {
            for(int i=0; i<Main.pieces.size(); i++) {
                Square square = new Square(move.x, move.y);
                
                if(Main.pieces.get(i).pieceOnSquare(square)) {
                    if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }
            }
            validLocation = true;
        //this allows for castling to occur
        } else if (Math.abs(move.x - move.piece.x) == 2 && move.y - move.piece.x == 0) {
            //the attempt was to castle queenside
            if ((move.x - move.piece.x) == -2) {
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
                    /*Check to see if the king is attacked, or if the square beside
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
            checked, only there is one less square involved when castling kingside
            */
            } else if ((move.x - move.piece.x) == 2) {
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
    
    public static boolean isPawn(Move move) {
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
        if ((move.x == move.piece.x) && ((move.y - move.piece.y) == direction)) {
            validLocation = true;
            //This is if the pawn has been attempted to have been moved two forward
            //The pawn can't have moved
        } else if ((move.x == move.piece.x) && ((move.y - move.piece.y) == (direction*2)) && move.piece.hasMoved == false) {
            validLocation = true;
            //This is for the capture of pawns
        } else if (move.y == move.piece.y + direction && (move.x == move.piece.x + 1 || move.x == move.piece.x - 1)) {
            for(int i=0; i<Main.pieces.size(); i++) {
                Square square = new Square(move.x, move.y);
                
                if(Main.pieces.get(i).pieceOnSquare(square)) {
                    if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }else {
                    /*This is for if en passant is played
                    Firstly, the destination square needs to be empty (already verified). Secondly, the 
                    piece beside the pawn needs to be a pawn, and of the opposite colour */
                    return false; //for now leave enpassant until later
                }
            }
        }

        if(validLocation) {
            for(int i=0; i<Main.pieces.size(); i++) {
                Square square = new Square(move.x, move.y);
                
                if(Main.pieces.get(i).pieceOnSquare(square)) {
                    if(Main.pieces.get(i).pieceColour.equals(move.piece.pieceColour)){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    
    }
}
