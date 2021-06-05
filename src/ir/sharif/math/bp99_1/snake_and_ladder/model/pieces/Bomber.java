package ir.sharif.math.bp99_1.snake_and_ladder.model.pieces;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Color;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Player;

public class Bomber extends Piece {
    public Bomber(Player player, Color green) {
        super(player, green);
    }


    @Override
    public void operate() {
        if(this.getSuperpower() && this.getExplode()){
            for (int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    Cell myCell = this.getCurrentBourd().getCell(this.getCurrentCell().getX() + i, this.getCurrentCell().getY() + j);
                    if (this.getCurrentBourd().getCells().contains(myCell)){
                        myCell.setPrize(null);
                        if (myCell.getPiece() != null){
                            if (myCell.getPiece().getAlive() && !myCell.getPiece().getColor().equals(Color.RED)){
                                myCell.getPiece().setAlive(false);
                                if (!myCell.getPiece().getColor().equals(Color.YELLOW)){
                                    myCell.getPiece().setSuperpower(false);
                                }
                            }
                        }
                    }
                }
            }
            this.getCurrentCell().setColor(Color.BLACK);
        }
    }
}
