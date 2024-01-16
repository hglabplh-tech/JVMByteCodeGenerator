package genbytecj.generator.model.utils;


import genbytecj.generator.model.exceptions.UnexpectedApplicationState;

/**
 * Utilities regarding error handling and simplified throwing mechanisms.
 */
public class ErrorUtils {
    public static UnexpectedApplicationState shouldNotReachHere(String msg) {
        return new UnexpectedApplicationState(msg);
    }

    public static UnexpectedApplicationState shouldNotReachHere() {
        return new UnexpectedApplicationState();
    }
}
