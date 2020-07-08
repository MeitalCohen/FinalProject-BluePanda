package exceptions;

public class BookAlreadyReturnedException extends BusinessException {
    public BookAlreadyReturnedException() {
        super("Book Already Returned");
    }
}
