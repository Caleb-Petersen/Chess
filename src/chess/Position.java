/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.Piece.COLOUR;
import java.util.ArrayList;

/**
 *
 * @author Caleb
 */
public class Position {
   // public FEN fen;
    public ArrayList<Piece> boardPosition;
    private Move lastMove;
    private boolean lastMoveExists;
    
    public Position(FEN initFEN) {
        this.boardPosition = initFEN.fenToBoardPosition();
        this.lastMoveExists = false;
    }
    public Position(ArrayList<Piece> b) {
        this.boardPosition = b;
        this.lastMoveExists = false;
    }
    public Position(ArrayList<Piece> b, Move m) {
        this.boardPosition = b;
        this.lastMove = m;
        this.lastMoveExists = true;
    }
    
    public Position(Position p) {
        /**
         * Creates a deep copy of the boardPosition array for a new position
         */
        ArrayList<Piece> copy = new ArrayList<>();

        for(Piece piece : p.boardPosition) {
            copy.add(new Piece(piece));
        }
        this.boardPosition = copy;
        
        if(p.lastMove != null) {
            this.lastMove = new Move(p.lastMove);
        }else {
            this.lastMove = null;
        }
        
        this.lastMoveExists = p.lastMove != null;
    }
    
    public boolean getLastMoveExists() {
        return this.lastMoveExists;
    }
    
    public Move getLastMove() {
        return this.lastMove;
    }
    public void setLastMove(Move move) {
        this.lastMove = new Move(move);
        this.lastMoveExists = move != null;
    }
    public int getEvaluation() {
        int evaluation = 0;
        
        //Sum up the values of all of the pieces for rudimentary evaluation
        for(Piece p : this.boardPosition) {
            evaluation += p.getValue();
        }
        return evaluation;
    }
    
    public ArrayList<Move> generateMoves() {
        //Call generate moves with the opposite colour that is recorded in the move history
        if(this.lastMoveExists) {
            if(this.lastMove.piece.pieceColour == COLOUR.BLACK){
                return this.generateMoves(COLOUR.WHITE);
            }
            return this.generateMoves(COLOUR.BLACK);
        }
        
        return new ArrayList<>();
    }
    
    public ArrayList<Move> generateMoves(COLOUR colour) {
        //generates the possible moves for the colour specified
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for(Piece piece : this.boardPosition) {
            //Ensure that the moves generated are for one colour only
            if(piece.pieceColour == colour) {
                piece.updatePossibleDestinations(this);
            
                for(Square destinationSquare : piece.possibleDestinations) {
                    possibleMoves.add(new Move(piece, destinationSquare));
                }
            }
        }
        return possibleMoves;
    }
    
}
