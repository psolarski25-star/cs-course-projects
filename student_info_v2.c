/**
 * @file student_info.c
 * @author Payton Solarski
 * @date 2026-09-14
 * @brief A basic program making a struct in C.
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
 * @brief Main entry point of program.
 * 
 * @details Constructs three student variables using the above type, 
 * and prints their contents.
 *
 * @param none
 * @return none
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
	
	// Printing the contents of the above variables.
	for (int i = 0; i < 3; i++)
	{
		printf("ID: %d, credit hours: %.2f, GPA: %.2f\n", students[i].id, students[i].credit_hours, students[i].gpa);
	}
	
	// Return 0 if program is a success
	return 0;
}
