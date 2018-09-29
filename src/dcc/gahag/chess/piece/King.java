package dcc.gahag.chess.piece;


import java.util.List;

import dcc.gahag.chess.board.Coord;


/**
 * The king piece.
 */
public class King implements IPiece {
  protected Coord _position;

  public Coord getPosition() {
    return this._position;
  }

  public void setPosition(Coord c) {
    this._position = c;
  }

  
  protected final Iterable<Coord> _movement = List.of(
    new Coord(0, 1),
    new Coord(1, 0),
    new Coord(-1, 0),
    new Coord(0, -1),
    new Coord(1, 1),
    new Coord(-1, 1),
    new Coord(1, -1),
    new Coord(-1, -1)
  );
  
  public Iterable<Coord> movement() {
    return this._movement;
  }


  /**
   * Constructs a king in the given coordinate.
   * @param c the coordinate for the king to be placed, possibly null
   */
  public King(Coord c) {
    this.setPosition(c);
  }
}
