package numerical.recipes;

import java.util.Arrays;

public class Matrix {

    private final double [][] array;
    final int n;
    final int m;

    public double[][] getArray() {
        return this.array;
    }

    public Matrix(double[][] array) throws Exception {
        this.n = array.length;
        this.m = array[0].length;
        this.array = new double[this.n][this.m];

        for (int i=0; i < this.n; i++) {
            if (array[i].length != this.m) {
                throw new Exception("Inconsistent array dimensions");
            }
            // Copy array to make sure we do not reference input array
            System.arraycopy(array[i], 0, this.array[i], 0, this.m);
        }
    }

    public Matrix(double value, int n, int m) throws Exception {
        this.array = new double[n][m];
        this.n = n;
        this.m = m;

        for (int i=0; i < this.n; i++) {
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
        return Arrays.deepEquals(this.getArray(), B.getArray());
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

    public static Matrix add(Matrix A, Matrix B) throws Exception {
        return A.add(B);
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

    public static Matrix multiply(Matrix A, Matrix B) throws Exception {
        return A.multiply(B);
    }

}