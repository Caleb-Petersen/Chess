/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

/**
 *
 * @author Caleb
 */
public class Constants {
    public static String ORIGINAL_POSITION_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public static String TEST_FEN = "1r5r/ppk3pp/8/3QP3/8/1B6/1P3PPP/5K1R";
    public static int KING_VALUE = 1000;
    public static int QUEEN_VALUE = 9;
    public static int ROOK_VALUE = 5;
    public static int BISHOP_VALUE = 3;
    public static int KNIGHT_VALUE = 3;
    public static int PAWN_VALUE = 1;
    
    public static int BOTTOM_EVALUATION = -10000; //Constant for a very low evaluation
    public static int TOP_EVALUATION = 10000; //Constant for a very high evaluation
    
    public static int SEARCH_DEPTH = 3;
}
