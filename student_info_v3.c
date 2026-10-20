/**
 * @file student_info_v3.c
 * @author Payton Solarski
 * @date 2026-10-07
 * @brief A program to construct students and print their information.
 *
 * @details This program defines a type, student_info, and then
 * uses it to make an array containing three student variables and 
 * prints their contents.
 */
 
#include <stdio.h>
 
/**
 * Struct for student information:
 * student ID, number of credit hours, and GPA.
 */ 
typedef struct 
{
	unsigned int id;
	float credit_hours;
	float gpa;
} student_info;

/**
 * Print each student within the given array.
 */
void print_students(int count, student_info students[count]);

/**
 * @brief Main entry point of program.
 * @return 0 success code
 * 
 * @details Constructs three student variables using the above type, 
 * and prints their contents.
 *
 */
int main(void) 
{
	// Creating an array with three student variables.
	student_info students[3] = 
	{
		{12345, 30.0, 3.25},
		{67890, 91.67, 3.71},
		{20238, 68.14, 3.16}
	};
	
	// Find length of array
	int count = (int)(sizeof(students) / sizeof(students[0]));
	
	// Call the print function
	print_students(count, students);
	
	// Return 0 if program is a success
	return 0;
}

void print_students(int count, student_info students[count])
{
	// Go through array and use pointer to print each student within it
	for (int i = 0; i < count; i++)
	{
		printf("ID: %d, credit hours: %.2f, GPA: %.2f\n", 
			students[i].id, 
			students[i].credit_hours, 
			students[i].gpa);
	}		
}
