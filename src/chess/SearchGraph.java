/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

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
    public int search(Position position, int depth, boolean optimizing) {
        System.out.println("_____________________________________________");
        System.out.println("Optimizing: " + optimizing);
        System.out.println("Board Position: " + position.boardPosition.size());
        System.out.println("Depth: " + depth);
        System.out.println("_____________________________________________");
       
        if(depth == 0) {
            return position.getEvaluation();
        }
        ArrayList<Move> possibleMoves = position.generateMoves();

        System.out.println("Generated Moves");
        //set the initial optimization value to be either very low or very high
        int evaluation = optimizing ? -100000 : 100000; 

        for(Move move : possibleMoves) {
            Position virtualPosition = executeVirtualMove(position, move);

            evaluation = search(virtualPosition, depth - 1, !optimizing);

        }
        
        return evaluation;
        
    }
    
    public Position executeVirtualMove(Position position, Move move) {
        //make a deep copy of the position using the copy constructor
        Position virtualPosition = new Position(position);
        move.executeMove(virtualPosition);
        
        return virtualPosition;
    }
}
