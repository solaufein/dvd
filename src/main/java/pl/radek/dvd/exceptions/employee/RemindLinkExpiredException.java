package pl.radek.dvd.exceptions.employee;

/**
 * User: Sola
 * Date: 2014-09-24
 * Time: 13:56
 */
public class RemindLinkExpiredException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RemindLinkExpiredException(String message) {
        super(message);
    }
}
