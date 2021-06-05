package ir.sharif.math.bp99_1.snake_and_ladder.model.pieces;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Color;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Player;
import ir.sharif.math.bp99_1.snake_and_ladder.model.pieces.Piece;

public class Sniper extends Piece {
    public Sniper(Player player, Color blue) {
        super(player, blue);
    }

    @Override
    public void operate() {
        if(this.getSuperpower() && this.getAlive()){
            Cell cell1 = this.getCurrentBourd().getCell(this.getCurrentCell().getX() + 1, this.getCurrentCell().getY());
            Cell cell2 = this.getCurrentBourd().getCell(this.getCurrentCell().getX() - 1, this.getCurrentCell().getY());
            Cell cell3 = this.getCurrentBourd().getCell(this.getCurrentCell().getX(), this.getCurrentCell().getY() + 1);
            Cell cell4 = this.getCurrentBourd().getCell(this.getCurrentCell().getX(), this.getCurrentCell().getY() - 1);
            if (this.getCurrentBourd().getCells().contains(cell1)){
                if (cell1.getPiece() != null){
                    if (cell1.getPiece().getPlayer().getPlayerNumber() != this.getPlayer().getPlayerNumber() && cell1.getPiece().getAlive() && !cell1.getPiece().getColor().equals(Color.RED)){
                        cell1.getPiece().setAlive(false);
                        if (!cell1.getPiece().getColor().equals(Color.YELLOW)){
                            cell1.getPiece().setSuperpower(false);
                        }
                        this.setSuperpower(false);
                    }
                }
            }if (this.getCurrentBourd().getCells().contains(cell2)){
                if (cell2.getPiece() != null){
                    if (cell2.getPiece().getPlayer().getPlayerNumber() != this.getPlayer().getPlayerNumber() && cell2.getPiece().getAlive() && !cell2.getPiece().getColor().equals(Color.RED)){
                        cell2.getPiece().setAlive(false);
                        if (!cell2.getPiece().getColor().equals(Color.YELLOW)){
                            cell2.getPiece().setSuperpower(false);
                        }
                        this.setSuperpower(false);
                    }
                }
            }if (this.getCurrentBourd().getCells().contains(cell3)){
                if (cell3.getPiece() != null){
                    if (cell3.getPiece().getPlayer().getPlayerNumber() != this.getPlayer().getPlayerNumber() && cell3.getPiece().getAlive() && !cell3.getPiece().getColor().equals(Color.RED)){
                        cell3.getPiece().setAlive(false);
                        if (!cell3.getPiece().getColor().equals(Color.YELLOW)){
                            cell3.getPiece().setSuperpower(false);
                        }
                        this.setSuperpower(false);
                    }
                }
            }if (this.getCurrentBourd().getCells().contains(cell4)){
                if (cell4.getPiece() != null){
                    if (cell4.getPiece().getPlayer().getPlayerNumber() != this.getPlayer().getPlayerNumber() && cell4.getPiece().getAlive() && !cell4.getPiece().getColor().equals(Color.RED)){
                        cell4.getPiece().setAlive(false);
                        if (!cell4.getPiece().getColor().equals(Color.YELLOW)){
                            cell4.getPiece().setSuperpower(false);
                        }
                        this.setSuperpower(false);
                    }
                }
            }
        }
    }
}
