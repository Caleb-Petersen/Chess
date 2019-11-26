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
public class Game {
    public Position currentPosition;
    private boolean engineMoving;
    private boolean pieceSelectedForMove;
    private Square initialSquare;
    private ChessUI chessUI;
    
    
    public Game() {
        resetGamePosition();
        this.engineMoving = false;
        this.pieceSelectedForMove = false;
        
        chessUI = new ChessUI(this);
        updateView();
    }
    
    public void updateView() {
        chessUI.updateView(this.currentPosition);
    }
    
    public void resetGamePosition() {
        FEN initialPosition = new FEN(Constants.ORIGINAL_POSITION_FEN);
        this.currentPosition =  new Position(initialPosition.fenToBoardPosition());
        
        //Hack to make the search graph thing the last piece to have moved is white
        Piece fakePiece = new Piece(Piece.TYPE.KING, Piece.COLOUR.WHITE,0,0 );
        Move fakeMove = new Move(fakePiece, new Square(0,1));
        this.currentPosition.setLastMove(fakeMove);
    }
    
    public void squareSelected(Square selectedSquare) {
        if(!engineMoving) {
            if(!pieceSelectedForMove) {
                this.initialSquare = selectedSquare;
                this.pieceSelectedForMove = true;
            }
            else {
                //a piece has already been selected so this attempts to move
                Piece piece = null;
                //Get the piece that was initially clicked. If no such piece exists, then inform the user of that fact
                for(int i=0; i<currentPosition.boardPosition.size(); i++) {
                    if(currentPosition.boardPosition.get(i).location.x == initialSquare.x && currentPosition.boardPosition.get(i).location.y == initialSquare.y) {
                        piece = currentPosition.boardPosition.get(i);
                    }
                }
                if(piece != null) {
                    Move move = new Move(piece, selectedSquare);

                    if(move.isValidMove(currentPosition)) {
                        System.out.println("size before: " + currentPosition.boardPosition.size());
                        move.executeMove(currentPosition);
                        System.out.println("size after: " + currentPosition.boardPosition.size());
                        updateView();
                        //Move the computer
                        engineMoving = true;
                        SearchGraph.findBestMove(currentPosition, Piece.COLOUR.BLACK).executeMove(currentPosition);
                        updateView();
                        engineMoving = false;
                    }else {
                        Main.infoBox("Invalid Move!", "Error");
                    }
                }else {
                    Main.infoBox("You didn't select a piece!", "Error");
                }
                this.pieceSelectedForMove = false;
            }
        }
    }
}
