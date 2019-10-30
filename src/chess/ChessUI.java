package Chess;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb
 */
public class ChessUI extends JPanel implements ActionListener{
    private final JButton[][] boardButtons;
    public Position currentPosition;
    
    public ChessUI() {
        this.boardButtons = new JButton[8][8];
        FEN initialPosition = new FEN(Constants.ORIGINAL_POSITION_FEN);
        this.currentPosition =  new Position(initialPosition.fenToBoardPosition());
        
        display();
        updateView(this.currentPosition);
    }   
    
    private void display(){
        
        this.setLayout(new GridLayout(8,8));
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                boardButtons[i][j] = new JButton();
                this.add(boardButtons[i][j]);
                boardButtons[i][j].addActionListener(this);
            }
        }
        
        //set up the JFrame
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
    
    public void updateView(Position position) {
        for(Piece piece : position.boardPosition) {
            boardButtons[piece.location.x][piece.location.y].setIcon(new ImageIcon(piece.getImage()));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton selectedButton = (JButton)arg0.getSource();
        
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                if(boardButtons[row][col] == selectedButton) {
                    System.out.println("row is " + row);
                    System.out.println("col is " + col);
                }
            }
        }
    }
    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(!engineMoving) {
            if(!firstClickOccured) {
                this.initialPosition = this.getMousePosition();
                firstClickOccured = true;
            }
            else {
                this.finalPosition = this.getMousePosition();
        
                //The source square is the location (in coordinates: (0-7, 0-7)) of the piece that was initially clicked
                Square source = new Square ((xPixelToCoordinate((int) initialPosition.getX())),(yPixelToCoordinate((int) initialPosition.getY(),7)));
                //the destination square is the end location (in coordinates: (0-7, 0-7)) of the piece that was moved
                Square destination = new Square ((xPixelToCoordinate((int) finalPosition.getX())),(yPixelToCoordinate((int) finalPosition.getY(),7)));

                Piece piece = null;
                //Get the piece that was initially clicked. If no such piece exists, then inform the user of that fact
                for(int i=0; i<Main.pieces.size(); i++) {
                    if(Main.pieces.get(i).location.x == source.x && Main.pieces.get(i).location.y == source.y) {
                        piece = Main.pieces.get(i);
                    }
                }
                if(piece != null) {
                    Move move = new Move(piece, destination);

                    Position position = new Position(Main.pieces);
                    if(move.isValidMove(position)) {
                        move.executeMove(position);
                        repaint();

                        //Move the computer
                        engineMoving = true;
                        Position p = new Position(Main.pieces, move);
                        SearchGraph.findBestMove(p, Piece.COLOUR.BLACK).executeMove(p);
                        engineMoving = false;
                    }else {
                        Main.infoBox("Invalid Move!", "Error");
                    }
                }else {
                    Main.infoBox("You didn't select a piece!", "Error");
                }
                this.firstClickOccured = false;
            }
        }
    }
    */

    
}
