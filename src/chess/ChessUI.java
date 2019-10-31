package Chess;

import java.awt.Color;
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
    private Game game;
    
    public ChessUI(Game g) {
        this.boardButtons = new JButton[8][8];
        this.game = g;
        
        display();
    }   
    
    private void display(){
        
        this.setLayout(new GridLayout(8,8));
        for(int i=7; i>=0; i--) {
            for(int j=0; j<8; j++) {
                boardButtons[j][i] = new JButton();
                this.add(boardButtons[j][i]);
                boardButtons[j][i].addActionListener(this);
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
        //Update the background Colour
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                Color buttonColour = Color.getHSBColor(0.0f, 0.0f, 0.663f); //defaul to white square
                if((i+j)%2 == 0) {
                    //Black square if the sum of the square indexs is even
                    buttonColour = Color.getHSBColor(0.0f, 0.0f, 0.333f);
                }
                
                boardButtons[i][j].setBackground(buttonColour);
            }
        }
        for(Piece piece : position.boardPosition) {
            boardButtons[piece.location.x][piece.location.y].setIcon(new ImageIcon(piece.getImage()));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton selectedButton = (JButton)arg0.getSource();
        
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                if(boardButtons[col][row] == selectedButton) {
                    game.squareSelected(new Square(col, row));
                }
            }
        }
    }
}
