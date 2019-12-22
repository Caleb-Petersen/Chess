/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 * @remark Evaluation Constants were chosen based on the author's knowledge of 
 * chess and personal opinions. They are intended to favour central pieces
 * @author Caleb
 */
public class EvaluationConstants {
    public static double WHITE_PAWN_LOCATION[][] = {
        {9, 9,  9,  9,  9,  9,  9,  9}, 
        {1.2,1.2,1.2,1.2,1.2,1.2,1.2,1.2}, 
        {1, 1,  1,  1,  1,  1,  1,  1}, 
        {1, 1,  1,  1.1,1.2,1.1,1,  1}, 
        {1, 1,  1.2,1.2,1.2,1,  1,  1}, 
        {1, 1,  1,  1,  1,  1,  1,  1}, 
        {1, 1,  1,  1,  1,  1,  1,  1}, 
        {0, 0,  0,  0,  0,  0,  0,  0}, 
    };
    public static double BLACK_PAWN_LOCATION[][] = {
        {0,0,0,0,0,0,0,0}, 
        {1,1,1,1,1,1,1,1}, 
        {1,1,1,1,1,1,1,1}, 
        {1,1,1.2,1.2,1.2,1,1,1}, 
        {1,1,1,1.1,1.2,1.1,1,1}, 
        {1,1,1,1,1,1,1,1}, 
        {1.2,1.2,1.2,1.2,1.2,1.2,1.2,1.2}, 
        {9,9,9,9,9,9,9,9}, 
    };
    
    public static double KNIGHT_LOCATION[][] = {
        {2,2,2,2,2,2,2,2}, 
        {2,2,2,2,2,2,2,2}, 
        {2.5,3,3,3,3,3,3,2.5}, 
        {2.8,3,3.2,3.3,3.3,3.2,3,2.8}, 
        {2.8,3,3.2,3.3,3.3,3.2,3,2.8}, 
        {2.5,3,3,3,3,3,3,2.5}, 
        {2,2,2,2,2,2,2,2}, 
        {2,2,2,2,2,2,2,2}, 
    };
    public static double BISHOP_LOCATION[][] = {
        {2,2,2,2,2,2,2,2}, 
        {2.5,2.5,2.5,2.5,2.5,2.5,2.5,2.5}, 
        {3,3,3,3,3,3,3,3}, 
        {3,3,3,3,3,3,3,3}, 
        {3,3,3,3,3,3,3,3}, 
        {3,3,3,3,3,3,3,3}, 
        {2.5,2.5,2.5,2.5,2.5,2.5,2.5,2.5}, 
        {2,2,2,2,2,2,2,2}, 
    };
    public static double ROOK_LOCATION[][] = {
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
        {5,5,5,5,5,5,5,5}, 
    };
    
    public static double QUEEN_LOCATION[][] = {
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
        {9,9,9,9,9,9,9,9}, 
    };
}
