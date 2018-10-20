package Chess;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb
 */
public class ChessUI extends JFrame implements MouseListener{
    Point initialPosition, finalPosition;
    
    public ChessUI() {
        //Define variables
        
        this.setSize(1100,900);
        
        for(int i=0; i<Main.pieces.size(); i++) {
            JLabel pieceHolder = new JLabel();
            pieceHolder.setSize(100, 100);
            pieceHolder.setLocation(xCoordinateToPixels(Main.pieces.get(i).x), yCoordinateToPixels(Main.pieces.get(i).y, 700));
            pieceHolder.setIcon(Main.pieces.get(i).pieceImage);
            if(Main.pieces.get(i).pieceType.equals("rook")) {
                System.out.println("Row: " + xCoordinateToPixels(Main.pieces.get(i).x) + " Column: " + yCoordinateToPixels(Main.pieces.get(i).y, 700));
            }
            this.add(pieceHolder);
        }
        //ContentPanel contentPanel = new ContentPanel(image,5,5);
        /*
        for(int i=0; i<Main.pieces.size(); i++) {
            this.add(new ContentPanel(Main.pieces.get(i).pieceImage, Main.pieces.get(i).x, Main.pieces.get(i).y));
        }*/
        
        this.setVisible(true);
        this.addMouseListener(this);
    }   
    public static int xCoordinateToPixels(int coordinate) {
        return coordinate*100;
    }
    
    public static int xPixelToCoordinate(int pixel) {
        float roughCoordinate = pixel/100;
        return (int) Math.floor(roughCoordinate);
    }
    
    public static int yCoordinateToPixels(int coordinate, int constant) {
        //constant could be 0 or 700
        return Math.abs(constant - coordinate*100);
    }
    
    public static int yPixelToCoordinate(int pixel, int constant) {
        /**
         * @param pixel (pixel location on the board)
         */
        float roughCoordinate = (pixel-30)/100; 
        return (int) Math.abs(constant - Math.floor(roughCoordinate));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Not yet used (could eventually be implemented for some purpose)
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.initialPosition = this.getMousePosition();
    }

    
    @Override
    public void mouseReleased(MouseEvent e) {
        this.finalPosition = this.getMousePosition();
        
        Square source = new Square ((yPixelToCoordinate((int)initialPosition.getY(),0)), (xPixelToCoordinate((int)initialPosition.getX())));
        System.out.println(source.col);
        Square destination;

        System.out.println(initialPosition.getX());
        System.out.println(initialPosition.getY());
        //do the logic in here for now. Eventually it should probably get moved
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Not used yet (could eventually be implemented for some purpose)
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not used yet (could eventually be implemented for some purpose)
    }
    
}

/**
 * Old attempt to have moving graphics: Will revisit later when developing the 
 * full graphical display.
 */
//class ContentPanel extends JPanel{ 
//    BufferedImage image;
//    int xPixel,yPixel;
//    /*
//    public ImagePanel(){
//        System.out.println("Into image Panel");
//        
//    }*/
//    //BufferedImage image = new ImageIcon(getClass().getResource("chessboard.jpg"));
//    public ContentPanel(BufferedImage img, int xCoordinate, int yCoordinate) {
//         //Init variables
//       // JLabel board_container = new JLabel();
//        //this.setLayout(null);
//        
//        
//        /*
//        board_container.setSize(800,800);
//        board_container.setIcon(board_img);
//  
//        board_container.setLocation(0,0);
//        */
//        //Associate the variables with the JFrame
//        //this.add(board_container);
//        /*
//        addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                System.out.println(e.getX());
//            }
//        });
//
//        addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                System.out.println(e.getX());
//            }
//        });*/
//        
//        
//    }
//    
//    /*
//    @Override
//    protected void paintComponent(Graphics g) {
//               
//        for(int i=0; i<Main.pieces.size(); i++) {
//            System.out.println(ChessUI.xCoordinateToPixels(Main.pieces.get(i).x) + ", " + ChessUI.yCoordinateToPixels(Main.pieces.get(i).y,0));
//            Graphics gCopy = g.create();
//            super.paintComponent(gCopy);
//            gCopy.drawImage(Main.pieces.get(i).pieceImage, ChessUI.xCoordinateToPixels(Main.pieces.get(i).x), ChessUI.yCoordinateToPixels(Main.pieces.get(i).y,0), this);
//            JLabel c = new JLabel();
//            c.setBounds(ChessUI.xCoordinateToPixels(Main.pieces.get(i).x), ChessUI.yCoordinateToPixels(Main.pieces.get(i).y,0), 100, 100);
//            c.paint(gCopy);
//        }
//    }  */
//}