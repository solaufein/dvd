package pl.radek.dvd.exceptions.employee;

/**
 * User: Sola
 * Date: 2014-09-23
 * Time: 18:25
 */
public class EmployeeNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
