package TicTacToe.Factories;

import TicTacToe.Models.BotDifficultyLevel;
import TicTacToe.Strategies.botPlayingStrategy.BotPlayingStrategy;
import TicTacToe.Strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel){
        return  new RandomBotPlayingStrategy();
    }
}
