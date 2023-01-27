package TicTacToe;

import TicTacToe.Controllers.GameController;
import TicTacToe.Models.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// THis class is more like a client class
// it has to interacat with Game class i.e. models
// through controllers

public class TicTacToeGame {
    // This is the main Class
    public static void main(String[] args) {
       // in this main method we will have the complete logic to run the game
        //It will take inputs i.e. parameters initially to create Game class and then start the game.
        //Game is the main class for this game so here it is important to
        // take parameters for it so that we can create it first and then it should keep on running
        // inputs will be: dimension and players

        GameController gameController= new GameController();
        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the Game Dimension");
        int dimension= sc.nextInt();
        int toIterate=dimension-1;
        List<Player>  players= new ArrayList<>();
        System.out.println("Will there be any Bot in you Game? y/n");
        String isBot= sc.next();

        if(isBot.equals("y")){
            toIterate=toIterate-1;
            //toIterate=dimension-2;  this command will also work;
        }

        for(int i=0;i<toIterate;++i){
            System.out.println("What is the name of a Player? "+i);
            String playerName= sc.next();

            System.out.println("What will be the symbol of the player? "+i);
            String playerSymbol= sc.next();

            players.add(new Player(playerName,playerSymbol.charAt(0), PlayerType.HUMAN));
        }

        //System.out.println("Will there be any Bot in the game? y/n");



        if(isBot.equals("y")){
            System.out.println("What will be the symbol of a BOT?");
            String playerSymbol= sc.next();

            players.add(new Bot(isBot,playerSymbol.charAt(0),BotDifficultyLevel.EASY));
        }


        Game game = gameController.createGame(dimension,players );
        //gameController.createGame(dimension,);

        //After creating a game object our main TicTocToeGame class
        // should keep on running till it ends or someone wins or game os draw
        // we will use while loop

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            // we want to make undo and move work and print the same for player

            System.out.println("This is the current Board");

            //Print the board
            gameController.displayBoard(game);


            System.out.println("Does anyone wants to undo the move? y/n");

            String input=sc.next();
            if(input.equals("y")){
                // undo()
                gameController.undo(game);
            }
            else{
                // ask for the move
                //System.out.println("This is [current player]'s move. Please enter the cell coordinates as (row,col) starting from 1");
              gameController.executeNextMove(game);
            }

        }

        System.out.println("Game has Ended. Result:");

        if(!game.getGameStatus().equals(GameStatus.DRAW)){
            System.out.println("Winner is:");
        }
//        if(game.getGameStatus().equals(GameStatus.ENDED)){
//            System.out.println("Winner is:");
//        }

    }
}
