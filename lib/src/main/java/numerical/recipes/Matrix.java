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
                throw new Exception("Inconsistent array dimensions");
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

        if ((this.n != B.n) || (this.m != B.m)) {
            throw new Exception("Dimensions are not equal");
        }

        Matrix C = new Matrix(this.n, this.m);
        for (int i=0; i < this.n; i++) {
            for (int j=0; j < this.m; j++) {
                C.array[i][j] = this.array[i][j] + B.array[i][j];
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

        if (this.m != B.n) {
            throw new Exception("Incompatible dimensions for multiplication");
        }

        Matrix C = new Matrix(0, this.n, B.m);
        for (int i=0; i < C.n; i++) {
            for (int j=0; j < C.m; j++) {
                for (int k=0; k < this.m; k++){
                    C.array[i][j] += this.array[i][k] * B.array[k][j];
                }
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

}