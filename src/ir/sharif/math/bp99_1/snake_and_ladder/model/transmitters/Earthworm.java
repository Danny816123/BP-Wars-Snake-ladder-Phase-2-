package ir.sharif.math.bp99_1.snake_and_ladder.model.transmitters;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Board;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.pieces.Piece;

import java.util.Random;

public class Earthworm extends Transmitter{
    public Earthworm(Cell firstCell, Cell lastCell, int xBoard, int yBoard, Board myBoard) {
        super(firstCell, lastCell, xBoard, yBoard, myBoard);
    }

    Random rand = new Random();
    int x = rand.nextInt(getXBoard()) + 1;
    int y = rand.nextInt(getYBoard()) + 1;
    Cell randomCell = this.getMyBoard().getCell(x, y);

    @Override
    public void transmit(Piece piece) {
        piece.getPlayer().applyOnScore(-3);
        piece.setMyPrize(null);
        if (randomCell.canEnter(piece)){
            piece.moveTo(randomCell);
        }
    }
}
