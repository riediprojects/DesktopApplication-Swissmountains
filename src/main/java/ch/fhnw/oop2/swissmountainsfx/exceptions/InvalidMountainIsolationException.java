package ch.fhnw.oop2.swissmountainsfx.exceptions;

/**
 * An exception that is thrown if the mountain isolation is invalid.
 * 
 * @author Linus
 */
public class InvalidMountainIsolationException extends Exception {

    /**
     * Constructs an instance of <code>InvalidMountainIsolationException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMountainIsolationException(String msg) {
        super(msg);
    }
}
