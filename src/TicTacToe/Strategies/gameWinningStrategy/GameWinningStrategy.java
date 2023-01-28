package TicTacToe.Strategies.gameWinningStrategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Cell;
import TicTacToe.Models.Player;

public interface GameWinningStrategy {
   boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell);
}
