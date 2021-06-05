package ir.sharif.math.bp99_1.snake_and_ladder.model.transmitters;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Board;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Color;
import ir.sharif.math.bp99_1.snake_and_ladder.model.pieces.Piece;

public class Fatal extends Transmitter {
    public Fatal(Cell firstCell, Cell lastCell, int xBoard, int yBoard, Board myBoard) {
        super(firstCell, lastCell, xBoard, yBoard, myBoard);
    }

    @Override
    public void transmit(Piece piece) {
        piece.getPlayer().applyOnScore(-3);
        piece.setMyPrize(null);
        piece.setAlive(false);
        if (!piece.getColor().equals(Color.YELLOW)){
            piece.setSuperpower(false);
        }
        if (getLastCell().canEnter(piece)){
            piece.moveTo(this.getLastCell());
        }
    }
}
