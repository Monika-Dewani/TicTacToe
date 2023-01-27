package TicTacToe.Models;

import TicTacToe.Factories.BotPlayingStrategyFactory;
import TicTacToe.Strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;


    // constructor created by intellij
//    public Bot(String name, char aChar, PlayerType type) {
//        super(name, aChar, type);
//    }

    public Bot(String name, char aSymbol, BotDifficultyLevel difficultyLevel) {
        super(name, aSymbol, PlayerType.BOT);
        this.botDifficultyLevel=difficultyLevel;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }



    public BotDifficultyLevel getBotDifficultyLevel(){
        return botDifficultyLevel;
    }


    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        this.botDifficultyLevel=botDifficultyLevel;
    }

    // bot is one of our plyayer so we will also implement the
    // decideMove methode her ein bot
    public Move decideMove(Board board){
        // bot will decide the move based on playing strategy
        //and based on the difficulty level bot will decide the playing strategy
        // SO we will use Factory design Pattern to kepp which will give us the correct instance of
        // playing startegy based on the value of botDifficulty level;
        return botPlayingStrategy.decideMove(this,board);
    }

}
