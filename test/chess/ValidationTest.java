/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import Chess.Move;
import Chess.Piece;
import Chess.Validation;
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
public class ValidationTest {
    
    public ValidationTest() {
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
     * Test of isDiagonal method, of class Validation.
     */
    @Test
    public void testIsDiagonal() {
        System.out.println("isDiagonal");
        ArrayList<Piece> boardPosition = null;
        Move move = null;
        boolean expResult = false;
        boolean result = Validation.isDiagonal(boardPosition, move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isStraight method, of class Validation.
     */
    @Test
    public void testIsStraight() {
        System.out.println("isStraight");
        ArrayList<Piece> boardPosition = null;
        Move move = null;
        boolean expResult = false;
        boolean result = Validation.isStraight(boardPosition, move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isKnight method, of class Validation.
     */
    @Test
    public void testIsKnight() {
        System.out.println("isKnight");
        ArrayList<Piece> boardPosition = null;
        Move move = null;
        boolean expResult = false;
        boolean result = Validation.isKnight(boardPosition, move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isKing method, of class Validation.
     */
    @Test
    public void testIsKing() {
        System.out.println("isKing");
        ArrayList<Piece> boardPosition = null;
        Move move = null;
        boolean expResult = false;
        boolean result = Validation.isKing(boardPosition, move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPawn method, of class Validation.
     */
    @Test
    public void testIsPawn() {
        System.out.println("isPawn");
        ArrayList<Piece> boardPosition = null;
        Move move = null;
        boolean expResult = false;
        boolean result = Validation.isPawn(boardPosition, move);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}