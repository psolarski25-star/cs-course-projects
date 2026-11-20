/**
 * @file kismet_v3.c
 * @author Payton Solarski
 * @date 2026-11-13
 * @brief A program to play the game Kismet.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/**
 * Enum for the different colors a die can be.
 */
typedef enum
{
	RED,
	GREEN,
	BLACK
} die_color;

/**
 * Struct for Kismet die information: die value and its color.
 */
typedef struct
{
	int value;
	die_color color;
} kismet_die;

/**
 * Gives the color of a die based on its value.
 * @param value The value of the die
 * @return die_color The color for that value
 */
die_color color_for_value(int value);

/**
 * Gives the name of a color as text.
 * @param color The color to convert
 * @return The color's name
 */
const char *color_name(die_color color);

/**
 * Assigns a random number between 1-6 to each die and sets its color accordingly
 * @param dice Array to hold the dice
 */
void roll_dice(kismet_die dice[5]);

/**
 * Sorts dice into ascending order by value.
 * @param dice Array of dice to sort
 */
void sort_dice(kismet_die dice[5]);

/**
 * Counts how many dice show each value. counts[1] through counts[6] are used;
 * counts[0] is unused so the index matches the die value.
 * @param dice Array of dice
 * @param counts Array of 7 ints that receives the counts
 */
void count_values(const kismet_die dice[5], int counts[7]);

/**
 * Displays the dice values and colors.
 * @param dice Array of dice
 */
void print_dice(const kismet_die dice[5]);

/**
 * Sets the five dice to the given values and gives each its matching color.
 * Used by main to force specific hands.
 * @param dice Array of dice to fill
 * @param a First die value
 * @param b Second die value
 * @param c Third die value
 * @param d Fourth die value
 * @param e Fifth die value
 */
void set_dice(kismet_die dice[5], int a, int b, int c, int d, int e);

/**
 * Prints a label, the dice, and the score for one demonstration hand.
 * @param label Description of the hand being shown
 * @param dice Array of dice
 * @param score The score to display
 */
void show_case(const char *label, const kismet_die dice[5], int score);

/**
 * Calculates the total of all the dice.
 * @param dice Array of dice
 * @return int The sum of the dice values
 */
int total(const kismet_die dice[5]);

/**
 * Calculates the score if the user chose the option represented by the value
 * in the basic section.
 * @param dice Array of dice to choose from
 * @param value Value that has been chosen by user
 * @return int The score for that value
 */
int basic_single(const kismet_die dice[5], int value);

/**
 * Calculates the score if there's two pairs of dice with the same colors
 * (e.g. two Aces (black) and two Sixes (black)).
 * @param dice Array of dice to choose from
 * @return int Total of all dice, or 0
 */
int two_pair_same_color(const kismet_die dice[5]);

/**
 * Calculates the score if there are at least three dice with the same value.
 * @param dice Array of dice to choose from
 * @return int Total of all dice, or 0
 */
int three_of_a_kind(const kismet_die dice[5]);

/**
 * Calculates the score if the dice are 1-2-3-4-5 or 2-3-4-5-6 (in any order).
 * @param dice Array of dice to choose from
 * @return int 30, or 0
 */
int straight(const kismet_die dice[5]);

/**
 * Calculates the score if the dice are all the same color.
 * @param dice Array of dice to choose from
 * @return int 35, or 0
 */
int flush(const kismet_die dice[5]);

/**
 * Calculates the score if there's a pair of one value and three of a kind of another.
 * @param dice Array of dice to choose from
 * @return int Total of all dice plus 15, or 0
 */
int full_house(const kismet_die dice[5]);

/**
 * Calculates the score if there is a full house and all dice are the same color.
 * @param dice Array of dice to choose from
 * @return int Total of all dice plus 20, or 0
 */
int full_house_flush(const kismet_die dice[5]);

/**
 * Calculates the score if there are at least four dice of the same value.
 * @param dice Array of dice to choose from
 * @return int Total of all dice plus 25, or 0
 */
int four_of_a_kind(const kismet_die dice[5]);

/**
 * Calculates the score of all the dice no matter value or color.
 * @param dice Array of dice to choose from
 * @return int Total of all dice
 */
int yarborough(const kismet_die dice[5]);

/**
 * Calculates the score if all the dice are the same value.
 * @param dice Array of dice to choose from
 * @return int Total of all dice plus 50, or 0
 */
