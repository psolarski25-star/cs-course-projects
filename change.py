# Denominations' values in cents
HUNDRED_DOLLAR = 10000
FIFTY_DOLLAR = 5000
TWENTY_DOLLAR = 2000
TEN_DOLLAR = 1000
FIVE_DOLLAR = 500
DOLLAR = 100
QUARTER = 25
DIME = 10
NICKEL = 5
PENNY = 1

# Make a list saying of all the denominations and their value in cents
DENOMINATIONS = [
    ("Hundred-dollar Bill", HUNDRED_DOLLAR),
    ("Fifty-dollar Bill", FIFTY_DOLLAR),
    ("Twenty-dollar Bill", TWENTY_DOLLAR),
    ("Ten-dollar Bill", TEN_DOLLAR),
    ("Five-dollar Bill", FIVE_DOLLAR),
    ("Dollar Bill", DOLLAR),
    ("Quarter", QUARTER),
    ("Dime", DIME),
    ("Nickel", NICKEL),
    ("Penny", PENNY)

]


def main():
    """
    Main function that orchestrates the change-making process.

    Coordinates input collection, change calculation, and result display.
    This function serves as the entry point for the program.
    """
    price, tender = get_input()
    change, breakdown = make_change(price, tender)
    display(change, breakdown)


def get_input():
    """Get the price and amount tendered from the user."""
    print("Welcome to my Change Program")
    price = float(input("Enter the sales price (xx.xx): $"))
    tender = float(input("Enter the amount tendered (xx.xx): $"))

    # Keeps looping until both are valid inputs
    while (price < 0) or (tender < 0) or (tender < price):
        if price < 0:
            price = float(input("Sorry, invalid input. Please enter price again: $"))
        elif tender < price:
            tender = float(input(f"Sorry, the tender you entered is not enough. Please enter tender again: $"))
    return price, tender


def make_change(price, tender):
    """
    Calculate change amount and determine optimal denomination breakdown.

    Uses algorithm to minimize the number of bills and coins
    returned by starting with the largest denominations first.

    Args:
        price (float): The sales price of the item.
        tender (float): The amount of money tendered by customer.

    Returns:
        tuple: A tuple containing:
            - change (float): Total change amount in dollars
            - breakdown (list): List of tuples (denomination_name, count)
                                for each denomination needed
    """
    change = tender - price
    # Convert change to cents
    change_cents = int(round(change * 100))

    # Empty list to store results
    breakdown = []
    for name, value in DENOMINATIONS:
        # Calculate the number of each denomination is needed for the change
        count = change_cents // value
        # Updates change_cents to be the remainder of the cents needed
        change_cents %= value
        # If we need a denomination, it'll add to the breakdown list
        if count > 0:
            breakdown.append((name, count))

    return change, breakdown


def display(change, breakdown):
    """
    Display the total change and denomination breakdown to the user.

    Formats and prints the change amount and lists each denomination
    with proper pluralization (e.g., "1 - Quarter" vs "2 - Quarters").
    Special handling for "Penny"/"Pennies" pluralization.

    Args:
        change (float): Total change amount in dollars.
        breakdown (list): List of tuples containing (denomination_name, count)
                        for each denomination to be returned.
    """
    print(f"\nYour change is: ${change:.2f}")
    print("You will receive:")
    # Goes through the list breakdown and prints how many of each denomination
    for name, count in breakdown:
        # Sees if there's only one of the denomination
        if count == 1:
            print(f"{count} - {name}")
        else:
            # See if it's pennies, and make its name different if so
            if name == "Penny":
                print(f"{count} - Pennies")
            else:
                print(f"{count} - {name}s")


main()