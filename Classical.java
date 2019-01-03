/*
  Author: Grant Okamoto
  Class: CS 331
  Purpose: Test the time complexity/efficiency of matrix multiplication using the classical method
*/

import java.util.Scanner;

public class Classical {

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int input;
		System.out.println("Classical Multiplication Algorithm Test\n");
		
        // request user input
		System.out.println("Please input n for both matricies of size n x n: ");
		input = keyboard.nextInt();
		
		//loop to run multiple tests at once (m = number of tests)
		for(int m = 0; m < 5; m++)
		{
			int[][] A = new int[input][input];
			int[][] B = new int[input][input];
			int[][] C = new int[input][input];
			
			// create matrices
			for (int i = 0; i < input; i++)
	        {
	            for (int j = 0; j < input; j++)
	            {
	                A[i][j] = 1;
	        		B[i][j] = 1;
	            }
	        }
			
			// starts timer for time taken
			long startTime = System.nanoTime();
			// multiply the 2 matrices
			for(int i = 0; i < input; i++)
			{
				for(int j = 0; j < input; j++)
				{
					for(int k = 0; k < input; k++)
					{
						C[i][j] = C[i][j] + (A[i][k] * B[j][k]);
					}
				}
			}
			
			// ends timer for time taken
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
	        System.out.println("Took "+ (endTime - startTime) + " ns"); 
		}
	}
}
