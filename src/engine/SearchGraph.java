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
    
    public SearchGraph(Position p, MoveHistory m) {
        this.position = p;
        this.lastMove = m;
    }
    //search(Main.piece, new MoveHistory(), 0)
    public MoveHistory search(ArrayList<Piece> boardPosition, ArrayList<MoveHistory> path, MoveHistory moveHistory, int depth) {
        //For testing purposes assume that the engine is always black move. This will be fixed later
        //NOTE: somehow I need to store move history here, so that once depth is reached the function
        //will have access to the moves that were played up until that point
        if(depth == this.DEPTH) {
            return moveHistory;
        }else {
            ArrayList<Move> possibleMoves = generateMoves(boardPosition);
            
            ArrayList<MoveHistory> moveOptions = new ArrayList();
            for(int i=0; i<possibleMoves.size(); i++) {
                Position virtualPosition = executeVirtualMove(boardPosition, possibleMoves.get(i));
                
                moveOptions.add(search(virtualPosition.fen.fenToBoardPosition(),new ArrayList<>(), (new MoveHistory(possibleMoves.get(i),virtualPosition)), depth + 1));
                
            }
            //calculate max or min of the possible moves (dependent on depth because depth represents colour when using %2, and add it to the path
            if(depth %2 == 0) {
                path.add(maxMoveOption(moveOptions));
            }else {
                path.add(minMoveOption(moveOptions));
            }
        }
        
        return path.get(0);
        
    }
    
    public MoveHistory maxMoveOption(ArrayList<MoveHistory> moveOptions) {
        //Returns the highest eval of the list
        int index = 0;
        //need a way to calculate eval from move history
        return moveOptions.get(index);
    }
    public MoveHistory minMoveOption(ArrayList<MoveHistory> moveOptions) {
        //returns the lowest eval of the list
        int index = 0;
        //need a way to calculate eval from move history
        return moveOptions.get(index);
    }       
    public ArrayList<Move> generateMoves(ArrayList<Piece> boardPosition) {
        ArrayList<Move> possibleMoves = new ArrayList();
        System.out.println("This thing here");
        for(int i=0; i<boardPosition.size(); i++) {
            Piece piece = boardPosition.get(i);
            piece.updatePossibleDestinations(boardPosition);
            for(int j=0; j<piece.possibleDestinations.size(); j++) {
                possibleMoves.add(new Move(piece, piece.possibleDestinations.get(j)));
            }
        }
        return possibleMoves;
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
