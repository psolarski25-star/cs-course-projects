/**
 * @file array_stats.c
 * @author Payton Solarski
 * @date 2026-10-17
 * @brief A program to give different stats on arrays.
 */
 
#include <stdio.h>
#include <stdlib.h>
 
/**
 * Completely fills it with random values between 0 and 
 * some specified positive integer upper bound, inclusive.
 */
void fill_array(unsigned int arr[], int length, unsigned int upper_bound)
{
	// Go through the array and fill it random values
	for(int i = 0; i < length; i++)
	{
		arr[i] = (unsigned int) rand() % (upper_bound + 1);
	}
}

/**
 * Finds the minimum value in this array.
 * @return unsigned int The minimum value
 */
unsigned int array_min(const unsigned int arr[], int length)
{
	// Define the minimum as the first value of the array
	unsigned int min = arr[0];

	for(int i = 1; i < length; i++)
	{
		if(arr[i] < min)
		{
			min = arr[i];
		}
	}
	
	return min;
}

/**
 * Finds the maximum value in this array.
 * @return unsigned int The maximum value
 */
unsigned int array_max(const unsigned int arr[], int length)
{
	// Define the maximum as the first value of the array
	unsigned int max = arr[0];

	for(int i = 1; i < length; i++)
	{
		if(arr[i] > max)
		{
			max = arr[i];
		}
	}
	
	return max;
}

/**
 * Finds the mean (average) value in this array.
 * @return double The mean value
 */
double array_mean(const unsigned int arr[], int length)
{
	// Create a sum variable, start at 0
	double sum = 0.0;

	for(int i = 0; i < length; i++)
	{
		sum += arr[i];
	}
	
	// Divide the sum by the length of the array to find the mean
	return sum / (double) length;
}

/**
 * @brief Main entry point of program.
 * @return 0 success code
 * 
 * @details Creates an array and uses the above helper functions to fill it and
 * find its minimum, maximum, and mean values.
 */
int main(void) 
{
	// Create an array of 1000 unsigned integers
	unsigned int array[1000];
	int length = sizeof(array) / sizeof(array[0]);
	
	// Fill it with random values from 0 to 99
	fill_array(array, length, 99);
	
	// Find and print the minimum, maximum, and mean values of the array
	printf("Minimum: %u\n", array_min(array, length));
	printf("Maximum: %u\n", array_max(array, length));
	printf("Mean: %.2f\n", array_mean(array, length));
	
	// Return 0 if program is a success
	return 0;
}
