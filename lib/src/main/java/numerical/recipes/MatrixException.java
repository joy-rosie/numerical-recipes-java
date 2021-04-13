package numerical.recipes;

public class MatrixException extends RuntimeException {

    private MatrixErrorType errorCode;

    public MatrixException(String message) {
        super(message);
    }

    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatrixException(MatrixErrorType errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public MatrixException(MatrixErrorType errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public MatrixErrorType getErrorCode() {
        return errorCode;
    }
}

