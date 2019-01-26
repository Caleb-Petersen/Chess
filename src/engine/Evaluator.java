/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import Chess.*;
/**
 *
 * @author Caleb
 */
public class Evaluator {
    public Position position;
    public double evaluation;
    public Move bestMove;
    
    public Evaluator(Position p) {
        this.position = p;
        this.evaluation = 0.0;
        this.bestMove = null;
    }
}
