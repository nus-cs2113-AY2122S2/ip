public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String errorMessage, int errorCount) {
        if (errorCount < 3) {
            System.out.println("UwU *whats this?* : " + errorMessage);
        } else if (errorCount < 6) {
            System.out.println("OwO *notices error* : " + errorMessage);
        } else {
            System.out.println("ÒwÓ *intensifies* : " + errorMessage);
        }

    }
}

