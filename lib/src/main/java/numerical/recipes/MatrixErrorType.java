package numerical.recipes;

public enum MatrixErrorType {
    DimensionMismatch("Dimensions of matrices incompatible to perform the operation."),
    RowDimensionMismatch("Row dimensions of matrices incompatible to perform the operation."),
    ColumnDimensionMismatch("Column dimensions of matrices incompatible to perform the operation."),
    AddDimensionMismatch("Dimensions of matrices are not the same. When adding matrices the dimensions must be equal."),
    MultiplyDimensionMismatch("Dimensions of matrices are not the same. When multiplying matrices the inner dimensions must agree."),
    NonInvertible("This matrix is not invertible.");

    private final String message;

    MatrixErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
