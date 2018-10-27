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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static ArrayList<Piece> pieces = new ArrayList<Piece>();
    public static ArrayList<Piece> deletedPieces = new ArrayList<Piece>();
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                //The initial position in an FEN string
                FEN initialPosition = new FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
                pieces = initialPosition.fenToBoardPosition();
                
                ChessUI frame = new ChessUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
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



