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
import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                //displayChessBoard();
                ChessUI chessUI = new ChessUI();
            }
        });
    }
    
    public static void infoBox(String infoMessage, String titleBar) {
        /**
         * @param infoMessage message to be displayed to the user
         * @param titleBar the title of the box displayed to the user
         */
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
