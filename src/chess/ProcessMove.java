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
public class ProcessMove {
    
    public static void processMove(Move move) {
        System.out.println("PROCESS MOVE");
        if(move.isValidMove()) {
            move.executeMove();
        }else {
            Main.infoBox("Error", "Invalid Move");
        }
    }
}
