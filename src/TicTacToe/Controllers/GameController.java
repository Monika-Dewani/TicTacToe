package TicTacToe.Controllers;

import TicTacToe.Models.Game;
import TicTacToe.Models.GameStatus;
import TicTacToe.Models.Player;

import java.util.List;


// Controllers are not generally required in games
// we can directly make the main class talk to the
// Game class


//In Interview for interactive application like game,
//we can skip controller and services(except interface of the services),
// directly communicate with the model i.e. create the undo and nextMove, and displayBoard method in Game class itself;
// SO main class directly talk  to the game Class

public class GameController {


    public void undo(Game game){
        game.undo();
    }

    // Return Type is game cos we want the game after creating a game
    //after the client provide dimensions and list of player
    // Here we need to use builder to create a game;
    public Game createGame(int dimension, List<Player> players) {
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
    // Controller is nothing but calling methods on behalf of client then why
    //shoudld we have the controller?
    // Because we dont want to expose all our methods and models to the client
    //So any main class will only call these GameController methods
    // Till the time all these moethods are working as expected we can change any
    // any code, any class in the internal code

    // this controller layer we are allowing our client to talk to it only
    // we are hiding all the functionalities from the client
    //this enables us to change the functionality easily in the future





    // Client continously needs to check the status of the game so
    // that the comp can change the game state and end the game
    // or delare the winner

    public GameStatus getGameStatus(Game game){
    return game.getGameStatus();
    }

    public void displayBoard(Game game){
        game.displayBoard();

    }

    public void executeNextMove(Game game) {
         game.makeNextMove();
    }


}
