/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.Piece.COLOUR;

/**
 *
 * @author Caleb
 */
public class Validation {
     public static boolean isDiagonal(Position position, Move move) {
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
        if ((Math.abs(move.destination.y - move.piece.location.y)) == (Math.abs(move.destination.x - move.piece.location.x))) {
           
            //The piece cannot move to the square that it itself occupies
            if ((move.destination.x - move.piece.location.x) != 0) {
                
                //It is known that the piece travels diagonally, however there also need to be no other pieces in its way
                //This loops through all of the squares in between the piece, and it's destination square

                int maxX = java.lang.Math.max(move.piece.location.x, move.destination.x);
                int minX = java.lang.Math.min(move.piece.location.x, move.destination.x);
               
                int yLocation = move.piece.location.y;
                
                if(maxX == move.piece.location.x) {
                    yLocation = move.destination.y;
                }
                
                //The slope variable indicates whether or not the y value increases or decreases as the x value increases
                //It is either -1, or +1, and is used to adjust the y value as the squares are checked
                int slope = (move.destination.y - move.piece.location.y) / (move.destination.x - move.piece.location.x);

                //Loop through all of the squares on the diagonal and check if a piece is on those squares
                for (int i = minX + 1; i < maxX; i++) {
                    yLocation = yLocation + slope;
                            
                    for(Piece piece : position.boardPosition) {
                        Square square = new Square(i, yLocation);
                        if(piece.isOnSquare(square)) {
                            return false;
                        }
                    }
                }
                //The piece colours must be different, or the destination must be empty
                return isValidDestination(position, move);
            }
        }
        return false;
    }

    public static boolean isStraight(Position position, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Does the piece travel horizontally or vertically?
         *      2. Are there any pieces in the way of the path of that piece?
         *      3. Is the destination square occupied by a piece of the same colour?
         */     
        
        if (move.destination.y == move.piece.location.y) {
            //a horizontal move was atempted
            int maxX = java.lang.Math.max(move.piece.location.x, move.destination.x);
            int minX = java.lang.Math.min(move.piece.location.x, move.destination.x);
            //Check to ensure that all of the squares between minX and maxX are empty
            for (int i = (minX + 1); i < maxX; i++) {
                Square square = new Square(i, move.piece.location.y);
                
                for(Piece piece : position.boardPosition) {
                    if(piece.isOnSquare(square)) {
                        return false;
                    }
                }
            }
        } else if (move.destination.x == move.piece.location.x) {
            //a vertical move was attempted
            int maxY = java.lang.Math.max(move.piece.location.y, move.destination.y);
            int minY = java.lang.Math.min(move.piece.location.y, move.destination.y);
            //Check to ensure that all of the squares between minY and maxY are empty
            for (int i = (minY + 1); i < maxY; i++) {
                Square square = new Square(move.piece.location.x, i );
                
                for(Piece piece : position.boardPosition) {
                    if(piece.isOnSquare(square)) {
                        return false;
                    }
                }
            }
        }else {
            //neither a vertical or horizontal move was attempted, so the move cannot be straight
            return false;
        }
        //Checking to see that the piece on the destination square doesn't have a piece of the same colour on it
        return isValidDestination(position, move);
    }

    public static boolean isKnight(Position position, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         */  
        boolean validLocation = false;

        //Check to see if the knight moved up/down one y, and over two columns. The
        //absolute values allow it to occur in any direction 
        if ((Math.abs(move.destination.y - move.piece.location.y) == 1) && (Math.abs(move.destination.x - move.piece.location.x) == 2)) {
            validLocation = true;
            
        //Check to see if the knight moved up/down two rows, and over one column. 
        //The absolute values allow it to occur in any direction. 
        } else if ((Math.abs(move.destination.y - move.piece.location.y) == 2) && (Math.abs(move.destination.x - move.piece.location.x) == 1)) {
            validLocation = true;
        }
        
        return validLocation && isValidDestination(position, move);
    }
    
