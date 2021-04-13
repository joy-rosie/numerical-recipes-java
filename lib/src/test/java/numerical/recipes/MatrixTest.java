/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package numerical.recipes;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    // Constructor------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixConstructorEmpty() throws Exception {
        double[][] expected = new double[0][0];

        Matrix matrix = new Matrix();

        assertArrayEquals(expected, matrix.getArray());
    }

    @Test
    public void testMatrixConstructorArray() throws Exception {
        double[][] array = {{1, 2}, {3, 4}};
        double[][] expected = {{1, 2}, {3, 4}};

        Matrix matrix = new Matrix(array);

        assertArrayEquals(expected, matrix.getArray());
    }

    @Test
    public void testMatrixConstructorExceptionArrayInconsistentArrayDimensions1() throws Exception {
        double[][] array = {{1, 2}, {3, 4, 5}};
        String expected = "Inconsistent array dimensions";

        Exception thrown = assertThrows(Exception.class, () -> new Matrix(array));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixConstructorExceptionArrayInconsistentArrayDimensions2() throws Exception {
        double[][] array = {{1, 2}, {3}};
        String expected = "Inconsistent array dimensions";

        Exception thrown = assertThrows(Exception.class, () -> new Matrix(array));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixConstructorValueNM() throws Exception {
        double value = 1;
        int n = 2;
        int m = 3;
        double[][] expected = {{1, 1, 1}, {1, 1, 1}};

        Matrix matrix = new Matrix(value, n, m);

        assertArrayEquals(matrix.getArray(), expected);
    }

    @Test
    public void testMatrixConstructorValueN() throws Exception {
        double value = 1;
        int n = 2;
        double[][] expected = {{1, 1}, {1, 1}};

        Matrix matrix = new Matrix(value, n);

        assertArrayEquals(matrix.getArray(), expected);
    }

    @Test
    public void testMatrixConstructorNM() throws Exception {
        int n = 2;
        int m = 3;
        double[][] expected = {{Double.NaN, Double.NaN, Double.NaN}, {Double.NaN, Double.NaN, Double.NaN}};

        Matrix matrix = new Matrix(n, m);

        assertArrayEquals(expected, matrix.getArray());
    }

    @Test
    public void testMatrixConstructorN() throws Exception {
        int n = 2;
        double[][] expected = {{Double.NaN, Double.NaN}, {Double.NaN, Double.NaN}};

        Matrix matrix = new Matrix(n);

        assertArrayEquals(expected, matrix.getArray());
    }

    // Equals-----------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixEqualsTrueEmpty() throws Exception {
        Matrix A = new Matrix();
        Matrix B = new Matrix();
        boolean expected = true;

        boolean equals = A.equals(B);

        assertEquals(expected, equals);
    }

    @Test
    public void testMatrixEqualsTrueNonEmpty() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        boolean expected = true;

        boolean equals = A.equals(B);

        assertEquals(expected, equals);
    }

    @Test
    public void testMatrixEqualsFalseSameSize() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{3, 4}, {1, 2}});
        boolean expected = false;

        boolean equals = A.equals(B);

        assertEquals(expected, equals);
    }

    @Test
    public void testMatrixEqualsFalseDifferentSize() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        boolean expected = false;

        boolean equals = A.equals(B);

        assertEquals(expected, equals);
    }

    // Add--------------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixAddMatrixEmpty() throws Exception {
        Matrix A = new Matrix();
        Matrix B = new Matrix();
        Matrix expected = new Matrix();

        Matrix C = A.add(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddMatrixNonEmpty() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix expected = new Matrix(new double[][]{{6, 8}, {10, 12}});

        Matrix C = A.add(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddMatrixException() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{1, 2}});
        String expected = "Dimensions are not equal";

        Exception thrown = assertThrows(Exception.class, () -> A.add(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixAddDouble() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double b = 1;
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = A.add(b);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddStaticMatrixMatrix() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix expected = new Matrix(new double[][]{{6, 8}, {10, 12}});

        Matrix C = Matrix.add(A, B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddStaticMatrixDouble() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double b = 1;
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = Matrix.add(A, b);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddStaticDoubleMatrix() throws Exception {
        double a = 1;
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = Matrix.add(a, B);

        assertTrue(expected.equals(C));
    }

    // Multiply---------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixMultiplyEmpty() throws Exception {
        Matrix A = new Matrix();
        Matrix B = new Matrix();
        Matrix expected = new Matrix();

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyOneDimension() throws Exception {
        Matrix A = new Matrix(new double[][]{{1}});
        Matrix B = new Matrix(new double[][]{{2}});
        Matrix expected = new Matrix(new double[][]{{2}});

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsIdentity() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{1, 0}, {0, 1}});
        Matrix expected = new Matrix(new double[][]{{1, 2}, {3, 4}});

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsSquare() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {1, 1}});
        Matrix expected = new Matrix(new double[][]{{3, 4}, {7, 10}});

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsRectangle() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {1, 1}});
        Matrix expected = new Matrix(new double[][]{{3, 4}});

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyException() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2, 3}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        String expected = "Incompatible dimensions for multiplication";

        Exception thrown = assertThrows(Exception.class, () -> A.multiply(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixMultiplyDouble() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double b = 2;
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = A.multiply(b);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyStaticMatrixMatrix() throws Exception {
        Matrix A = new Matrix(new double[][]{{1}});
        Matrix B = new Matrix(new double[][]{{2}});
        Matrix expected = new Matrix(new double[][]{{2}});

        Matrix C = Matrix.multiply(A, B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyStaticMatrixDouble() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double b = 2;
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = Matrix.multiply(A, b);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyStaticDoubleMatrix() throws Exception {
        double a = 2;
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = Matrix.multiply(a, B);

        assertTrue(expected.equals(C));
    }

}
