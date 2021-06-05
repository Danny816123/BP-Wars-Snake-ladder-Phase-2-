package ir.sharif.math.bp99_1.snake_and_ladder.model.pieces;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Color;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Player;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Wall;
import ir.sharif.math.bp99_1.snake_and_ladder.model.pieces.Piece;
import ir.sharif.math.bp99_1.snake_and_ladder.model.prizes.Prize;

public class Robber extends Piece {
    public Robber(Player player, Color yellow) {
        super(player, yellow);
        this.setSuperpower(true);
    }

    @Override
    public boolean isValidMove(Cell destination, int diceNumber) {
        if (destination.getX() - this.getCurrentCell().getX() == diceNumber && destination.getY() == this.getCurrentCell().getY() && destination.canEnter(this)) {
            return true;
        } else if (- destination.getX() + this.getCurrentCell().getX() == diceNumber && destination.getY() == this.getCurrentCell().getY() && destination.canEnter(this)) {
            return true;
        } else if (destination.getY() - this.getCurrentCell().getY() == diceNumber && destination.getX() == this.getCurrentCell().getX() && destination.canEnter(this)) {
            return true;
        } else if (- destination.getY() + this.getCurrentCell().getY() == diceNumber && destination.getX() == this.getCurrentCell().getX() && destination.canEnter(this)) {
            return true;
        }
        else return false;
    }
    @Override
    public void operate() {
        if(this.getAlive()){
            if (this.getMyPrize() == null){
                if (this.getCurrentCell().getPrize() != null) {
                    Prize myPrize = this.getCurrentCell().getPrize();
                    this.setMyPrize(myPrize);
                    this.getCurrentBourd().getPrizes().remove(myPrize);
                    this.getCurrentCell().setPrize(null);
                }
            }else{
                this.getCurrentCell().setPrize(this.getMyPrize());
                this.getCurrentBourd().getPrizes().add(this.getMyPrize());
                this.setMyPrize(null);
                this.getCurrentCell().getPrize().setCell(this.getCurrentCell());
            }
        }
    }
}