    public static boolean isKing(Position position, Move move) {
        /**
         * @param boardPosition contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Does the piece travel to a location one away, and is that location free?
         *      2. Was castling attempted? If so, castling is split up into kingside and queenside cases
         *      3. Is the destination square occupied by a piece of the same colour?
         */   
        
        if (((Math.abs(move.destination.y - move.piece.location.y) == 0 && (Math.abs(move.destination.x - move.piece.location.x)) == 1)
                || (Math.abs(move.destination.y - move.piece.location.y) == 1 && (Math.abs(move.destination.x - move.piece.location.x)) == 0)
                || (Math.abs(move.destination.y - move.piece.location.y) == 1 && (Math.abs(move.destination.x - move.piece.location.x)) == 1))) {
            
            return isValidDestination(position, move);
        } else if (Math.abs(move.destination.x - move.piece.location.x) == 2 && move.destination.y - move.piece.location.y == 0) {
            if ((move.destination.x - move.piece.location.x) == -2) {
                //NOTE:: Queenside castling was attempted
                
                //Find the rook that matches the king
                Piece rook = null;
                for(int i=0; i<position.boardPosition.size(); i++) {
                    //For queenside castling, the x position of the rook will be zero, and the y will match that of the king
                    if(position.boardPosition.get(i).location.x == 0 && position.boardPosition.get(i).location.y == move.piece.location.y) {
                        rook = position.boardPosition.get(i);
                    }
                } 
                //If the rook is not found (it has moved likely), or it has moved away and then back, then castling is invalid
                if(rook != null && move.piece.hasMoved == false && rook.hasMoved == false) {
                    //check the path between the rook and the king for checks and pieces
                    //get all of the squares between the king and the rook and check that
                    //      1. The square isn't controlled by an opponent's piece
                    //      2. The square isn't occupied
                    
                    if(move.piece.isAttacked(position) == false) {
                        //NOTE:: could convert this to an array to avoid having the three variables
                        Square oneLeft = new Square(move.piece.location.x - 1, move.piece.location.y);
                        Square twoLeft = new Square(move.piece.location.x - 2, move.piece.location.y);
                        Square threeLeft = new Square(move.piece.location.x - 3, move.piece.location.y);

                        if(oneLeft.squareControlled(position, move.piece.pieceColour) == false &&
                                oneLeft.isPieceOnSquare(position) == false &&
                                twoLeft.squareControlled(position, move.piece.pieceColour) == false &&
                                twoLeft.isPieceOnSquare(position) == false &&
                                threeLeft.isPieceOnSquare(position) == false) {

                            return true;
                        }
                    }
                }
            } else if ((move.destination.x - move.piece.location.x) == 2) {
                //NOTE:: castling kingside was attempted
                
                //Find the rook that matches the king
                Piece rook = null;
                for(int i=0; i<position.boardPosition.size(); i++) {
                    //For queenside castling, thhe x position of the rook will be 7, and the y will match that of the king
                    if(position.boardPosition.get(i).location.x == 7 && position.boardPosition.get(i).location.y == move.piece.location.y) {
                        rook = position.boardPosition.get(i);
                    }
                } 
                
                if(rook != null && move.piece.hasMoved == false && rook.hasMoved == false) {
                    //check the path between the rook and the king for checks and pieces
                    //get all of the squares between the king and the rook and check that
                    //      1. The square isn't controlled by an opponent's piece
                    //      2. The square isn't occupied
                    if(move.piece.isAttacked(position) == false) {
                        Square oneLeft = new Square(move.piece.location.x + 1, move.piece.location.y);
                        Square twoLeft = new Square(move.piece.location.x + 2, move.piece.location.y);
                        
                        if(oneLeft.squareControlled(position, move.piece.pieceColour) == false &&
                                oneLeft.isPieceOnSquare(position) == false &&
                                twoLeft.squareControlled(position, move.piece.pieceColour) == false &&
                                twoLeft.isPieceOnSquare(position) == false) {

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isPawn(Position position, Move move) {
        /**
         * @param position contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the move is valid
         * The Algorithm
         *      1. Determine the colour of the pawn that is being move (and so the direction that it travels)
         *      2. Check through the attempts to move the pawn forward (using direction) one
         *      3. Check through the attempts to move the pawn forward (using direction) two
         *      4. Check captures (if the piece is on the destination square), and enpassant
         *  
         */   
        boolean validLocation = false; //Valid location => the square is correct -> check that the square is not occupied
        
        Square destinationSquare = new Square(move.destination.x, move.destination.y);
        
        int direction = (move.piece.pieceColour == Piece.COLOUR.WHITE) ? 1 : -1; //White: 1, Black: -1
        
        if ((move.destination.x == move.piece.location.x) && ((move.destination.y - move.piece.location.y) == direction)) {
            //This occurs if the pawn is attempted to have be moved forward once
            //Check to see that the destination square is empty (the move is valid)
            return destinationSquare.isPieceOnSquare(position) == false;
        } else if ((move.destination.x == move.piece.location.x) && ((move.destination.y - move.piece.location.y) == (direction*2)) && move.piece.hasMoved == false) {
            //This is if the pawn has been attempted to have been moved two forward
            //The pawn can't have moved, and the two squares in front need to be empty
            Square intermediateSquare = new Square(move.destination.x, (move.destination.y - direction));
            
            return destinationSquare.isPieceOnSquare(position) == false && intermediateSquare.isPieceOnSquare(position) == false;
            
        } else if (move.destination.y == (move.piece.location.y + direction) && (move.destination.x == (move.piece.location.x + 1) || move.destination.x == (move.piece.location.x - 1))) {
            //a capture or enpassant was attempted
            for(int i=0; i<position.boardPosition.size(); i++) {
                if(position.boardPosition.get(i).location.x == move.destination.x && position.boardPosition.get(i).location.y == move.destination.y) {
                    if(position.boardPosition.get(i).pieceColour == move.piece.pieceColour){
                        return false;
                    }
                    //a capture was attempted
                    validLocation = true;
                }
            }   
                
            if(validLocation == false) {
                //this checks for enpassant
                //There must be a valid last move
                //The destination square must be empty 
                //The piece beside the pawn (one y value up from the destination square) must be a pawn
                //That pawn beside the pawn being moved must be of the opponent's colour
                //That pawn beside the pawn being moved must have just moved up two squares
                if(position.getLastMoveExists() && !destinationSquare.isPieceOnSquare(position)) {
                    //The column is the same as the destination, the row is the same as the location
                    Square squareBeside = new Square(destinationSquare.x, move.piece.location.y);
                    if(squareBeside.isPieceOnSquare(position)) {
                        for(Piece p : position.boardPosition) {
                            if(p.location.x == squareBeside.x && p.location.y == squareBeside.y) {
                                if(p.pieceColour != move.piece.pieceColour) {
                                    //Check that the last move was moving a pawn up two
                                    validLocation = position.getLastMove().destination.x == squareBeside.x &&
                                            position.getLastMove().destination.y == squareBeside.y &&
                                            position.getLastMove().piece.pieceType == Piece.TYPE.PAWN &&
                                            position.getLastMove().piece.location.x == squareBeside.x &&
                                            position.getLastMove().piece.location.y == squareBeside.y + 2*direction;
                                }
                            }
                        }
                    }
                }
            } 
        }
        
        return validLocation && isValidDestination(position, move);
    }
    
    public boolean destinationIsEmpty(Position position, Move move) {
        Square square = new Square(move.destination.x, move.destination.y);
        
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).isOnSquare(square)) {
                if(position.boardPosition.get(i).pieceColour == move.piece.pieceColour){
                    return false;
                }
            }
        } 
        return true;
    }
    public static boolean isValidDestination(Position position, Move move) {
        /**
         * @param position contains the current state of the board
         * @param move is the move in the current board position that is be assessed for validity
         * @returns boolean indicating whether or not the destination square contains a piece of the same colour as the one being moved
         * and whether or not the king is attacked in the final position
         */  
        Square square = new Square(move.destination.x, move.destination.y);
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).isOnSquare(square)) {
                if(position.boardPosition.get(i).pieceColour == move.piece.pieceColour){
                    return false;
                }
            }
        } 
        
        return true;
    }
    
    /**
     * @return boolean indicating if the king is attacked
     * @summary checks to see if the king is attacked or not
     * @param position
     * @param move
     */
    public static boolean isKingAttacked(Position position, Move move) throws Exception {
        Position virtualPosition = new Position(position);
        Move virtualMove = new Move(move);
        
        virtualMove.executeMove(virtualPosition);
        //Check that the king is not attacked in the final position
        Piece moversKing = null;
        for(Piece piece : virtualPosition.boardPosition) {
            if(piece.pieceType == Piece.TYPE.KING && piece.pieceColour == virtualMove.piece.pieceColour) {
                moversKing = piece;
            }
        }
        if(moversKing == null)
        {
            throw new Exception("Illegal Position");
        }
        //Ensure that the king was found and is not attacked
        return moversKing.location.squareControlled(virtualPosition, virtualMove.piece.pieceColour);
    }
}
