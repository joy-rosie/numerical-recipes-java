package numerical.recipes;

import java.util.Arrays;

public class Matrix {

    private final double [][] array;
    final int n;
    final int m;

    public static double[][] copyArray(double[][] array, int n, int m) throws Exception {
        double[][] arrayCopy = new double[n][m];
        for (int i=0; i < n; i++) {
            if (array[i].length != m) {
                throw new MatrixException(MatrixErrorType.DimensionMismatch);
            }
            // Copy array to make sure we do not reference input array
            // https://stackoverflow.com/questions/1697250/difference-between-various-array-copy-methods
            System.arraycopy(array[i], 0, arrayCopy[i], 0, m);
        }
        return arrayCopy;
    }

    public double[][] getArray() throws Exception {
        return copyArray(this.array, this.n, this.m);
    }

    public Matrix(Matrix M) throws Exception {
        this.n = M.n;
        this.m = M.m;
        this.array = M.getArray();
    }

    public Matrix(double[][] array) throws Exception {
        this.n = array.length;
        this.m = array[0].length;
        this.array = copyArray(array, this.n, this.m);
    }

    public Matrix(double value, int n, int m) throws Exception {
        this.array = new double[n][m];
        this.n = n;
        this.m = m;

        for (int i=0; i < this.n; i++) {
            // Could use Arrays.fill(this.array[i], value) instead but it is the same code
            for (int j=0; j < this.m; j++) {
                this.array[i][j] = value;
            }
        }
    }

    public Matrix(double value, int n) throws Exception {
        this(value, n, n);
    }

    public Matrix(int n, int m) throws Exception {
        this(Double.NaN, n, m);
    }

    public Matrix(int n) throws Exception {
        this(n, n);
    }

    public Matrix() throws Exception {
        this(0);
    }

    public boolean equals(Matrix B) throws Exception {
        // "short circuit evaluation" where we do not need to do deep equals if n or m do not match
        // https://stackoverflow.com/questions/16606021/two-conditions-in-one-if-statement-does-the-second-matter-if-the-first-is-false
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op2.html
        return this.n == B.n && this.m == B.m && Arrays.deepEquals(this.array, B.array);
    }

    public Matrix add(Matrix B) throws Exception {
        Matrix C = new Matrix(B.n, B.m);
        try {
            for (int i=0; i < this.n; i++) {
                for (int j=0; j < this.m; j++) {
                    C.array[i][j] = this.array[i][j] + B.array[i][j];
                }
            }
        } catch (Exception e) {
            if (this.n != B.n || this.m != B.m) {
                throw new MatrixException(MatrixErrorType.AddDimensionMismatch, e);
            } else {
                throw e;
            }
        }
        return C;
    }

    public Matrix add(double b) throws Exception {

        Matrix C = new Matrix(this.n, this.m);
        for (int i=0; i < this.n; i++) {
            for (int j=0; j < this.m; j++) {
                C.array[i][j] = this.array[i][j] + b;
            }
        }

        return C;

    }

    public static Matrix add(Matrix A, Matrix B) throws Exception {
        return A.add(B);
    }

    public static Matrix add(Matrix A, double b) throws Exception {
        return A.add(b);
    }

    public static Matrix add(double a, Matrix B) throws Exception {
        return B.add(a);
    }

    public Matrix multiply(Matrix B) throws Exception {
        Matrix C = new Matrix(0, this.n, B.m);
        try {
            for (int i=0; i < C.n; i++) {
                for (int j=0; j < C.m; j++) {
                    for (int k=0; k < this.m; k++){
                        C.array[i][j] += this.array[i][k] * B.array[k][j];
                    }
                }
            }
        } catch(Exception e) {
            if (this.m != B.n) {
                throw new MatrixException(MatrixErrorType.MultiplyDimensionMismatch, e);
            } else {
                throw e;
            }
        }
        return C;
    }

    public Matrix multiply(double b) throws Exception {

        Matrix C = new Matrix(this.n, this.m);
        for (int i=0; i < C.n; i++) {
            for (int j=0; j < C.m; j++) {
                C.array[i][j] = this.array[i][j] * b;
            }
        }

        return C;

    }

    public static Matrix multiply(Matrix A, Matrix B) throws Exception {
        return A.multiply(B);
    }

    public static Matrix multiply(Matrix A, double b) throws Exception {
        return A.multiply(b);
    }

    public static Matrix multiply(double a, Matrix B) throws Exception {
        return B.multiply(a);
    }

    public Matrix transpose() throws Exception {
        Matrix M = new Matrix(this.m, this.n);
        for (int i=0; i < M.n; i++) {
            for (int j=0; j < M.m; j++) {
                M.array[i][j] = this.array[j][i];
            }
        }
        return M;
    }

    public static Matrix transpose(Matrix M) throws Exception {
        return M.transpose();
    }

