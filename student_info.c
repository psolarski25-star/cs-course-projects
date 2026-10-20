/**
 * @file student_info.c
 * @author Payton Solarski
 * @date 2026-09-14
 * @brief A basic program making a struct in C.
 *
 * @details This program makes a struct, student_info, and then
 * uses it to make two student variables and prints their contents.
 */
 
#include <stdio.h>
 
/**
 * Struct for student information:
 * student ID, number of credit hours, and GPA.
 */ 
struct student_info
{
	unsigned int id;
	float credit_hours;
	float gpa;
};

/**
 * @brief Main entry point of program.
 * 
 * @details Constructs two student variables using the above struct, 
 * and prints their contents.
 *
 * @param none
 * @return none
 */
int main(void) 
{
	// Constructing two student variables.
	struct student_info student1 = {12345, 30.0, 3.25};
	struct student_info student2 = {67890, 91.67, 3.71};	
	
	// Printing the contents of the above variables.
	printf("ID: %d, credit hours: %.2f, GPA: %.2f\n", student1.id, student1.credit_hours, student1.gpa);
	printf("ID: %d, credit hours: %.2f, GPA: %.2f\n", student2.id, student2.credit_hours, student2.gpa);
	
	// Return 0 if program is a success
	return 0;
}
