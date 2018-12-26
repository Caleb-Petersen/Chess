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
public class Position {
    public FEN fen;
    public double eval;
    
    public Position(FEN initFEN) {
        this.fen = initFEN;
        this.eval = 0;
    }
}
