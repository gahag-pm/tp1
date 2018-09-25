package dcc.gahag.chess.board;


import java.util.Random;


/**
 * A bidimensional immutable coordinate.
 */
public class Coord {
  public final int x, y;


  /**
   * The origin coordinate, i.e. (0, 0).
   */
  public static final Coord origin = new Coord(0, 0);


  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  
  
  /**
   * Construct a random Coord, within the given bounds, if any.
   * @param b the bounds within the Coord will be randomized, possibly null
   */
  public static Coord random(Bounds b) {
    if (b == null) {
      Random r = new Random();
      
      return new Coord(r.nextInt(), r.nextInt());
    }
    
    return new Coord(
      b.lower.x + (int) (Math.random() * (b.upper.x - b.lower.x)),
      b.lower.y + (int) (Math.random() * (b.upper.y - b.lower.y))
    );
  }
}
