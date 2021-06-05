package ir.sharif.math.bp99_1.snake_and_ladder.logic;

import ir.sharif.math.bp99_1.snake_and_ladder.model.Board;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Cell;
import ir.sharif.math.bp99_1.snake_and_ladder.model.Player;
import ir.sharif.math.bp99_1.snake_and_ladder.model.prizes.Prize;
import ir.sharif.math.bp99_1.snake_and_ladder.util.Config;

import java.io.*;
import java.util.Scanner;

public class ModelLoader {
    private final File boardFile, playersDirectory, archiveFile;


    /**
     * DO NOT CHANGE ANYTHING IN CONSTRUCTOR.
     */
    public ModelLoader() {
        boardFile = Config.getConfig("mainConfig").getProperty(File.class, "board");
        playersDirectory = Config.getConfig("mainConfig").getProperty(File.class, "playersDirectory");
        archiveFile = Config.getConfig("mainConfig").getProperty(File.class, "archive");
        if (!playersDirectory.exists()) playersDirectory.mkdirs();
    }


    /**
     * read file "boardFile" and craete a Board
     * <p>
     * you can use "BoardBuilder" class for this purpose.
     * <p>
     * pay attention add your codes in "try".
     */
    public Board loadBord() {
        try {
            Scanner beholder = new Scanner(boardFile);
            BoardBuilder carpenter = new BoardBuilder(beholder);
            Board boardy = carpenter.build();
            if (boardy != null)
                return boardy;
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find board file");
            System.exit(-2);
        }
        return null;
    }

