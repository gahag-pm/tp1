package dcc.gahag.chess.piece;


import java.util.Arrays;

import dcc.gahag.chess.board.Coord;


/**
 * The knight piece.
 */
public class Knight implements IPiece {
  protected Coord _position;

  public Coord getPosition() {
    return this._position;
  }

  public void setPosition(Coord c) {
    this._position = c;
  }

  
  protected final Iterable<Coord> _movement = Arrays.asList(
    new Coord(2, 1),
    new Coord(1, 2),
    new Coord(-1, 2),
    new Coord(-2, 1),
    new Coord(-2, -1),
    new Coord(-1, -2),
    new Coord(1, -2),
    new Coord(2, -1)
  );
  
  public Iterable<Coord> movement() {
    return this._movement;
  }


  /**
   * Constructs a knight in the given coordinate.
   * @param c the coordinate for the knight to be placed, possibly null
   */
  public Knight(Coord c) {
    this.setPosition(c);
  }
}
