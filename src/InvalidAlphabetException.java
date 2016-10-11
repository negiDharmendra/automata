public class InvalidAlphabetException extends Throwable {
    public InvalidAlphabetException(String alphabet) {
        super(String.format("%s is not a valid alphabet", alphabet));
    }
}
