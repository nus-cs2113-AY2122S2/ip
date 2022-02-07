package shrek.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String errorMessage, int errorCount) {
        if (errorCount < 3) {
            System.out.println("UwU *whats this?* : " + errorMessage);
        } else if (errorCount < 8) {
            System.out.println("OwO *notices error* : " + errorMessage);
        } else if (errorCount < 15){
            System.out.println("ÒwÓ *intensifies* : " + errorMessage);
        } else {
            System.out.println("Quit fuzzing me!: " + errorMessage);
        }

    }
}

