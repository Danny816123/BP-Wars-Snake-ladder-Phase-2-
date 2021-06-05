package ir.sharif.math.bp99_1.snake_and_ladder.model.transmitters;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Board;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.pieces.Piece;

public class Transmitter {
    private final Cell firstCell, lastCell;
    private final int xBoard;
    private final int yBoard;
    private Board myBoard;

    public Transmitter(Cell firstCell, Cell lastCell, int xBoard, int yBoard, Board myBoard) {
        this.firstCell = firstCell;
        this.lastCell = lastCell;
        this.xBoard = xBoard;
        this.yBoard = yBoard;
        this.myBoard = myBoard;
    }

    public Cell getFirstCell() {
        return firstCell;
    }

    public Cell getLastCell() {
        return lastCell;
    }

    public int getXBoard() {
        return xBoard;
    }

    public int getYBoard() {
        return yBoard;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    /**
     * transmit piece to lastCell
     */
    public void transmit(Piece piece) {
        piece.getPlayer().applyOnScore(-3);
        piece.setMyPrize(null);
        if (getLastCell().canEnter(piece)){
            piece.moveTo(lastCell);
        }
    }

}
