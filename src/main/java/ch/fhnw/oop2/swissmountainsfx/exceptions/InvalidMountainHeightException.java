package ch.fhnw.oop2.swissmountainsfx.exceptions;

/**
 * An exception that is thrown if the mountain height is invalid.
 * 
 * @author Linus
 */
public class InvalidMountainHeightException extends Exception {

    /**
     * Constructs an instance of <code>InvalidMountainHeightException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMountainHeightException(String msg) {
        super(msg);
    }
}