    /**
     * load player.
     * if no such a player exist, create an account(file) for him/her.
     * <p>
     * you can use "savePlayer" method of this class for that purpose.
     * <p>
     * add your codes in "try" block .
     */
    public Player loadPlayer(String name, int playerNumber, String name2, Board board, String folder) {
        try {
            File playerFile = getPlayerFile(name, folder);
            if (playerFile != null){
                Scanner scanner = new Scanner(playerFile);
                int pnumber = scanner.nextInt();
                scanner.nextLine();
                int emtiaz = scanner.nextInt();
                Player myPlayer = new Player(name, emtiaz, 0, playerNumber);
                scanner.nextLine();
                myPlayer.setNobat(scanner.nextInt());
                scanner.nextLine();
                myPlayer.setMoveLeft(scanner.nextInt());
                scanner.nextLine();
                myPlayer.setMoveRight(scanner.nextInt());
                scanner.nextLine();
                boolean b = scanner.nextLine().equals("true");
                myPlayer.setDicePlayedThisTurn(b);
                boolean e = false;
                for (int i = 0; i < 4; i++){
                    Cell myCell = board.getCell(scanner.nextInt(), scanner.nextInt());
                    myPlayer.getPieces().get(i).setCurrentCell(myCell);
                    myPlayer.getPieces().get(i).setCurrentBourd(board);
                    myCell.setPiece(myPlayer.getPieces().get(i));
                    scanner.nextLine();
                    boolean c = scanner.nextLine().equals("true");
                    myPlayer.getPieces().get(i).setAlive(c);
                    boolean d = scanner.nextLine().equals("true");
                    myPlayer.getPieces().get(i).setSuperpower(d);
                    if (i == 3) e = scanner.nextLine().equals("true");
                }
                myPlayer.getPieces().get(3).setCarryingPrize(e);

                if (e){
                    int xxxx = scanner.nextInt();
                    int yyyy = scanner.nextInt();
                    int ss = scanner.nextInt();
                    int cc = scanner.nextInt();
                    int nn = scanner.nextInt();
                    Prize myPrizee = new Prize(board.getCell(xxxx, yyyy), ss, cc, nn);
                    myPlayer.getPieces().get(3).setMyPrize(myPrizee);
                    scanner.nextLine();
                }
                for (Cell myCell : board.getCells()){
                    myCell.setPrize(null);
                }
                board.getPrizes().clear();
                int prizeCount = scanner.nextInt();
                scanner.nextLine();
                for (int h = 0; h < prizeCount; h++){
                    int xxx = scanner.nextInt();
                    int yyy = scanner.nextInt();
                    int s = scanner.nextInt();
                    int c = scanner.nextInt();
                    int n = scanner.nextInt();
                    Prize myPrize = new Prize(board.getCell(xxx, yyy), s, c, n);
                    board.getCell(xxx, yyy).setPrize(myPrize);
                    board.getPrizes().add(myPrize);
                    scanner.nextLine();
                }
                int[] field = new int[7];
                for (int j = 1; j < 7; j++){
                    field[j] = scanner.nextInt();
                    scanner.nextLine();
                }
                myPlayer.getDice().setDiceField(field);
                return myPlayer;
            }else {
                Player newp = new Player(name, 0, 0, playerNumber);
                playerFile = new File(folder + "\\" + name  + ".txt");
                playerFile.createNewFile();
                return newp;
            }
        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("could not find player file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * if player does not have a file, create one.
     * <p>
     * else update his/her file.
     * <p>
     * add your codes in "try" block .
     */
    public void savePlayer(Player player1, Player player2, String folder, Board myBoard) {
        try {
            // add your codes in this part
            File file = getPlayerFile(player1.getName(), folder);
            if (file == null){
                file = new File(folder + "\\" + player1.getName()  + ".txt");
                file.createNewFile();
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.println(player1.getPlayerNumber());
            printStream.println(player1.getScore());
            printStream.println(player1.getNobat());
            printStream.println(player1.getMoveLeft());
            printStream.println(player1.getMoveRight());
            printStream.println(player1.isDicePlayedThisTurn());
            for (int i = 0; i < 4; i++){
                printStream.println(player1.getPieces().get(i).getCurrentCell().getX() + " " + player1.getPieces().get(i).getCurrentCell().getY());
                printStream.println(player1.getPieces().get(i).getAlive());
                printStream.println(player1.getPieces().get(i).getSuperpower());
            }
            printStream.println(player1.getPieces().get(3).getCarryingPrize());
            if (player1.getPieces().get(3).getCarryingPrize()){
                printStream.println(player1.getPieces().get(3).getMyPrize().getCell().getX() + " " + player1.getPieces().get(3).getMyPrize().getCell().getY() + " " + player1.getPieces().get(3).getMyPrize().getPoint() + " " + player1.getPieces().get(3).getMyPrize().getChance() + " " + player1.getPieces().get(3).getMyPrize().getDiceNumber());
            }
            printStream.println(myBoard.getPrizes().size());
            for (Prize myPrize : myBoard.getPrizes()){
                printStream.println(myPrize.getCell().getX() + " " + myPrize.getCell().getY() + " " + myPrize.getPoint() + " " + myPrize.getChance() + " " + myPrize.getDiceNumber());
            }
            for (int j = 1; j < 7; j++){
                printStream.println(player1.getDice().getDiceField()[j]);
            }
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find player file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadPFPath(String player1, String player2) {
        // add your codes in this part
        File file = getPlayerFolder(player1, player2);
        if (file == null){
            file = new File("playersDirectory\\" + player1  + "&" + player2);
            file.mkdirs();
        }
        return "playersDirectory\\" + player1  + "&" + player2;
    }

    /**
     * give you a name (player name), search for its file.
     * return the file if exist.
     * return null if not.
     */
    private File getPlayerFile(String player1, String folder) {
        File pfile = new File(folder + "\\" + player1  + ".txt");
        if (pfile.exists()) return pfile;
        else return null;
    }

    private File getPlayerFolder(String name1, String name2) {
        File pfile = new File("playersDirectory\\" + name1  + "&" + name2);
        if (pfile.exists()) return pfile;
        else return null;
    }

    /**
     * at the end of the game save game details
     */
    public void archive(Player player1, Player player2, String folder) {
        try {
            // add your codes in this part
            File file = getPlayerFile(player1.getName(), folder);
            file.delete();
            File filee = getPlayerFile(player2.getName(), folder);
            filee.delete();
            PrintStream printStream = new PrintStream(new FileOutputStream(archiveFile, true));
            printStream.println(player1.getName());
            printStream.println(player1.getScore());
            printStream.println(player2.getName());
            printStream.println(player2.getScore());
            if (player1.getScore() > player2.getScore()){
                printStream.println(player1.getName());
            } else if (player1.getScore() < player2.getScore()){
                printStream.println(player2.getName());
            } else printStream.println("Draw");
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //public static void main(String[] args) {
    //    ModelLoader bruh = new ModelLoader();
    //    if (bruh.loadBord() == null) System.out.println("null");
    //    else System.out.println("ok");
    //}

}
