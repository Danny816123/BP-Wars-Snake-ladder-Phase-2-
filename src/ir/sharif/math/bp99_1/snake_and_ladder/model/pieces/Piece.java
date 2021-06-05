package ir.sharif.math.bp99_1.snake_and_ladder.model.pieces;

import ir.sharif.math.bp99_1.snake_and_ladder.model.*;
import ir.sharif.math.bp99_1.snake_and_ladder.model.prizes.Prize;

public class Piece {
    private Cell currentCell;
    private final Color color;
    private final Player player;
    private boolean isSelected;
    private Board currentBourd;
    private boolean alive;
    private boolean superpower;
    private boolean explode;
    private boolean carryingPrize;
    private Prize myPrize;

    public Piece(Player player, Color color) {
        this.color = color;
        this.player = player;
        this.alive = true;
        this.superpower = false;
        this.explode = false;
        this.carryingPrize = false;
        this.myPrize = null;
    }

    public boolean getAlive() {
        return alive;
    }

    public boolean getSuperpower() {
        return superpower;
    }

    public Player getPlayer() {
        return player;
    }

    public Color getColor() {
        return color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Board getCurrentBourd() {
        return currentBourd;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setSuperpower(boolean superpower) {
        this.superpower = superpower;
    }

    public void setCurrentBourd(Board currentBourd) {
        this.currentBourd = currentBourd;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public boolean getExplode() {
        return this.explode;
    }

    public void setExplode(boolean a) {
        this.explode = a;
    }

    public boolean getCarryingPrize() {
        return this.carryingPrize;
    }

    public void setCarryingPrize(boolean a) {
        this.carryingPrize = a;
    }

    public Prize getMyPrize() {
        return this.myPrize;
    }

    public void setMyPrize(Prize a) {
        this.myPrize = a;
    }


    /**
     * @return "true" if your movement is valid  , else return " false"
     * <p>
     * In this method, you should check if the movement is valid of not.
     * <p>
     * You can use some methods ( they are recommended )
     * <p>
     * 1) "canEnter" method in class "Cell"
     * <p>
     * if your movement is valid, return "true" , else return " false"
     */
    public boolean isValidMove(Cell destination, int diceNumber) {
        if (destination.getX() - currentCell.getX() == diceNumber && destination.getY() == currentCell.getY() && destination.canEnter(this)) {
            waloop : for (int i = 0; i < diceNumber; i++){
                for (Wall wall : currentBourd.getWalls()){
                    if (wall.getCell1().equals(currentBourd.getCell(currentCell.getX() + i, currentCell.getY())) && wall.getCell2().equals(currentBourd.getCell(currentCell.getX() + i + 1, currentCell.getY()))){
                        return false;
                    }
                }
            }
            return true;
        } else if (- destination.getX() + currentCell.getX() == diceNumber && destination.getY() == currentCell.getY() && destination.canEnter(this)) {
            waloop : for (int i = 0; i < diceNumber; i++){
                for (Wall wall : currentBourd.getWalls()){
                    if (wall.getCell2().equals(currentBourd.getCell(currentCell.getX() - i, currentCell.getY())) && wall.getCell1().equals(currentBourd.getCell(currentCell.getX() - i - 1, currentCell.getY()))){
                        return false;
                    }
                }
            }
            return true;
        } else if (destination.getY() - currentCell.getY() == diceNumber && destination.getX() == currentCell.getX() && destination.canEnter(this)) {
            waloop : for (int i = 0; i < diceNumber; i++){
                for (Wall wall : currentBourd.getWalls()){
                    if (wall.getCell1().equals(currentBourd.getCell(currentCell.getX(), currentCell.getY() + i)) && wall.getCell2().equals(currentBourd.getCell(currentCell.getX(), currentCell.getY() + i + 1))){
                        return false;
                    }
                }
            }
            return true;
        } else if (- destination.getY() + currentCell.getY() == diceNumber && destination.getX() == currentCell.getX() && destination.canEnter(this)) {
            waloop : for (int i = 0; i < diceNumber; i++){
                for (Wall wall : currentBourd.getWalls()){
                    if (wall.getCell2().equals(currentBourd.getCell(currentCell.getX(), currentCell.getY() - i)) && wall.getCell1().equals(currentBourd.getCell(currentCell.getX(), currentCell.getY() - i - 1))){
                        return false;
                    }
                }
            }
            return true;
        }
        else return false;
    }

    public void operate() { }

    /**
     * @param destination move selected piece from "currentCell" to "destination"
     */
    public void moveTo(Cell destination) {
        if (destination.getColor().equals(color)){
            player.applyOnScore(4);
        }
        currentCell.setPiece(null);
        destination.setPiece(this);
        setCurrentCell(destination);
        if (destination.getPrize() != null && this.myPrize == null){
            destination.getPrize().using(this);
        }
        if (destination.getTransmitter() != null){
            if (destination.getTransmitter().getFirstCell().equals(destination)){
                destination.getTransmitter().transmit(this);
            }
        }


    }
}