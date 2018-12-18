/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caleb
 */
public class PieceTest {
    
    public PieceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of moveLegal method, of class Piece.
     */
    @Test
    public void testMoveLegal() {
        System.out.println("moveLegal");
        int x = 0;
        int y = 0;
        Piece instance = new Piece();
        boolean expResult = false;
        boolean result = instance.moveLegal(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class Piece.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Piece instance = new Piece();
        ImageIcon expResult = null;
        ImageIcon result = instance.getImage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePossibleDestinations method, of class Piece.
     */
    @Test
    public void testUpdatePossibleDestinations() {
        System.out.println("updatePossibleDestinations");
        Piece instance = new Piece();
        instance.updatePossibleDestinations();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pieceOnSquare method, of class Piece.
     */
    @Test
    public void testPieceOnSquare() {
        System.out.println("pieceOnSquare");
        Square square = null;
        Piece instance = new Piece();
        boolean expResult = false;
        boolean result = instance.pieceOnSquare(square);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareToDraw method, of class Piece.
     */
    @Test
    public void testPrepareToDraw() {
        System.out.println("prepareToDraw");
        Piece instance = new Piece();
        JLabel expResult = null;
        JLabel result = instance.prepareToDraw();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBlank method, of class Piece.
     */
    @Test
    public void testDrawBlank() {
        System.out.println("drawBlank");
        Piece instance = new Piece();
        JLabel expResult = null;
        JLabel result = instance.drawBlank();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
