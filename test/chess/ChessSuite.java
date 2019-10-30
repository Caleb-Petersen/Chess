/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import chess.Images.ImagesSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Caleb
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SquareTest.class, ValidationTest.class, MainTest.class, MoveTest.class, ImagesSuite.class, PieceTest.class, FENTest.class})
public class ChessSuite {
    
}
