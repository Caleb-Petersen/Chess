/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;


/**
 *
 * @author Caleb
 
public class Main {
    public static void main(String[] args) {
        ImageFrame frame = new ImageFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}*/
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Main {
    public static ArrayList<Piece> pieces = new ArrayList<Piece>();
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                initializeOriginalPosition();
                
                ChessUI frame = new ChessUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    
    public static void infoBox(String infoMessage, String titleBar) {
        /*This function creats a diolog box giving the user some sort of information.
        It make the communication with the user a little more prominent.
        */
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    ///NOTE:::::::::This will be removed once the PGN class is initialized, but does work for the present
    public static void initializeOriginalPosition() {
        /* The initialize original function initializes the position to the 
        starting position, and the information (for the array) is loaded from an 
        xml file. 
        */
        try {
            //load the initial position file
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("C:/Users/Caleb/Documents/NetbeansProjects/Chess/src/chess/initial position.xml"));
            doc.getDocumentElement().normalize();
            /*the initial position file is divided using the class "square", with
            each square having a y, column, pieceType, pieceColour, squareColour,
            and a hasMoved boolean. 
            */
            NodeList listOfSquares = doc.getElementsByTagName("square");
            int totalSquares = listOfSquares.getLength();
            
            /* this loops through all of the squares, and adds the information from
            each square to the main boardPosition array. 
            */
            for (int s = 0; s < listOfSquares.getLength(); s++) {
                Node firstSquareNode = listOfSquares.item(s);
                if (firstSquareNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstSquareElement = (Element) firstSquareNode;
                    NodeList Row = firstSquareElement.getElementsByTagName("row");
                    Element rowElement = (Element) Row.item(0);
                    NodeList textRowList = rowElement.getChildNodes();
                    int rows = Integer.parseInt(((Node) textRowList.item(0)).getNodeValue().trim());

                    NodeList column = firstSquareElement.getElementsByTagName("col");
                    Element columnElement = (Element) column.item(0);
                    NodeList textColumnList = columnElement.getChildNodes();
                    int columns = Integer.parseInt(((Node) textColumnList.item(0)).getNodeValue().trim());

                    NodeList PieceType = firstSquareElement.getElementsByTagName("pieceType");
                    Element pieceTypeElement = (Element) PieceType.item(0);
                    NodeList textPieceTypeList = pieceTypeElement.getChildNodes();
                    String pieceType = (((Node) textPieceTypeList.item(0)).getNodeValue().trim());

                    NodeList PieceColour = firstSquareElement.getElementsByTagName("pieceColour");
                    Element pieceColourElement = (Element) PieceColour.item(0);
                    NodeList textPieceColourList = pieceColourElement.getChildNodes();
                    String pieceColour = (((Node) textPieceColourList.item(0)).getNodeValue().trim());

                    NodeList SquareColour = firstSquareElement.getElementsByTagName("squareColour");
                    Element squareColourElement = (Element) SquareColour.item(0);
                    NodeList textSquareColourList = squareColourElement.getChildNodes();
                    String squareColour = (((Node) textSquareColourList.item(0)).getNodeValue().trim());
                    NodeList HasMoved = firstSquareElement.getElementsByTagName("hasMoved");
                    Element HasMovedElement = (Element) HasMoved.item(0);
                    NodeList textHasMovedList = HasMovedElement.getChildNodes();
                    boolean hasMoved = Boolean.parseBoolean(((Node) textHasMovedList.item(0)).getNodeValue().trim());
                    
                    //Add the information retrieved from the xml file to the array
                    
                   // System.out.println(System.getProperty("java.classpath"));
                   
                    if(!pieceType.equals("null")) {
                        Piece piece = new Piece();
                        piece.y = rows-1;
                        piece.x = columns-1;
                        piece.pieceType = pieceType;
                        piece.pieceColour = pieceColour; 
                        piece.hasMoved = hasMoved;
                        piece.updatePossibleDestinations();
//                        ImageIcon image = null;
//                        try {
//                            
//                            //image = ImageIO.read(new File("C:/Users/Caleb/Documents/NetbeansProjects/Chess/src/chess/Images/whitekingblack.jpg"));
//                        }
//                        catch (IOException e){
//                            e.printStackTrace();
//                        }
                        
                        piece.pieceImage = piece.getImage();//getImages(piece.pieceColour + "_" + piece.pieceType);
                        pieces.add(piece);
                        //System.out.println(piece.pieceColour + " " + piece.pieceType + " on (" + piece.x + " " + piece.y + ")" );
                    }
                }
            }
            /*once the array has the correct information, append that information
            to the board graphically using the reset board function.
            */
        } catch (SAXParseException err) {

        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



