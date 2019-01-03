/*
  Author: Grant Okamoto
  Class: CS 331
  Purpose: object that can multiply 2 matrices using strassen or divide and conquer
*/

public class MultMatrixObject {

	private int[][] A;
	private int[][] B;
	
	public MultMatrixObject(int[][] newA, int[][] newB)
	{
		A = newA;
		B = newB;
	}
	//Divide and Conquer Multiplication
    public int[][] DaCMultiply(int[][] A, int[][] B)
    {     
    	int length = A.length;
        int[][] C = new int[length][length];
        // base case
        if (length == 1)
        {
            C[0][0] = A[0][0] * B[0][0];
        }
        else
        {
            int[][] A11 = new int[length/2][length/2];
            int[][] A12 = new int[length/2][length/2];
            int[][] A21 = new int[length/2][length/2];
            int[][] A22 = new int[length/2][length/2];
            int[][] B11 = new int[length/2][length/2];
            int[][] B12 = new int[length/2][length/2];
            int[][] B21 = new int[length/2][length/2];
            int[][] B22 = new int[length/2][length/2];
 
            // Dividing matrix A into 4 matrices
            divide(A, A11, 0 , 0);
            divide(A, A12, 0 , length/2);
            divide(A, A21, length/2, 0);
            divide(A, A22, length/2, length/2);
            // Dividing matrix A into 4 matrices
            divide(B, B11, 0 , 0);
            divide(B, B12, 0 , length/2);
            divide(B, B21, length/2, 0);
            divide(B, B22, length/2, length/2);
 
            /*
            C11 = A11*B11 + A12*B21
            C12 = A11*B12 + A12*B22
            C21 = A21*B11 + A22*B21
            C22 = A21*B12 + A22*B22
            */
            int [][] C11 = add(DaCMultiply(A11,B11), DaCMultiply(A12,B21));
            int [][] C12 = add(DaCMultiply(A11,B12), DaCMultiply(A12,B22));
            int [][] C21 = add(DaCMultiply(A21,B11), DaCMultiply(A22,B21));
            int [][] C22 = add(DaCMultiply(A21,B12), DaCMultiply(A22,B22));
 
            // merge 4 matrices into one matrix (C)
            merge(C11, C, 0 , 0);
            merge(C12, C, 0 , length/2);
            merge(C21, C, length/2, 0);
            merge(C22, C, length/2, length/2);
        }
        return C;
    }
    // strassen multiplication
    public int[][] strassenMultiply(int[][] A, int[][] B)
    {        
    	int length = A.length;
        int[][] C = new int[length][length];
        // base case
        if (length == 1)
        {
            C[0][0] = A[0][0] * B[0][0];
        }
        else
        {
            int[][] A11 = new int[length/2][length/2];
            int[][] A12 = new int[length/2][length/2];
            int[][] A21 = new int[length/2][length/2];
            int[][] A22 = new int[length/2][length/2];
            int[][] B11 = new int[length/2][length/2];
            int[][] B12 = new int[length/2][length/2];
            int[][] B21 = new int[length/2][length/2];
            int[][] B22 = new int[length/2][length/2];
 
            //divide matrix A into 4 matrices
            divide(A, A11, 0 , 0);
            divide(A, A12, 0 , length/2);
            divide(A, A21, length/2, 0);
            divide(A, A22, length/2, length/2);
            //divide matrix B into 4 matrices
            divide(B, B11, 0 , 0);
            divide(B, B12, 0 , length/2);
            divide(B, B21, length/2, 0);
            divide(B, B22, length/2, length/2);
 
            /*
              M1 = (A11 + A22)(B11 + B22)
              M2 = (A21 + A22) B11
              M3 = A11 (B12 - B22)
              M4 = A22 (B21 - B11)
              M5 = (A11 + A12) B22
              M6 = (A21 - A11) (B11 + B12)
              M7 = (A12 - A22) (B21 + B22)
            */
 
            int [][] M1 = strassenMultiply(add(A11, A22), add(B11, B22));
            int [][] M2 = strassenMultiply(add(A21, A22), B11);
            int [][] M3 = strassenMultiply(A11, subtract(B12, B22));
            int [][] M4 = strassenMultiply(A22, subtract(B21, B11));
            int [][] M5 = strassenMultiply(add(A11, A12), B22);
            int [][] M6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
            int [][] M7 = strassenMultiply(subtract(A12, A22), add(B21, B22));
 
            /*
              C11 = M1 + M4 - M5 + M7
              C12 = M3 + M5
              C21 = M2 + M4
              C22 = M1 - M2 + M3 + M6
            */
            int [][] C11 = add(subtract(add(M1, M4), M5), M7);
            int [][] C12 = add(M3, M5);
            int [][] C21 = add(M2, M4);
            int [][] C22 = add(subtract(add(M1, M3), M2), M6);
 
            // merge 4 matrices into one matrix (C)
            merge(C11, C, 0 , 0);
            merge(C12, C, 0 , length/2);
            merge(C21, C, length/2, 0);
            merge(C22, C, length/2, length/2);
        } 
        return C;
    }
    // method that subtracts all values within 2 matrices
    private int[][] subtract(int[][] A, int[][] B)
    {
    	int length = A.length;
        int[][] tempC = new int[length][length];
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                tempC[i][j] = A[i][j] - B[i][j];
            }
        }
        return tempC;
    }
    // method that adds all values within 2 matrices
    private int[][] add(int[][] A, int[][] B)
    {
    	int length = A.length;
        int[][] tempC = new int[length][length];
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                tempC[i][j] = A[i][j] + B[i][j];
            }
        }
        return tempC;
    }
    // method that divides a parent matrix into 2 child matrices
    private void divide(int[][] Parent, int[][] Child, int startI, int startJ) 
    {
        for(int i1 = 0, i2 = startI; i1 < Child.length; i1++, i2++)
            for(int j1 = 0, j2 = startJ; j1 < Child.length; j1++, j2++)
                Child[i1][j1] = Parent[i2][j2];
    }
    // method that merges 2 child matrices into 1 parent matrix
     void merge(int[][] Child, int[][] Parent, int startI, int startJ) 
    {
        for(int i1 = 0, i2 = startI; i1 < Child.length; i1++, i2++)
            for(int j1 = 0, j2 = startJ; j1 < Child.length; j1++, j2++)
                Parent[i2][j2] = Child[i1][j1];
    }
    
}
