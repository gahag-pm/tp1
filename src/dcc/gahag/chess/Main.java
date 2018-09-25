package dcc.gahag.chess;


import java.lang.management.ManagementFactory;

import dcc.gahag.chess.util.Box;


public class Main {
  /**
   * Gets the current thread's user time in seconds.
   */
  private static double userTime() {
    return ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() / 1000000000.0;
  }
  
  
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
    
    System.out.printf("User time: %.3f seconds.%n", userTime());
  }
}
