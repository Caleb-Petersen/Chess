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
    public Move move;
    public Position position; //The position before the move
    
    public MoveHistory(Move initMove, Position initPosition) {
        this.move = initMove;
        this.position = initPosition;
    }
    
    public MoveHistory(MoveHistory mh) {
        this.move = new Move(mh.move);
        this.position = new Position(mh.position);
    }
}

