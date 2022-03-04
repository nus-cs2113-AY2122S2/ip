package marites.exception;

/**
 * Base class for any exceptions marites.Marites may throw.
 */
abstract public class MaritesException extends Exception {
    /**
     * Returns an error message for user consumption.
     * @return Error message
     */
    abstract public String getErrorMessage();
}
