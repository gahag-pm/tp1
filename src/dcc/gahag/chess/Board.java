package dcc.gahag.chess;


import java.io.PrintStream;

import dcc.gahag.chess.util.FilterIterator;
import dcc.gahag.chess.util.TransformIterator;


/**
 * A chess board with mutable tiles and immutable size.
 * It's tiles are represented by integers, possibly indicating the movement number.
 * The tiles' default value is 0.
 */
public class Board {
  protected int[][] tiles;

  /**
   * The width of the board.
   */
  public final int width;
  /**
   * The size of the board. Equivalent to `width * width`.
   */
  public final int size;
  /**
   * The boundaries of the board.
   */
  public final Bounds bounds;


  /**
   * Construct a Board of the given width.
   * @param width the width of the board, must be a positive number
   */
  public Board(int width) {
    if (width < 1)
      throw new IllegalArgumentException();
    
    this.bounds = new Bounds(
      Coord.origin,
      new Coord(width - 1, width - 1)
    );
    this.tiles = new int[width][width];
    
    this.width = width;
    this.size = width * width;
  }
  

  /**
   * Get the value of a given tile.
   * @param c the Coord of the tile, must be within the board's bounds and mustn't be null
   */
  public int getTile(Coord c) {
    if (c == null)
      throw new IllegalArgumentException("Coord mustn't be null");
    
    if (!this.bounds.contains(c))
      throw new IllegalArgumentException("Coord out of bounds");
    
    return this.tiles[c.x][c.y];
  }

  /**
   * Set the value of a given tile.
   * @param c the Coord of the tile, must be within the board's bounds and mustn't be null
   * @param value the value to be set
   */
  public void setTile(Coord c, int value) {
    if (c == null)
      throw new IllegalArgumentException("Coord mustn't be null");
    
    if (!this.bounds.contains(c))
      throw new IllegalArgumentException("Coord out of bounds");
    
    this.tiles[c.x][c.y] = value;
  }

  /**
   * Reset the value of a given tile (i.e., set the tile's value to 0).
   * @param c the Coord of the tile, must be within the board's bounds and mustn't be null
   * @param value the value to be set
   */
  public void resetTile(Coord c) {
    this.setTile(c, 0);
  }
  

  /**
   * Calculate the possible steps for the given piece within the board.
   * The values are calculated from the piece's movement, current position at the moment
   * of the function call, and the board's boundaries.
   * @param p the piece, mustn't be null
   */
  public Iterable<Coord> steps(final IPiece p) {
    if (p == null)
      throw new IllegalArgumentException("IPiece mustn't be null");
    
    // Keep the piece's current position, for if it changes the iterator won't change.
    final Coord c = p.getPosition();
    
    return () -> new FilterIterator<Coord>(
      new TransformIterator<Coord, Coord>(
        p.movement().iterator(),
        (Coord m) -> new Coord(c.x + m.x, c.y + m.y)
      ),
      (Coord m) -> this.bounds.contains(m)
    );
  }


  /**
   * Print the board's representation to the given PrintStream.
   * @param p the PrintStream to be used, mustn't be null
   */
  public void print(PrintStream p) {
    if (p == null)
      throw new IllegalArgumentException("PrintStream mustn't be null");
    
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles.length; j++)
        p.format("%2d ", this.tiles[j][i]);

      p.println();
    }
  }
}
