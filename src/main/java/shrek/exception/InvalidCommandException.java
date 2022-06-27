package shrek.exception;

/**
 * Signals an error caused by an invalid input by the user.
 */
public class InvalidCommandException extends RuntimeException {
    private static int LOW_NUMBER_OF_ERRORS = 5;
    private static int MEDIUM_NUMBER_OF_ERRORS = 10;
    private static int HIGH_NUMBER_OF_ERRORS = 15;

    /**
     * Prints a customised message depending on the number of errors made.
     *
     * @param errorMessage The error message to be printed.
     * @param errorCount   the number of errors made.
     */
    public InvalidCommandException(String errorMessage, int errorCount) {
        if (errorCount < LOW_NUMBER_OF_ERRORS) {
            System.out.println("UwU *whats this?* : " + errorMessage);
        } else if (errorCount < MEDIUM_NUMBER_OF_ERRORS) {
            System.out.println("OwO *notices error* : " + errorMessage);
        } else if (errorCount < HIGH_NUMBER_OF_ERRORS) {
            System.out.println("ÒwÓ *intensifies* : " + errorMessage);
        } else {
            System.out.println("Quit fuzzing me!: " + errorMessage);
        }

    }
}
