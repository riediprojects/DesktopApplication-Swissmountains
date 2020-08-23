package ch.fhnw.oop2.swissmountainsfx.exceptions;

/**
 * An exception that is thrown if the mountain prominence is invalid.
 * 
 * @author Linus
 */
public class InvalidMountainProminenceException extends Exception {

    /**
     * Constructs an instance of <code>InvalidMountainProminenceException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMountainProminenceException(String msg) {
        super(msg);
    }
}
