/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.FEN;
import Chess.Piece;
import java.util.ArrayList;
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
public class FENTest {
    
    public FENTest() {
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
     * Test of fenToBoardPosition method, of class FEN.
     */
    @Test
    public void testFenToBoardPosition() {
        System.out.println("fenToBoardPosition");
        FEN instance = null;
        ArrayList<Piece> expResult = null;
        ArrayList<Piece> result = instance.fenToBoardPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of charToPiece method, of class FEN.
     */
    @Test
    public void testCharToPiece() {
        System.out.println("charToPiece");
        String character = "";
        FEN instance = null;
        Piece expResult = null;
        Piece result = instance.charToPiece(character);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
