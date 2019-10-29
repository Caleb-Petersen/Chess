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
    public Move lastMove;
    
    public Position(FEN initFEN) {
        this.boardPosition = initFEN.fenToBoardPosition();
    }
    public Position(ArrayList<Piece> b) {
        this.boardPosition = b;
    }
    public Position(ArrayList<Piece> b, Move m) {
        this.boardPosition = b;
        this.lastMove = m;
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
        this.lastMove = new Move(p.lastMove);
    }
    
    public int getEvaluation() {
        int evaluation = 0;
        
        //Sum up the values of all of the pieces for rudimentary evaluation
        for(Piece p : this.boardPosition) {
            evaluation += p.getValue();
        }
        System.out.println("Evaluation of the position is: " + evaluation);
        return evaluation;
    }
    
    public ArrayList<Move> generateMoves() {
        //Call generate moves with the opposite colour that is recorded in the move history
        if(this.lastMove.piece.pieceColour == COLOUR.BLACK) {
            return this.generateMoves(COLOUR.WHITE);
        }
        return this.generateMoves(COLOUR.BLACK);
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
