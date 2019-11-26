/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author Caleb
 */
public class MoveHistory {
    private final boolean isValidMoveHistory; //Indicator that the movehistory is not just a placeholder
    public Move move;
    public Position position; //The position before the move
    
    public MoveHistory() {
        //This is a placeholder move history element for when no last move exists
        this.isValidMoveHistory = false;
        this.move = null;
        this.position = null;
    }
    public MoveHistory(Move initMove, Position initPosition) {
        this.isValidMoveHistory = true;
        this.move = initMove;
        this.position = initPosition;
    }
    
    public MoveHistory(MoveHistory mh) {
        this.isValidMoveHistory = true;
        this.move = new Move(mh.move);
        this.position = new Position(mh.position);
    }
    
    public boolean getIsValidMoveHistory() {
        return this.isValidMoveHistory;
    }
}