int kismet(const kismet_die dice[5]);

/**
 * Main entry point into program.
 * @return 0 If code success
 */
int main(void)
{
	kismet_die dice[5];

	set_dice(dice, 1, 1, 6, 6, 3);
	show_case("Two Pair Same Color (scores)", dice, two_pair_same_color(dice));
	set_dice(dice, 1, 1, 2, 2, 3);
	show_case("Two Pair Same Color (zero)", dice, two_pair_same_color(dice));

	set_dice(dice, 4, 4, 4, 2, 5);
	show_case("Three of a Kind (scores)", dice, three_of_a_kind(dice));
	set_dice(dice, 1, 1, 2, 2, 3);
	show_case("Three of a Kind (zero)", dice, three_of_a_kind(dice));

	set_dice(dice, 2, 3, 4, 5, 6);
	show_case("Straight (scores)", dice, straight(dice));
	set_dice(dice, 1, 2, 3, 4, 6);
	show_case("Straight (zero)", dice, straight(dice));

	set_dice(dice, 1, 6, 6, 1, 1);
	show_case("Flush (scores)", dice, flush(dice));
	set_dice(dice, 1, 2, 3, 4, 5);
	show_case("Flush (zero)", dice, flush(dice));

	set_dice(dice, 4, 4, 4, 2, 2);
	show_case("Full House (scores)", dice, full_house(dice));
	set_dice(dice, 1, 2, 3, 4, 5);
	show_case("Full House (zero)", dice, full_house(dice));

	set_dice(dice, 3, 3, 4, 4, 4);
	show_case("Full House Flush (scores)", dice, full_house_flush(dice));
	set_dice(dice, 4, 4, 4, 2, 2);
	show_case("Full House Flush (zero)", dice, full_house_flush(dice));

	set_dice(dice, 5, 5, 5, 5, 1);
	show_case("Four of a Kind (scores)", dice, four_of_a_kind(dice));
	set_dice(dice, 1, 1, 1, 2, 2);
	show_case("Four of a Kind (zero)", dice, four_of_a_kind(dice));

	set_dice(dice, 1, 3, 4, 5, 6);
	show_case("Yarborough (scores)", dice, yarborough(dice));
	set_dice(dice, 1, 1, 1, 1, 1);
	show_case("Yarborough (lowest possible)", dice, yarborough(dice));

	set_dice(dice, 6, 6, 6, 6, 6);
	show_case("Kismet (scores)", dice, kismet(dice));
	set_dice(dice, 6, 6, 6, 6, 5);
	show_case("Kismet (zero)", dice, kismet(dice));

	return 0;
}

die_color color_for_value(int value)
{
	die_color color;

	// Set die's color based on its value
	if(value == 1 || value == 6)
	{
		color = BLACK;
	}
	else if(value == 2 || value == 5)
	{
		color = RED;
	}
	else
	{
		color = GREEN;
	}

	return color;
}

const char *color_name(die_color color)
{
	const char *name;

	// Convert the color to its text name
	if(color == RED)
	{
		name = "red";
	}
	else if(color == GREEN)
	{
		name = "green";
	}
	else
	{
		name = "black";
	}

	return name;
}

void roll_dice(kismet_die dice[5])
{
	// Go through the array and fill each die with a random value and its color
	for(int i = 0; i < 5; i++)
	{
		dice[i].value = rand() % 6 + 1;
		dice[i].color = color_for_value(dice[i].value);
	}
}

void sort_dice(kismet_die dice[5])
{
	// Insertion sort by value; whole dice move so each color stays with its value
	for(int i = 1; i < 5; i++)
	{
		kismet_die current = dice[i];
		int j = i - 1;

		while(j >= 0 && dice[j].value > current.value)
		{
			dice[j + 1] = dice[j];
			j--;
		}

		dice[j + 1] = current;
	}
}

void count_values(const kismet_die dice[5], int counts[7])
{
	// Start every count at zero
	for(int v = 0; v < 7; v++)
	{
		counts[v] = 0;
	}

	// Tally each die under its value
	for(int i = 0; i < 5; i++)
	{
		counts[dice[i].value]++;
	}
}

void print_dice(const kismet_die dice[5])
{
	for(int i = 0; i < 5; i++)
	{
		// Prints the die's value and color
		printf("Die #%d: %d, %s\n", i + 1,
			dice[i].value, color_name(dice[i].color));
	}
}

