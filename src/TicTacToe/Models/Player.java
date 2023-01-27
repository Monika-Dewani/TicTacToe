package TicTacToe.Models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType playerType;
    public Player(String name, char aChar, PlayerType type){
        this.name=name;
        this.symbol=aChar;
        this.playerType=type;
    }

    public char getSymbol(){
        return symbol;
    }

    public void setSymbol(char symbol){
        this.symbol=symbol;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public PlayerType getPlayerType(){
        return playerType;
    }

    public void setPlayerType(PlayerType playerType){
        this.playerType=playerType;
    }

    public Move decideMove(Board board){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the row staring from 0");
        int row= sc.nextInt();

        System.out.println("Please enter the column staring from 0");
        int col= sc.nextInt();

        return new Move(this,new Cell(row,col));
    }
}
