package dcc.gahag.chess;


import dcc.gahag.chess.board.Board;
import dcc.gahag.chess.board.Coord;
import dcc.gahag.chess.piece.Horse;
import dcc.gahag.chess.util.Box;
import dcc.gahag.chess.util.Threading;


public final class Main {
  public static void main(String args[]) {
    Board board = new Board(8);
    Horse horse = new Horse(Coord.random(board.bounds));
    Box<Long> moves = new Box<Long>();

    Coord position = horse.getPosition();
    System.out.printf("Initial position: (%d, %d)%n", position.x, position.y);
    
    if (horse.tour(board, moves))
      board.print(System.out);
    else
      System.err.println("Solution does not exist");

    System.out.println();

    System.out.printf("Total moves: %d.%n", moves.value);
    
    System.out.printf("User time: %.3f seconds.%n", Threading.userTime());
  }
}
