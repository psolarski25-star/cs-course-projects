/**
 * @file kismet_v3.c
 * @author Payton Solarski
 * @date 2026-11-18
 * @brief A program to play the game Kismet.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

/**
 * Value stored in a scorecard slot that has not been chosen yet.
 */
#define UNSCORED (-1)

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
 * Scorecard layout (21 slots):
 *   index 0-14  : options 1-15 (index = option number - 1)
 *   index 15    : Basic Total (options 1-6)
 *   index 16    : Bonus
 *   index 17    : Basic Section Total (Basic Total + Bonus)
 *   index 18    : Kismet Section Total (options 7-15)
 *   index 19    : Basic Section Total (carried over to the Kismet section)
 *   index 20    : Game Total
 * Each label includes its option number so the player knows what to type.
 */
const char *const slot_labels[21] =
{
	" 1. Aces",
	" 2. Deuces",
	" 3. Treys",
	" 4. Fours",
	" 5. Fives",
	" 6. Sixes",
	" 7. Two Pair - Same Color",
	" 8. Three of a Kind",
	" 9. Straight",
	"10. Flush",
	"11. Full House",
	"12. Full House - Same Color",
	"13. Four of a Kind",
	"14. Yarborough",
	"15. Kismet",
	"    Basic Total",
	"    Bonus",
	"    Basic Section Total",
	"    Kismet Section Total",
	"    Basic Section Total",
	"    Game Total"
};

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
 * Lets the player re-roll any or all of the dice, up to two times. Each time,
 * the player picks dice by number (1-5) and enters 0 when finished. Choosing
 * no dice ends the re-rolling early.
 * @param dice Array of dice to re-roll
 */
void roll_again(kismet_die dice[5]);

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
 * Displays one row of the scorecard: its label and its score, or "--"
 * if it has not been scored.
 * @param scorecard Array of scores
 * @param slot Index of the row to display
 */
void print_row(const int scorecard[21], int slot);

/**
 * Displays the current state of the scorecard. 
 * Any option not yet chosen will just show a dash.
 * @param scorecard The scores to display
 */
void display_scorecard(const int scorecard[21]);

/**
 * Displays the options that are still available and asks the player to choose
 * one (1-15). Asks again if the number is out of range or already scored.
 * @param scorecard Array of 21 scores
 * @return The chosen option (1-15), or 0 if no options are left to choose
 */
int select_option(const int scorecard[21]);

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
	int scorecard[21];
	int choice;

	// Seed the random number generator so each run is different
	srand((unsigned int) time(NULL));

	// Demonstrate roll_again
	roll_dice(dice);
	printf("Starting roll:\n");
	print_dice(dice);
	roll_again(dice);

	// Start every scorecard slot as not yet chosen
	for(int i = 0; i < 21; i++)
	{
		scorecard[i] = UNSCORED;
	}

	// Demonstrate display_scorecard
	printf("\nFresh scorecard:\n");
	display_scorecard(scorecard);

	// Pretend a few options were chosen, including one that scored zero
	scorecard[0] = 3;    // Aces
	scorecard[3] = 0;    // Fours (zero is a real score)
	scorecard[8] = 30;   // Straight

	printf("After choosing Aces, Fours, and Straight:\n");
	display_scorecard(scorecard);

	// Demonstrate select_option
	choice = select_option(scorecard);
	printf("You chose: %s\n", slot_labels[choice - 1]);

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

void roll_again(kismet_die dice[5])
{
	int rerolls_used = 0;
	bool done = false;

	// Allow at most two re-rolls, or fewer if the player picks no dice
	while(rerolls_used < 2 && !done)
	{
		bool selected[5] = {false, false, false, false, false};
		int choice = -1;
		int count = 0;

		printf("\nRe-roll %d of 2. Your dice:\n", rerolls_used + 1);
		print_dice(dice);

		// Let the player pick dice by number until they enter 0
		while(choice != 0)
		{
			printf("Enter a die number (1-5) to re-roll, or 0 when finished: ");
			scanf("%d", &choice);

			if(choice < 0 || choice > 5)
			{
				printf("Please enter a number between 0 and 5.\n");
			}
			else if(choice != 0)
			{
				selected[choice - 1] = true;
				printf("Die #%d will be re-rolled.\n", choice);
			}
		}

		// Re-roll only the selected dice and update their colors
		for(int i = 0; i < 5; i++)
		{
			if(selected[i])
			{
				dice[i].value = rand() % 6 + 1;
				dice[i].color = color_for_value(dice[i].value);
				count++;
			}
		}

		// Picking no dice means the player is finished
		if(count == 0)
		{
			done = true;
		}
		else
		{
			rerolls_used++;
		}
	}

	printf("\nFinal dice:\n");
	print_dice(dice);
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

void print_row(const int scorecard[21], int slot)
{
	printf("%-30s", slot_labels[slot]);

	if(scorecard[slot] == UNSCORED)
	{
		printf("%4s\n", "--");
	}
	else
	{
		printf("%4d\n", scorecard[slot]);
	}
}

void display_scorecard(const int scorecard[21])
{
	printf("---- BASIC SECTION ----\n");
	for(int i = 0; i < 6; i++)
	{
		print_row(scorecard, i);
	}
	for(int i = 15; i < 18; i++)
	{
		print_row(scorecard, i);
	}

	printf("---- KISMET SECTION ----\n");
	for(int i = 6; i < 15; i++)
	{
		print_row(scorecard, i);
	}
	for(int i = 18; i < 21; i++)
	{
		print_row(scorecard, i);
	}

	printf("\n");
}

int select_option(const int scorecard[21])
{
	int choice = 0;
	int available = 0;
	bool valid = false;

	// Show only the options that have not been scored yet
	printf("Available options:\n");
	for(int i = 0; i < 15; i++)
	{
		if(scorecard[i] == UNSCORED)
		{
			printf("%s\n", slot_labels[i]);
			available++;
		}
	}

	// If nothing is left, skip the prompt so the loop can't run forever
	if(available == 0)
	{
		printf("(none)\n");
		valid = true;
	}

	// Keep asking until the player enters an available option
	while(!valid)
	{
		printf("Choose an option (1-15): ");
		scanf("%d", &choice);

		if(choice < 1 || choice > 15)
		{
			printf("Please enter a number between 1 and 15.\n");
		}
		else if(scorecard[choice - 1] != UNSCORED)
		{
			printf("That option has already been scored. "
				"Please choose an available one.\n");
		}
		else
		{
			valid = true;
		}
	}

	return choice;
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
