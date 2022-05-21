package uma.taw.ubayspring.exception;

public class UbayException extends Exception{
    public UbayException() {
    }

    public UbayException(String message) {
        super(message);
    }

    public UbayException(String message, Throwable cause) {
        super(message, cause);
    }

    public UbayException(Throwable cause) {
        super(cause);
    }

    public UbayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
