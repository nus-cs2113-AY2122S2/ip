package shrek.exception;

public class InvalidCommandException extends RuntimeException {
    private static int LOW_NUMBER_OF_ERRORS = 5;
    private static int MEDIUM_NUMBER_OF_ERRORS = 10;
    private static int HIGH_NUMBER_OF_ERRORS = 15;

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