    public Matrix concatenateVertical(Matrix B) throws Exception {

        if (this.m != B.m) {
            throw new MatrixException(MatrixErrorType.ColumnDimensionMismatch);
        }

        Matrix C = new Matrix(this.n + B.n, this.m);
        for (int i=0; i < this.n; i++) {
            System.arraycopy(this.array[i], 0, C.array[i], 0, C.m);
        }
        for (int i=0; i < B.n; i++) {
            System.arraycopy(B.array[i], 0, C.array[i + this.n], 0, C.m);
        }

        return C;

    }

    public static Matrix concatenateVertical(Matrix A, Matrix B) throws Exception {
        return A.concatenateVertical(B);
    }

    public Matrix concatenateHorizontal(Matrix B) throws Exception {

        if (this.n != B.n) {
            throw new MatrixException(MatrixErrorType.RowDimensionMismatch);
        }

        // This might not be the most efficient in terms of memory
        return this.transpose().concatenateVertical(B.transpose()).transpose();

    }

    public static Matrix concatenateHorizontal(Matrix A, Matrix B) throws Exception {
        return A.concatenateHorizontal(B);
    }

    @Override
    public String toString() {
        return String.join("]\n", Arrays.deepToString(this.array).split("],"));
    }

    public Matrix getSubMatrix(int iStart, int iEnd, int jStart, int jEnd) throws Exception {
        Matrix M = new Matrix(iEnd - iStart + 1, jEnd - jStart + 1);
        for (int i = 0; i < M.n; i++) {
            System.arraycopy(this.array[i + iStart], jStart, M.array[i], 0, M.m);
        }
        return M;
    }

    public Matrix GaussJordanElimination(Matrix B) throws Exception {

        // Gauss-Jordan Elimination
        // The following operations are allowed
        // 1) Swap rows
        // 2) Multiply row by non zero scalar
        // 3) Add one row to the multiple of another
        // We only use (2) and (3) for now
        // We concatenate the two matrices
        // First we make "upper triangular" matrix
        // Then we remove all "non diagonal" values

        Matrix M = this.concatenateHorizontal(B);

        System.out.println(M);
        // Normalize first row by the leading coefficient
        double currentRowLeadingCoefficient = M.array[0][0];
        for (int j=0; j < M.m; j++) {
            M.array[0][j] = M.array[0][j] / currentRowLeadingCoefficient;
        }
        System.out.println(M);

        for (int i=1; i < M.n; i++) {
            // Better to declare inside for loop
            // https://stackoverflow.com/questions/4501482/java-declaring-variables-in-for-loops
            double previousRowLeadingCoefficient = M.array[i][i-1];
            // Subtract previous row multiplied by leading coefficient from current row
            for (int j=0; j < M.m; j++) {
                M.array[i][j] = M.array[i][j] - previousRowLeadingCoefficient * M.array[i-1][j];
            }
            currentRowLeadingCoefficient = M.array[i][i];
            // Normalize current row by leading coefficient
            for (int j=0; j < M.m; j++) {
                M.array[i][j] = M.array[i][j] / currentRowLeadingCoefficient;
            }
            System.out.println(M);
        }

        // Remove all non diagonal coefficients
        for (int i=0; i < M.n; i++) {
            for (int k=0; k < M.n; k++) {
                if (i != k) {
                    double currentRowCoefficientToRemove = M.array[i][k];
                    for (int j=0; j < M.m; j++) {
                        M.array[i][j] = M.array[i][j] - currentRowCoefficientToRemove * M.array[k][j];
                    }
                }
            }
            System.out.println(M);
        }

        return M.getSubMatrix(0, M.n - 1, this.m, M.m - 1);
    }


    public static Matrix identity(int n) throws Exception {
        double[][] array = new double[n][n];
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                if(i == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return new Matrix(array);
    }

    public static Matrix inverseViaGaussJordan(Matrix A) throws Exception {
        if(A.n != A.m) {
            throw new MatrixException(MatrixErrorType.NotSquare);
        }
        Matrix identity = Matrix.identity(A.n);
        Matrix augmentedMatrix = A.concatenateHorizontal(identity);
        Matrix gaussJordan = gaussJordan(augmentedMatrix);
        return gaussJordan.getSubMatrix(0, A.n - 1, A.n, 2*A.n-1);
    }

    public static Matrix gaussJordan(Matrix inputA) throws Exception {
        Matrix A = new Matrix(inputA);
        // https://www.codesansar.com/numerical-methods/matrix-inverse-using-gauss-jordan-method-pseudocode.htm
        // Create a diagonal matrix
        for(int i=0;i<A.n;i++) {
            if(A.array[i][i] == 0) {
                throw new MatrixException(MatrixErrorType.NonInvertible);
            }
            // Make each element in the column zero by taking a multiple of the row which contains the
            // diagonal element of that column
            for(int j=0;j<A.n;j++) {
                if(i != j) {
                    double ratio = A.array[j][i]/A.array[i][i];
                    for(int k=0;k<A.m;k++) {
                        A.array[j][k] = A.array[j][k] - ratio * A.array[i][k];
                    }
                }
            }
        }
        // Normalise the diagonals
        for(int i=0;i<A.n;i++) {
            for(int j=A.n;j<A.m;j++) {
                A.array[i][j] = A.array[i][j]/A.array[i][i];
            }
        }
        return A;
    }
}