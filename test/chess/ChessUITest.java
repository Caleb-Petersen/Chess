/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.ChessUI;
import java.awt.event.MouseEvent;
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
public class ChessUITest {
    
    public ChessUITest() {
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
     * Test of xCoordinateToPixels method, of class ChessUI.
     */
    @Test
    public void testXCoordinateToPixels() {
        System.out.println("xCoordinateToPixels");
        int coordinate = 0;
        int expResult = 0;
        int result = ChessUI.xCoordinateToPixels(coordinate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of xPixelToCoordinate method, of class ChessUI.
     */
    @Test
    public void testXPixelToCoordinate() {
        System.out.println("xPixelToCoordinate");
        int pixel = 0;
        int expResult = 0;
        int result = ChessUI.xPixelToCoordinate(pixel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of yCoordinateToPixels method, of class ChessUI.
     */
    @Test
    public void testYCoordinateToPixels() {
        System.out.println("yCoordinateToPixels");
        int coordinate = 0;
        int constant = 0;
        int expResult = 0;
        int result = ChessUI.yCoordinateToPixels(coordinate, constant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of yPixelToCoordinate method, of class ChessUI.
     */
    @Test
    public void testYPixelToCoordinate() {
        System.out.println("yPixelToCoordinate");
        int pixel = 0;
        int constant = 0;
        int expResult = 0;
        int result = ChessUI.yPixelToCoordinate(pixel, constant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBoard method, of class ChessUI.
     */
    @Test
    public void testDrawBoard() {
        System.out.println("drawBoard");
        ChessUI instance = new ChessUI();
        instance.drawBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseClicked method, of class ChessUI.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent e = null;
        ChessUI instance = new ChessUI();
        instance.mouseClicked(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class ChessUI.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent e = null;
        ChessUI instance = new ChessUI();
        instance.mousePressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class ChessUI.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent e = null;
        ChessUI instance = new ChessUI();
        instance.mouseReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseEntered method, of class ChessUI.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent e = null;
        ChessUI instance = new ChessUI();
        instance.mouseEntered(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseExited method, of class ChessUI.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent e = null;
        ChessUI instance = new ChessUI();
        instance.mouseExited(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
