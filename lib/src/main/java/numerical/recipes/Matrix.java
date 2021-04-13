package numerical.recipes;

public class Matrix {

    double [][] array;
    int n;
    int m;

    public Matrix(int n, int m, double value) {
        this.array = new double[n][m];
        this.n = n;
        this.m = m;

        for (int i=0; i < this.n; i++) {
            for (int j=0; j < this.m; j++) {
                this.array[i][j] = value;
            }
        }
    }

    public Matrix(int n, int m) {
        this(n, m, Double.NaN);
    }

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix() {
        this(0, 0);
    }

    public Matrix(double[][] array) {
        this.array = array;
        this.n = array.length;
        this.m = array[0].length;
    }

    public static Matrix add(Matrix A, Matrix B) throws Exception {
        return A.add(B);
    }

    public Matrix add(Matrix B) throws Exception {
        if ((B.n != this.n) || (B.m != this.m)) {
            throw new Exception("Dimensions are not equal");
        }
        Matrix newMatrix = new Matrix(this.n, this.m);
        for (int i=0; i < this.n; i++) {
            for (int j=0; j < this.m; j++) {
                newMatrix.array[i][j] = this.array[i][j] + B.array[i][j];
            }
        }
        return newMatrix;
    }

    public static Matrix multiply(Matrix A, Matrix B) throws Exception {
        return A.add(B);
    }

    public Matrix multiply(Matrix B) throws Exception {
        if (B.n != this.m) {
            throw new Exception("Incompatible dimensions for multiplication");
        }
        Matrix newMatrix = new Matrix(this.n, B.m, 0);
        for (int i=0; i < this.n; i++) {
            for (int j=0; j < B.m; j++) {
                for (int k=0; k < this.m; k++){
                    newMatrix.array[i][j] += this.array[i][k] * B.array[k][j];
                }
            }
        }
        return newMatrix;
    }
}