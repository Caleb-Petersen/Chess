/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

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
public class MoveTest {
    
    public MoveTest() {
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
     * Test of moveIsEnpassant method, of class Move.
     */
    @Test
    public void testMoveIsEnpassant() {
        System.out.println("moveIsEnpassant");
        Move instance = null;
        boolean expResult = false;
        boolean result = instance.moveIsEnpassant();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCastling method, of class Move.
     */
    @Test
    public void testMoveIsCastling() {
        System.out.println("moveIsCastling");
        Move instance = null;
        boolean expResult = false;
        boolean result = instance.isCastling();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidMove method, of class Move.
     */
    @Test
    public void testIsValidMove() {
        System.out.println("isValidMove");
        Move instance = null;
        boolean expResult = false;
        boolean result = instance.isValidMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeMove method, of class Move.
     */
    @Test
    public void testExecuteMove() {
        System.out.println("executeMove");
        Move instance = null;
        instance.executeMove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
