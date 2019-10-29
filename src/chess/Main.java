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
                displayChessBoard();
            }
        });
    }
    
    private static void displayChessBoard(){
        //The initial position in an position string
        //rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        
        //rnbqk2r/1pp1b1p1/3p4/p3p1np/4Pp2/2PPBN1P/PPQNBPP1/R3K2R
        FEN initialPosition = new FEN(Constants.ORIGINAL_POSITION_FEN);
        
        pieces = initialPosition.fenToBoardPosition();
        //FEN random = new FEN("");
        //random.createFEN(pieces);
        
        //pieces.add(new Piece(Piece.TYPE.KING, Piece.COLOUR.BLACK, 5,5));
        //Position position = new Position(initialPosition);
        //SearchGraph graph = new SearchGraph(position, null);
        //MoveHistory history = graph.search(pieces, new ArrayList<>(), null, 0);
        //System.out.println(history.move.destination.x);
        /*
        Position testing = new Position("rnbqk2r/1pp1b1p1/3p4/p3p1np/4Pp2/2PPBN1P/PPQNBPP1/R3K2R");
        ArrayList<Piece> jazz = new ArrayList<Piece>();
        jazz = testing.fenToBoardPosition();
        position rand = new position("");
        rand.createFEN(jazz);
        System.out.println(rand.position.equals("rnbqk2r/1pp1b1p1/3p4/p3p1np/4Pp2/2PPBN1P/PPQNBPP1/R3K2R"));
        System.out.println(rand.position);
        */
        
        ChessUI chessUI = new ChessUI();
        
        //set up the JFrame
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(1100,900);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.getContentPane().add(chessUI);
        
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
        
    }
    public static void infoBox(String infoMessage, String titleBar) {
        /**
         * @param infoMessage message to be displayed to the user
         * @param titleBar the title of the box displayed to the user
         */
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
