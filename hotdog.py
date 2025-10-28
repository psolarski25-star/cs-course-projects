# Defining constants
HOT_DOGS_PER_PACKAGE = 10
BUNS_PER_PACKAGE = 8

def main():
    """Main function to take in all other functons and output for the user."""
    # Gathering all the values needed
    total_hot_dogs = get_inputs()
    dogs_needed, leftover_dogs = calculate_hot_dogs(total_hot_dogs)
    buns_needed, leftover_buns = calculate_buns(total_hot_dogs)

    # Use display function to output calculations
    display(dogs_needed, leftover_dogs, buns_needed, leftover_buns)

def get_inputs():
    """
    Use attendance and hot dog numbers from user to calculate
    how many total hot dogs will be needed for the cookout.
    """
    # Get attendance and number of hot dogs per person.
    total_attendance = int(input("How many people are in attendance? "))
    hot_dogs_per_person = int(input("How many hot dogs per person? "))

    # Take both inputs and use them to find how many total hot dogs are needed
    total_hot_dogs = total_attendance * hot_dogs_per_person
    return total_hot_dogs

def calculate_hot_dogs(total_hot_dogs):
    """Calculate the number of hot dog packages needed and leftovers."""
    dogs_needed = (total_hot_dogs + HOT_DOGS_PER_PACKAGE - 1) // HOT_DOGS_PER_PACKAGE
    leftover_dogs = dogs_needed * HOT_DOGS_PER_PACKAGE - total_hot_dogs
    return dogs_needed, leftover_dogs

def calculate_buns(total_hot_dogs):
    """Calculate the number of bun packages needed and leftovers."""
    buns_needed = (total_hot_dogs + BUNS_PER_PACKAGE - 1) // BUNS_PER_PACKAGE
    leftover_buns = buns_needed * BUNS_PER_PACKAGE - total_hot_dogs
    return buns_needed, leftover_buns

def display(dogs_needed, leftover_dogs, buns_needed, leftover_buns):
    """Taking all the calculated values and displaying everything."""
    # NOT using f-strings to display final numbers
    print("The minimum number of hot dog packages needed is", dogs_needed, "with",
          leftover_dogs, "left over hot dogs.")
    print("The minimum number of bun packages needed is", buns_needed, "with",
          leftover_buns, "left over buns.")

main()