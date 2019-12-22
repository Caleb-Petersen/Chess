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
        
        ArrayList<Move> possibleMoves = position.generateMoves(playerMoving);
        boolean maximizing = playerMoving == COLOUR.WHITE;
        int selectedIndex = -1;

        //Initialize the best evaluation with the worst evaluation possible
        int bestEvaluation = maximizing ? Constants.BOTTOM_EVALUATION : Constants.TOP_EVALUATION;
        
        for(int i=0; i<possibleMoves.size(); i++) {
            Position virtualPosition = executeVirtualMove(position, possibleMoves.get(i));
            int searchEvaluation = search(virtualPosition, Constants.SEARCH_DEPTH, !maximizing);
            
            if(maximizing && searchEvaluation > bestEvaluation) {
                bestEvaluation = searchEvaluation;
                selectedIndex = i;
            }
            else if(!maximizing && searchEvaluation < bestEvaluation){
                bestEvaluation = searchEvaluation;
                selectedIndex = i;
            }
            else if(searchEvaluation == bestEvaluation && selectedIndex == -1){
                bestEvaluation = searchEvaluation;
                selectedIndex = i;
            }
        }
        
        
        //Ensure that there wasn't some failure to select a move
        if(selectedIndex > -1) {
            return possibleMoves.get(selectedIndex);
        }
        //this is checkmate
        return null;
    }
    public static int search(Position position, int depth, boolean maximizing) {
        if(depth == 0) {
            return position.getEvaluation();
        }
        COLOUR playerColour = extractColour(maximizing);
        ArrayList<Move> possibleMoves = position.generateMoves(playerColour);
        
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
        Move virtualMove = new Move(move);
        virtualMove.executeMove(virtualPosition);
        
        return virtualPosition;
    }
    
    private static COLOUR extractColour(boolean maximizing) {
        if(maximizing){
            return COLOUR.WHITE;
        }
        return COLOUR.BLACK;
    }
}
