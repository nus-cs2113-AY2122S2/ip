package solana;

public class SolanaException extends Exception {
    public SolanaException(String errorMessage) {
        System.out.println(errorMessage + System.lineSeparator());
    }
}
