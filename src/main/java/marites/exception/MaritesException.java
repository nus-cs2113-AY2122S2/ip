package marites.exception;

/**
 * Base class for any exceptions marites.Marites may throw.
 */
abstract public class MaritesException extends Exception {
    abstract public String getErrorMessage();
}
