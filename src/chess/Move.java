/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.Piece.COLOUR;
import java.util.Iterator;


/**
 * 
 * @author Caleb
 */
public class Move {
    public Piece piece;
    public Square destination;
    
    public Move(Piece p, Square s) {
        this.piece = p;
        this.destination = s;
    }
    
    public Move(Move m) {
        this.piece = new Piece(m.piece);
        this.destination = new Square(m.destination);
    }
    
    public boolean isEnpassantAttempt(Position position) {
        /**
         * @param none
         * @returns a boolean indicating whether or not a move is enpassant
         * @NOTE: Function doesn't care about the validity of the move. It only 
         * wishes to classify the move as being enpassant without a doubt
         */
        
        //Algorithm to implement
        //Check to see if the piece moved was a pawn
        //check to see if the move is valid
        //Check to see if a diagonal move was made
        //Check to see that the destination square is empty
        //Check to see that the pawn being captured (via enpassant) is actually there
        //If all of those checks are true, return true, if not, return false
        
        int direction = (this.piece.pieceColour == Piece.COLOUR.WHITE) ? 1 : -1;
        
        if(this.piece.pieceType == Piece.TYPE.PAWN) {
            //check to see if the destination row is correct for the colour of piece (3rd rank for black pieces, 6th for white)
            if((this.piece.pieceColour == Piece.COLOUR.WHITE && this.destination.y == 5) || (this.piece.pieceColour == Piece.COLOUR.BLACK && this.destination.y == 2)) {
                //check to see that the pawn was one column removed from the destination destination (e.g the e file to the f file)
                if(Math.abs(this.piece.location.x - this.destination.x) == 1) {
                    //Check to see if the piece on the destination above the destination destination contains a pawn
                    //Check may not be necessary as we don't need to know about validity
                    if(this.destination.isPieceOnSquare(position) == false) { 
                        //Check to ensure that the enpassant is actually capturing a piece
                        Square square = new Square(this.destination.x, (this.destination.y - direction));
                        if(square.pieceOnSquare(position) == Piece.TYPE.PAWN) {
                            return true; //Assume that this move is a valid move
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isCastlingAttempt() {
        /**
         * Function purpose: To determine if a move involves castling
         * NOTE:this function must be used alongside the isValidMove function
         * as it does not check if a move is legal. If a move is legal and
         * isCastling returns true, it is know that a move is castling
         */

        return isKingsideCastlingAttempt() || isQueensideCastlingAttempt();
    }
    public boolean isKingsideCastlingAttempt() {
        /**
         * Function purpose: To determine if a move is a kingside castle attempt
         * NOTE:this function must be used alongside the isValidMove function
         * as it does not check if a move is legal. If a move is legal and
         * isKingsideCastlingAttempt returns true, it is know that a move is castling
         */
        
        //Algorithm to implement
        //Check to see if the piece moved is a king
        //Check to see if the king was moved two squares
        //If all are true, return true, if not, return false
        
        if(this.piece.pieceType == Piece.TYPE.KING) {
            if(this.piece.location.x - this.destination.x == -2) {
                return true;
            }
        }
        return false;
    }
    public boolean isQueensideCastlingAttempt() {
        /**
         * Function purpose: To determine if a move is a queenside castle attempt
         * NOTE:this function must be used alongside the isValidMove function
         * as it does not check if a move is legal. If a move is legal and
         * isQueensideCastlingAttempt returns true, it is know that a move is castling
         */
        
        //Algorithm to implement
        //Check to see if the piece moved is a king
        //Check to see if the king was moved two squares
        //If all are true, return true, if not, return false
        
        if(this.piece.pieceType == Piece.TYPE.KING) {
            if(this.piece.location.x - this.destination.x == 2) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return
     * @summary this function determines if a move is legal in a strict sense
     * @param position
     */
    public boolean isLegalMove(Position position) {
        boolean validMove = isValidMove(position);
        if(validMove){
           try {
                validMove = validMove && !Validation.isKingAttacked(position, this);
           }
           catch(Exception ex){
               Main.infoBox(ex.getMessage(), "Exception when checking for legal move");
           }
        }
        
        return validMove;
    }
    /**
     * @summary this function checks to see if a piece is legally allow to move to 
     * a square disregarding the king being attacked or not (unless the king itself 
     * is moving)
     * @param position
     */
    public boolean isValidMove(Position position) {
        /**
         * @param none
         * @returns whether or not a move is valid
         */
        switch(this.piece.pieceType) {
            case KING:
                return Validation.isKing(position, this);
                
            case QUEEN:
                return Validation.isDiagonal(position, this) || Validation.isStraight(position, this);
                
            case ROOK:
                return Validation.isStraight(position, this);
                
            case BISHOP:
                return Validation.isDiagonal(position, this);
     
            case KNIGHT:
                return Validation.isKnight(position, this);
                
            default:
                return Validation.isPawn(position, this);
        }
    }
    
    public void executeMove(Position position) {
        /**
         * @param none
         * @returns nothing
         * Function purpose: To execute a move
         */
        //If a piece was captured, remove it 
        
        for(int i=0; i<position.boardPosition.size(); i++) {
            if(position.boardPosition.get(i).location.x == this.destination.x && 
                    position.boardPosition.get(i).location.y == this.destination.y) {
                position.boardPosition.remove(i);
            }
        }
        
        //Handle edge cases where another piece is captured first
        if(isCastlingAttempt()) {
            //First handle the differences between queenside and kingside castling
            int queensideRookInitialColumn = 0;
            int queensideRookDestinationColumn = 3;
            int kingsideRookInitialColumn = 7;
            int kingsideRookDestinationColumn = 5;

            //initialize the columns to 0, set them later
            int rookInitialColumn = 0;
            int rookDestinationColumn = 0;

            if(isQueensideCastlingAttempt()) {
                rookInitialColumn = queensideRookInitialColumn;
                rookDestinationColumn = queensideRookDestinationColumn;
            }else if (isKingsideCastlingAttempt()) {
                rookInitialColumn = kingsideRookInitialColumn;
                rookDestinationColumn = kingsideRookDestinationColumn;
            }

            Square rookLocation = new Square(rookInitialColumn, this.piece.location.y);
            Piece castledRook = null;

            for(Piece possibleRook : position.boardPosition) {
                if(possibleRook.location.x == rookLocation.x && possibleRook.location.y == rookLocation.y) {
                    castledRook = possibleRook;
                }
            }
            if(castledRook == null) {
                System.out.println("Castling rook not found. The move must be invalid.");
            }
            else {
                castledRook.location.x = rookDestinationColumn;
            }
        }



        if(this.isEnpassantAttempt(position)) {
            int capturedPawnRow = (this.piece.pieceColour == COLOUR.WHITE) ? (this.destination.y - 1) : (this.destination.y + 1);

            Iterator<Piece> capturedPawnIterator = position.boardPosition.iterator();
            while(capturedPawnIterator.hasNext()) {
                Piece potentialCapturedPawn = capturedPawnIterator.next();
                if(potentialCapturedPawn.location.x == this.destination.x && 
                        potentialCapturedPawn.location.y == capturedPawnRow) {
                    capturedPawnIterator.remove();
                }
            }
        }

        //Move the matching piece (to the move piece) in the position
        //This is done because the reference to the piece in this move may not exist
        //in the position, though an equivalent piece will be there.
        Piece matchingPositionPiece = null;
        for(Piece p : position.boardPosition){
            if(this.piece.isEqualPiece(p)){
                matchingPositionPiece = p;
            }
        }
        //Move the piece to the destination square
        position.setLastMove(this);
       
        this.piece.hasMoved = true;
        this.piece.location.x = this.destination.x;
        this.piece.location.y = this.destination.y;
        
        if(matchingPositionPiece != null){
            matchingPositionPiece.hasMoved = true;
            matchingPositionPiece.location.x = this.destination.x;
            matchingPositionPiece.location.y = this.destination.y;
        }        
    }
}
