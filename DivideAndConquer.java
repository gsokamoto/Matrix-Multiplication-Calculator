/*
  Author: Grant Okamoto
  Class: CS 331
  Purpose: Test the time complexity/efficiency of matrix multiplication using the divide and conquer method
*/

import java.util.Scanner;
 
public class DivideAndConquer
{ 
    public static void main (String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Divide and Conquer Multiplication Algorithm Test\n");
        
        // request user input
        System.out.println("Enter n of matrix where n is the length and width of a n x n matrix:");
        int input = keyboard.nextInt();
        
		//loop to run multiple tests at once (m = number of tests)
        for(int m = 0; m < 5; m++)
        {
	        int[][] A = new int[input][input];
	        int[][] B = new int[input][input];
	        int[][] C = new int[input][input];
	        
	        // create the matrices
	        for (int i = 0; i < input; i++)
	        {
	            for (int j = 0; j < input; j++)
	            {
	                A[i][j] = 1;
	        		B[i][j] = 1;
	            }
	        }
	        // starts timer for finding time taken
	        long startTime = System.nanoTime();
	        
	        // multiply the matrices
	        MultMatrixObject multMatrix = new MultMatrixObject(A, B);
	        C = multMatrix.DaCMultiply(A, B);
	        
	        // ends timer for finding the time taken
	        long endTime = System.nanoTime();
	        
	        // print the resulting matrix
	        System.out.println("Product of matrices A and B: ");	  
	        for (int i = 0; i < input; i++)
	        {
	            for (int j = 0; j < input; j++)
	            {
	                System.out.print(C[i][j] + " ");
	            }
	            System.out.println();
	        } 

	        // prints time taken
	        System.out.println("Took "+(endTime - startTime) + " ns"); 
	    }
    }
}