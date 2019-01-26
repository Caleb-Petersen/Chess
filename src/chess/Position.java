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
    
    public Position(FEN initFEN) {
        this.boardPosition = initFEN.fenToBoardPosition();
        this.fen = initFEN;
    }
    public Position(FEN initFEN, ArrayList<Piece> b) {
        this.boardPosition = b;
        this.fen = initFEN;
    }
}
