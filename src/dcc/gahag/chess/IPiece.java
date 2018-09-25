package dcc.gahag.chess;


import java.util.function.IntPredicate;

import dcc.gahag.chess.util.Box;


/**
 * The basic interface of a chess piece.
 */
public interface IPiece {
  /**
   * The piece's current position.
   */
  Coord getPosition();
  /**
   * Set the piece's current position.
   */
  void setPosition(Coord c);

  /**
   * The movement mechanic of the piece.
   * An iterator of coordinates that indicates the possible movements of the piece from the
   * origin.
   */
  Iterable<Coord> movement();
  

  /**
   * The tour algorithm.
   * The default implementation is a simple call to the overload with a null moves counter.
   * @param board the board to tour, mustn't be null
   */
  default boolean tour(final Board board) {
    return this.tour(board, null);
  }
  
  /**
   * The tour algorithm.
   * The default implementation is a backtracking brute-force algorithm, that suits all
   * the possible pieces.
   * @param board the board to tour, mustn't be null
   * @param moves a output counter for the number of movements calculated, possibly null
   */
  default boolean tour(final Board board, final Box<Long> moves) {
    if (board == null)
      throw new IllegalArgumentException("board mustn't be null");
    
    final IPiece piece = this;
    
    IntPredicate tour = new IntPredicate() {
      public boolean test(int move) {
        if (move == board.size + 1) // The board is complete.
          return true;

        if (moves != null)
          moves.value++;
        
        for (Coord s : board.steps(piece))
          if (board.getTile(s) == 0) { // unvisited tile.
            // Save the initial position, in case a backtrack is needed:
            Coord c = piece.getPosition();
            
            piece.setPosition(s);
            board.setTile(s, move);
            
            if (this.test(move + 1))
              return true;
            
            // backtrack:
            piece.setPosition(c);
            board.resetTile(s);
          }
        
        return false;
      }
    };

    board.setTile(this.getPosition(), 1); // Start the tour in the current position.

    if (moves != null)
      moves.value = 1L;
    
    return tour.test(2); // Attempt the tour from the start.
  }
}
