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
    public MoveHistory search(Position position, ArrayList<MoveHistory> path, MoveHistory moveHistory, int depth) {
        //For testing purposes assume that the engine is always black move. This will be fixed later
        //NOTE: somehow I need to store move history here, so that once depth is reached the function
        //will have access to the moves that were played up until that point
        System.out.println("_____________________________________________");
        System.out.println("Path size: " + path.size());
        System.out.println("Board Position: " + position.boardPosition.size());
        System.out.println("Depth: " + depth);
        System.out.println("_____________________________________________");
       
        if(depth == this.DEPTH) {
            return moveHistory;
        }else {
            ArrayList<Move> possibleMoves = generateMoves(position);
            ArrayList<MoveHistory> moveOptions = new ArrayList<>();
            System.out.println("Generated Moves");
            for(int i=0; i<possibleMoves.size(); i++) {
                Position virtualPosition = executeVirtualMove(position, possibleMoves.get(i));
                
                moveOptions.add(search(virtualPosition,new ArrayList<>(), (new MoveHistory(possibleMoves.get(i),virtualPosition)), depth + 1));
                
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
    public ArrayList<Move> generateMoves(Position position) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        System.out.println("This thing here");
        for(int i=0; i<position.boardPosition.size(); i++) {
            Piece piece = position.boardPosition.get(i);
            piece.updatePossibleDestinations(position);
            
            for(int j=0; j<piece.possibleDestinations.size(); j++) {
                possibleMoves.add(new Move(piece, piece.possibleDestinations.get(j)));
            }
        }
        return possibleMoves;
    }
  
    public Position executeVirtualMove(Position position, Move move) {
        ArrayList<Piece> copyBoardPosition = Main.copyBoardPosition(position.boardPosition);
        for(int i=0; i< copyBoardPosition.size(); i++) {
            //manual check to avoid issues with objects and such
            if(copyBoardPosition.get(i).location.x == move.piece.location.x && copyBoardPosition.get(i).location.y == move.piece.location.y)  {
                copyBoardPosition.get(i).hasMoved = true;
                copyBoardPosition.get(i).location.x = move.destination.x;
                copyBoardPosition.get(i).location.y = move.destination.y;
                
                if(move.isCastling(position)) {
                    System.out.println("need to find associated rook, and move it too");
                }
            }
        }
        Position createdPosition = new Position(copyBoardPosition);
        return createdPosition;
    }
}
