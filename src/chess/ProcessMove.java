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
public class ProcessMove {
    
    public static void processMove(ArrayList<Piece> boardPosition, Move move) {
        System.out.println("PROCESS MOVE");
        if(move.isValidMove(boardPosition)) {
            move.executeMove(boardPosition);
        }else {
            Main.infoBox("Error", "Invalid Move");
        }
    }
}
