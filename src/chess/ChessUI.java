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
            pieceHolder.setLocation(xCoordinateToPixels(Main.pieces.get(i).x), yCoordinateToPixels(Main.pieces.get(i).y, 7));
            pieceHolder.setIcon(Main.pieces.get(i).pieceImage);
            if(Main.pieces.get(i).pieceType.equals("rook")) {
                System.out.println("Row: " + xCoordinateToPixels(Main.pieces.get(i).x) + " Column: " + yCoordinateToPixels(Main.pieces.get(i).y, 7));
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
        /**
         * @param pixel (the value (between 0 and 800) of the x pixel )
         * @returns the column value that matches the pixel
         * @example: if pixel = 750, the column value returned would be 7 (the max possible)
         */
        return (int) Math.floor(pixel/100.0);
    }
    
    public static int yCoordinateToPixels(int coordinate, int constant) {
        /**
         * @param coordinate (the value (between 0 and 7) for each row
         * @param constant (either 7 (when playing white) or 0 (when playing black))
         * @returns the top left pixel of the indicated row
         */
        return Math.abs(constant - coordinate)*100;
    }
    
    public static int yPixelToCoordinate(int pixel, int constant) {
        /**
         * @param pixel (pixel location on the board: it needs to be less than 840)
         * @param constant which is either 7 (when playing white) or 0 (when playing black)
         * @returns the row coordinate that matches the y pixel that was put in
         */
        return (int) Math.abs(constant - Math.floor((pixel-40)/100.0));
    }
    
    public void drawBoard() {
        /**
         * Redraws the images on the board
         * NOTE: This is for development only. Actual graphical display will not
         * use this, and will added once the rest of the program is in working order
         */
        this.getContentPane().removeAll();
 
       for(int i=0; i<Main.pieces.size(); i++) {
            JLabel pieceHolder = new JLabel();
            pieceHolder.setSize(100, 100);
            pieceHolder.setLocation(xCoordinateToPixels(Main.pieces.get(i).x), yCoordinateToPixels(Main.pieces.get(i).y, 7));
            pieceHolder.setIcon(Main.pieces.get(i).pieceImage);
            this.add(pieceHolder);
        }
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
        
        //The source square is the location (in coordinates: (0-7, 0-7)) of the piece that was initially clicked
        Square source = new Square ((xPixelToCoordinate((int) initialPosition.getX())),(yPixelToCoordinate((int) initialPosition.getY(),7)));
        //the destination square is the end location (in coordinates: (0-7, 0-7)) of the piece that was moved
        Square destination = new Square ((xPixelToCoordinate((int) finalPosition.getX())),(yPixelToCoordinate((int) finalPosition.getY(),7)));;
        
        Piece piece = null;
        //Get the piece that was initially clicked. If no such piece exists, then inform the user of that fact
        for(int i=0; i<Main.pieces.size(); i++) {
            if(Main.pieces.get(i).x == source.col && Main.pieces.get(i).y == source.row) {
                System.out.println("PIECE MATCH FOUND");
                piece = Main.pieces.get(i);
            }
        }
        if(piece != null) {
            Move move = new Move (piece, destination.col, destination.row);
            
            if(move.isValidMove()) {
                System.out.println("MOVE IS VALID");
                move.executeMove();
                this.drawBoard();
            }else {
                Main.infoBox("Invalid Move!", "Error");
            }
        }else {
            Main.infoBox("You didn't select a piece!", "Error");
        }
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