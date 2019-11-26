/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.Piece.COLOUR;
import java.util.ArrayList;
/**
 * This class is used to search through many positions recursively to be able to
 * generate a final position to evaluate
 * @author Caleb
 */
public class SearchGraph {
    
    public static Move findBestMove(Position position, COLOUR playerMoving) {
        //Only call this if position.generateMoves() > 0
        
        ArrayList<Move> possibleMoves = position.generateMoves();
        boolean maximizing = playerMoving == COLOUR.WHITE;
        int selectedIndex = -1;
        
        
        int bestEvaluation = maximizing ? Constants.BOTTOM_EVALUATION : Constants.TOP_EVALUATION;
        
        for(int i=0; i<possibleMoves.size(); i++) {
            Move virtualMove = new Move(possibleMoves.get(i));
            Position virtualPosition = executeVirtualMove(position, virtualMove);
            int searchEvaluation = search(virtualPosition, Constants.SEARCH_DEPTH, !maximizing);
            
            if(maximizing && searchEvaluation > bestEvaluation) {
                bestEvaluation = searchEvaluation;
                selectedIndex = i;
            }
            else if(!maximizing && searchEvaluation < bestEvaluation){
                bestEvaluation = searchEvaluation;
                selectedIndex = i;
            }
        }
        
        
        //Ensure that there wasn't some failure to select a move
        if(selectedIndex > -1) {
            return possibleMoves.get(selectedIndex);
        }
        //TODO implement some kind of exception if the possible moves size is 0
        System.out.println("THIS SHOULDN'T BE HAPPENING!!! CATASTROPHIC FAILURE");
        return null;
    }
    public static int search(Position position, int depth, boolean maximizing) {
        if(depth == 0) {
            return position.getEvaluation();
        }
        ArrayList<Move> possibleMoves = position.generateMoves();

        
        //set the initial optimization value to be either very low or very high
        int bestEvaluation = maximizing ? Constants.BOTTOM_EVALUATION : Constants.TOP_EVALUATION; 

        for(Move move : possibleMoves) {
            //Get the search evaluation result
            Position virtualPosition = executeVirtualMove(position, move);
            int searchEvaluation = search(virtualPosition, depth - 1, !maximizing);
            
            //Find the max or min depending on who is being optimized for
            if(maximizing) {
                bestEvaluation = Math.max(bestEvaluation, searchEvaluation);
            }
            else {
                bestEvaluation = Math.min(bestEvaluation, searchEvaluation);
            }
        }
        
        return bestEvaluation;
        
    }
    
    public static Position executeVirtualMove(Position position, Move move) {
        //make a deep copy of the position using the copy constructor
        Position virtualPosition = new Position(position);
        System.out.println("Executed virtual move");
        move.executeMove(virtualPosition);
        
        return virtualPosition;
    }
}
