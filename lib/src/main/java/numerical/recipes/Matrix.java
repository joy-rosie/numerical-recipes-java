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
}