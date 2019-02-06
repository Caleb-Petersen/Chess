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
    public FEN fen;
    public ArrayList<Piece> boardPosition;
    public MoveHistory lastMove;
    
    public Position(FEN initFEN) {
        this.boardPosition = initFEN.fenToBoardPosition();
        this.fen = initFEN;
    }
    public Position(ArrayList<Piece> b) {
        this.boardPosition = b;
        
    }
    public Position(FEN initFEN, ArrayList<Piece> b) {
        this.boardPosition = b;
        this.fen = initFEN;
    }
    //TODO add a function in here that will construct an FEN from the arraylist of pieces
}
