package dcc.gahag.chess.util;


import java.lang.management.ManagementFactory;


/**
 * Static class for threading utils.
 */
public final class Threading {
  private Threading() { }

  
  /**
   * Gets the current thread's user time in seconds.
   */
  public static double userTime() {
    return ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() / 1000000000.0;
  }
}
