def main():
    """Main function that coordinates user input, display results,
    and manages program repetition."""

    # Set the loop to true for the first run through
    loop = "Y"
    while loop == "Y":
        # Get the speed and time values from the user
        speed, time = get_inputs()
        # Print the table header for distance output
        print(f"{'Hour':<10}{'Distance (miles)':>15}")
        print("-" * 25)
        # Calculate and display the distance for each hour
        calc_distance(speed, time)
        # Ask user if they want to run the program again
        loop = input("Do you want to run again? (Y/N): ")

def get_inputs():
    """Get the speed and time inputs from the user and validate them.

    Returns:
        tuple: A pair containing speed and time as integers
    """

    # Prompt the user for vehicle speed
    speed = int(input("Enter the vehicle's speed: "))

    # Validate speed is not negative
    while speed < 0:
        speed = int(input("Invalid input, speed cannot be negative. "
        "Reenter the vehicle's speed: "))

    # Prompt the user for time travelled
    time = int(input("Enter the hours travelled: "))

    # Validate time is not negative
    while time < 0:
        time = int(input("Invalid input, hours travelled cannot be negative. "
        "Reenter the hours travelled: "))

    return speed, time

def calc_distance(speed, time):
    """Calculate and print the distance traveled for each hour.

    Args:
        speed (int): The speed of the vehicle in miles per hour.
        time (int): The number of hours traveled.
    """

    # Loop through each hour and compute distance over time
    for i in range(1, time + 1):
        # Distance formula: distance = speed * time
        distance = speed * i
        # Print as a formatted table row
        print(f"{i:<10}{distance:>15}")

# Displays when the program first starts, not everytime
print("Welcome to my Distance Calculator")

# Call on the main function to start the program
main()