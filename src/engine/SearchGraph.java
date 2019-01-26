/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import Chess.*;
import java.util.ArrayList;
/**
 * This class is used to search through many positions recursively to be able to
 * generate a final position to evaluate
 * @author Caleb
 */
public class SearchGraph {
    public Position position;
    public MoveHistory lastMove;
    public final int DEPTH = 3;
    public int currentDepth;
    
    public SearchGraph(Position p, MoveHistory m) {
        this.position = p;
        this.lastMove = m;
        this.currentDepth = 0; 
    }
    
    public void search(ArrayList<Piece> boardPosition) {
        //For testing purposes assume that the engine is always black move. This will be fixed later
        
        if(this.currentDepth != this.DEPTH) {
            currentDepth++;
            ArrayList<Position> possiblePositions = generateMoves(boardPosition);
            for(int i=0; i<possiblePositions.size(); i++) {
                search(possiblePositions.get(i).fen.fenToBoardPosition());
            }
        }else {
            //the end of the search graph has been reached
        }
        
    }
    
    public ArrayList<Position> generateMoves(ArrayList<Piece> boardPosition) {
        ArrayList<Position> possiblePositions = new ArrayList();
        for(int i=0; i<boardPosition.size(); i++) {
            Piece piece = boardPosition.get(i);
            piece.updatePossibleDestinations();
            for(int j=0; j<piece.possibleDestinations.size(); j++) {
                Move move = new Move(piece, piece.possibleDestinations.get(j));
                possiblePositions.add(executeVirtualMove(boardPosition, move));
            }
        }
        return possiblePositions;
    }
  
    public Position executeVirtualMove(ArrayList<Piece> boardPosition, Move move) {
        for(int i=0; i< boardPosition.size(); i++) {
            //manual check to avoid issues with objects and such
            if(boardPosition.get(i).location.x == move.piece.location.x && boardPosition.get(i).location.y == move.piece.location.y)  {
                boardPosition.get(i).hasMoved = true;
                boardPosition.get(i).location.x = move.destination.x;
                boardPosition.get(i).location.y = move.destination.y;
                
                if(move.isCastling()) {
                    System.out.println("need to find associated rook, and move it too");
                }
            }
        }
        FEN fen = new FEN();
        fen.createFEN(boardPosition);
        Position position = new Position(fen, boardPosition);
        return position;
    }
}
