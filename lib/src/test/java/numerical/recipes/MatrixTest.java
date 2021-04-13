/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package numerical.recipes;

import org.junit.Ignore;
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
    public void testMatrixConstructorMatrix() throws Exception {
        Matrix M = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double[][] expected = {{1, 2}, {3, 4}};

        Matrix matrix = new Matrix(M);

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
        String expected = MatrixErrorType.DimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> new Matrix(array));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixConstructorExceptionArrayInconsistentArrayDimensions2() throws Exception {
        double[][] array = {{1, 2}, {3}};
        String expected = MatrixErrorType.DimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> new Matrix(array));
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

    // copyArray--------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixCopyArrayStatic() throws Exception {
        int n = 2;
        int m = 2;
        double[][] array = {{1, 2}, {3, 4}};
        double[][] expected = {{1, 2}, {3, 4}};

        double[][] actual = Matrix.copyArray(array, n, m);

        assertArrayEquals(expected, actual);
        // array1 == array2 check if it **is the same object** rather than comparing the contents
        assertNotSame(expected, actual);
    }

    // getArray---------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixGetArray() throws Exception {
        Matrix matrix = new Matrix(2);
        double[][] expected = {{Double.NaN, Double.NaN}, {Double.NaN, Double.NaN}};

        double[][] array = matrix.getArray();

        assertArrayEquals(expected, array);
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
        String expected = MatrixErrorType.AddDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.add(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixAddDouble() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double B = 1;
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = A.add(B);

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
        double B = 1;
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = Matrix.add(A, B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixAddStaticDoubleMatrix() throws Exception {
        double A = 1;
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix expected = new Matrix(new double[][]{{2, 3}, {4, 5}});

        Matrix C = Matrix.add(A, B);

        assertTrue(expected.equals(C));
    }

    // Multiply---------------------------------------------------------------------------------------------------------
    @Test
    public void testMatrixMultiplyException() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2, 3}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        String expected = MatrixErrorType.MultiplyDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.multiply(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixMultiplyEmpty() throws Exception {
        Matrix A = new Matrix();
        Matrix B = new Matrix();
        Matrix expected = new Matrix();

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixScalarMultiplication() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double B = 2;
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = A.multiply(B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixMultiplyOneDimension() throws Exception {
        testMatrixMultiplyHelper(new double[][]{{1}}, new double[][]{{2}}, new double[][]{{2}});
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsIdentity() throws Exception {
        testMatrixMultiplyHelper(new double[][]{{1, 2}, {3, 4}}, new double[][]{{1, 0}, {0, 1}}, new double[][]{{1, 2}, {3, 4}});
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsSquare() throws Exception {
        testMatrixMultiplyHelper(new double[][]{{1, 2}, {3, 4}}, new double[][]{{1, 2}, {1, 1}}, new double[][]{{3, 4}, {7, 10}});
    }

    @Test
    public void testMatrixMultiplyMultipleDimensionsRectangle() throws Exception {
        testMatrixMultiplyHelper(new double[][]{{1, 2}}, new double[][]{{1, 2}, {1, 1}}, new double[][]{{3, 4}});
    }

    @Test
    public void testMatrixMultiplyStaticMatrixMatrix() throws Exception {
        testMatrixMultiplyHelper(new double[][]{{1}}, new double[][]{{2}}, new double[][]{{2}});
    }

    @Ignore
    public void testMatrixMultiplyHelper(double[][] A, double[][] B, double[][] C) throws Exception {
        Matrix AMatrix = new Matrix(A);
        Matrix BMatrix = new Matrix(B);
        Matrix expected = new Matrix(C);

        Matrix CCalculated = AMatrix.multiply(BMatrix);

        assertTrue(expected.equals(CCalculated));
    }

    @Test
    public void testMatrixStaticMethodScalarMultiplicationFirstArgumentMatrix() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double B = 2;
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = Matrix.multiply(A, B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixStaticMethodScalarMultiplicationFirstArgumentScalar() throws Exception {
        double A = 2;
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix expected = new Matrix(new double[][]{{2, 4}, {6, 8}});

        Matrix C = Matrix.multiply(A, B);

        assertTrue(expected.equals(C));
    }

    @Test
    public void testMatrixStaticMethodMatrixMultiplication() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 0}, {0, 1}});
        Matrix B = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix expected = new Matrix(new double[][]{{1, 2}, {3, 4}});

        Matrix C = Matrix.multiply(A, B);

        assertTrue(expected.equals(C));
    }

    // transpose--------------------------------------------------------------------------------------------------------

    @Test
    public void testMatrixTranspose() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});
        Matrix expected = new Matrix(new double[][]{{1, 3, 5}, {2, 4, 6}});

        Matrix M = A.transpose();

        assertTrue(expected.equals(M));
    }

    // concatenateVertical--------------------------------------------------------------------------------------------

    @Test
    public void testMatrixConcatenateVertical() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5, 6}});
        Matrix expected = new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});

        Matrix M = A.concatenateVertical(B);

        assertTrue(expected.equals(M));
    }

    @Test
    public void testMatrixConcatenateVerticalException1() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5}});
        String expected = MatrixErrorType.ColumnDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.concatenateVertical(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixConcatenateVerticalException2() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5, 6, 7}});
        String expected = MatrixErrorType.ColumnDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.concatenateVertical(B));
        assertEquals(expected, thrown.getMessage());
    }

    // concatenateHorizontal--------------------------------------------------------------------------------------------

    @Test
    public void testMatrixConcatenateHorizontal() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5}, {6}});
        Matrix expected = new Matrix(new double[][]{{1, 2, 5}, {3, 4, 6}});

        Matrix M = A.concatenateHorizontal(B);

        assertTrue(expected.equals(M));
    }

    @Test
    public void testMatrixConcatenateHorizontalException1() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5}});
        String expected = MatrixErrorType.RowDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.concatenateHorizontal(B));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    public void testMatrixConcatenateHorizontalException2() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(new double[][]{{5}, {6}, {7}});
        String expected = MatrixErrorType.RowDimensionMismatch.getMessage();

        Exception thrown = assertThrows(MatrixException.class, () -> A.concatenateHorizontal(B));
        assertEquals(expected, thrown.getMessage());
    }

}
