/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;

/**
 *
 * @author Caleb
 */
public class Position {
   // public FEN fen;
    public ArrayList<Piece> boardPosition;
    public MoveHistory lastMove;
    
    public Position(FEN initFEN) {
        this.boardPosition = initFEN.fenToBoardPosition();
    }
    public Position(ArrayList<Piece> b) {
        this.boardPosition = b;
    }
    public Position(ArrayList<Piece> b, MoveHistory mh) {
        this.boardPosition = b;
        this.lastMove = mh;
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
        this.lastMove = new MoveHistory(p.lastMove);
    }
}