void set_dice(kismet_die dice[5], int a, int b, int c, int d, int e)
{
	int values[5] = {a, b, c, d, e};

	// Give each die its forced value and the color that goes with it
	for(int i = 0; i < 5; i++)
	{
		dice[i].value = values[i];
		dice[i].color = color_for_value(values[i]);
	}
}

void show_case(const char *label, const kismet_die dice[5], int score)
{
	printf("%s\n", label);
	print_dice(dice);
	printf("Score: %d\n\n", score);
}

int total(const kismet_die dice[5])
{
	int sum = 0;

	// Go through and add to the sum
	for(int i = 0; i < 5; i++)
	{
		sum += dice[i].value;
	}

	return sum;
}

int basic_single(const kismet_die dice[5], int value)
{
	int sum = 0;

	// Go through and add to the sum if the die's value matches
	for(int i = 0; i < 5; i++)
	{
		if(dice[i].value == value)
		{
			sum += value;
		}
	}

	return sum;
}

int two_pair_same_color(const kismet_die dice[5])
{
	int counts[7];
	int score = 0;

	count_values(dice, counts);

	// Check every pair of different values: each needs 2+ dice and the same color
	for(int a = 1; a <= 6; a++)
	{
		for(int b = a + 1; b <= 6; b++)
		{
			if(counts[a] >= 2 && counts[b] >= 2 &&
			   color_for_value(a) == color_for_value(b))
			{
				score = total(dice);
			}
		}
	}

	return score;
}

int three_of_a_kind(const kismet_die dice[5])
{
	int counts[7];
	int score = 0;

	count_values(dice, counts);

	// If any value shows up at least three times, score all the dice
	for(int v = 1; v <= 6; v++)
	{
		if(counts[v] >= 3)
		{
			score = total(dice);
		}
	}

	return score;
}

int straight(const kismet_die dice[5])
{
	int counts[7];
	int score = 0;

	count_values(dice, counts);

	// 2-3-4-5 must each appear once, plus either a 1 or a 6
	if(counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1 &&
	   (counts[1] == 1 || counts[6] == 1))
	{
		score = 30;
	}

	return score;
}

int flush(const kismet_die dice[5])
{
	bool same_color = true;
	int score = 0;

	// Compare every die's color to the first die's color
	for(int i = 1; i < 5; i++)
	{
		if(dice[i].color != dice[0].color)
		{
			same_color = false;
		}
	}

	if(same_color)
	{
		score = 35;
	}

	return score;
}

int full_house(const kismet_die dice[5])
{
	int counts[7];
	int has_three = 0;
	int has_two = 0;
	int score = 0;

	count_values(dice, counts);

	// Look for a value that appears exactly 3 times and one exactly 2 times
	for(int v = 1; v <= 6; v++)
	{
		if(counts[v] == 3)
		{
			has_three = 1;
		}
		else if(counts[v] == 2)
		{
			has_two = 1;
		}
	}

	if(has_three && has_two)
	{
		score = total(dice) + 15;
	}

	return score;
}

int full_house_flush(const kismet_die dice[5])
{
	bool is_flush = false;
	bool is_full_house = false;
	int score = 0;

	// Check if the dice are flush (same color)
	if(flush(dice) != 0)
	{
		is_flush = true;
	}

	// Check if the dice have a full house
	if(full_house(dice) != 0)
	{
		is_full_house = true;
	}

	// If the dice are flush and have a full house calculate the score
	if(is_flush && is_full_house)
	{
		score = total(dice) + 20;
	}

	return score;
}

int four_of_a_kind(const kismet_die dice[5])
{
	int counts[7];
	int score = 0;

	count_values(dice, counts);

	// If any value shows up at least four times, score all the dice plus 25
	for(int v = 1; v <= 6; v++)
	{
		if(counts[v] >= 4)
		{
			score = total(dice) + 25;
		}
	}

	return score;
}

int yarborough(const kismet_die dice[5])
{
	return total(dice);
}

int kismet(const kismet_die dice[5])
{
	int counts[7];
	int score = 0;

	count_values(dice, counts);

	// If any value shows up on all five dice, score all the dice plus 50
	for(int v = 1; v <= 6; v++)
	{
		if(counts[v] == 5)
		{
			score = total(dice) + 50;
		}
	}

	return score;
}
