package TicTacToe.Models;

import TicTacToe.Exceptions.InvalidGameConstructionParametersException;
import TicTacToe.Strategies.gameWinningStrategy.GameWinningStrategy;
import TicTacToe.Strategies.gameWinningStrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    public Player getWinner() {
        return winner;
    }

    private Player winner;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private GameWinningStrategy gameWinningStrategy;

    public Game(){

    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public Board getBoard(){
        return board;
    }

    public void undo(){

    }

    public void displayBoard(){
        this.board.display();
    }

    public void setBoard(Board board){
        this.board=board;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void setPlayers(List<Player> players){
        this.players=players;
    }

    public List<Move> getMoves(){
        return moves;
    }

    public void setMove(List<Move> moves){
        this.moves=moves;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus){
        this.gameStatus=gameStatus;
    }

    public int getNextMoveIndex(){
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex){
        this.nextPlayerIndex=nextPlayerIndex;
    }


    public void makeNextMove(){
        // why do we dont want our player to make the move but game class get the move first that the player wants
        // we dont want our player to directly make the move.Otherwise player can take move at any time
            // we will first validate the move and then will make a move

            Player toMovePlayer= players.get(nextPlayerIndex);
        System.out.println("This is " +players.get(nextPlayerIndex).getName()+"'s turn");

            Move move=toMovePlayer.decideMove(this.board);

        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        System.out.println("Move happened at : "+
                row+", "+ col+".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove= new Move(
                players.get(nextPlayerIndex),
                board.getBoard().get(row).get(col)
        );

        this.moves.add(finalMove);

        if(gameWinningStrategy.checkWinner(board,players.get(nextPlayerIndex),finalMove.getCell())){
            gameStatus=GameStatus.ENDED;
            winner=players.get(nextPlayerIndex);
        }
        nextPlayerIndex +=1;
        nextPlayerIndex %= players.size();
    }

    public static class Builder{
        //in builder DP we learnt
        //and put all the class attributes in
        //Builder but that is not the actual scene
        // we write all those attributes which we want our
        //client to pass in the project(here in game)
        // we dont want our client or user to pass
        // the actual board  so comment it
        // we just want dimensions from client so include it
        // we want the listOf player from client so will keep it
        // we dont want the list of moves from client thats the internal part
        // and will happen when the game will run so remove moves
        // we dont eeven want gameStatus from client not even nextMoveIndex


        //So builder will have only those attribuites that are needed to create the
        // object of Parent Class;



        //private Board board;
        //private List<Move> moves;
        //private GameStatus gameStatus;
        //private int nextMoveIndex;

        private int dimension;

        private List<Player> players;

        // getters are not required;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this; // in builder we need chaining to pass attributes
                            // so returning Builder itself in setters;
        }

        //before creating build() method we need to do the validation
        // this is the another reason for using Builder DP

        private boolean valid() throws InvalidGameConstructionParametersException {
            if(this.dimension<3){
                throw new InvalidGameConstructionParametersException("Dimension CanNot be less than 3");
            }

            if(players.size()!=this.dimension-1){
                throw new InvalidGameConstructionParametersException("Number Of Players Cannot be less than Dimension-1");
            }

            // Validate not 2 people will have same character

            //validate the number of bots her it should be 1;
            return true;
        }

        public Game build() throws InvalidGameConstructionParametersException {
//            if(!valid()){
//                throw new InvalidGameConstructionParametersException("Something is wrong with your parameters");
//            }

            try{
                valid();
            }
            catch(Exception e){
                throw new InvalidGameConstructionParametersException(e.getMessage());
            }


           //After all validation and initialization from the client
            // we can set the values of all the attributes of game class object here
            // or in constructor of game class as well. Both ar =e fine
            // and then return the object that we will be creating as given below;

            Game game= new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimension));
            game.setMove(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            return game;


           // return new Game();
        }


    }

}
