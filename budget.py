def main():
    """
    Main function that runs the budgeting program.
    It gets the user's budget, records expenses, displays the results,
    and asks whether the user wants to run the program again.
    """
    budget = get_budget()
    expenses = get_expenses(budget)
    display(budget, expenses)
    get_loop()

def get_budget():
    """
    Prompts the user to enter their total budget amount.

    Returns:
        float: The total budget entered by the user.
    """
    # Get budget from user
    budget = float(input("Enter your budget amount (xxxx.xx): $"))
    return budget

def get_expenses(budget):
    """
    Prompts the user to enter expenses one by one until they enter 0.
    Validates that expenses are positive values and sums them.

    Args:
        budget (float): The user's total budget (not directly used, but included for context).

    Returns:
        float: The total of all expenses entered by the user.
    """
    # Initializes expenses as 0
    expenses = 0

    # Get the first expense from the user
    expense = float(input("Enter an expense (xxx.xx): $"))

    # Continue asking for expenses until 0 is entered
    while expense != 0:
        if expense < 0:
            # Rejects negative expenses and get new valid input
            print("Invalid expense. Please enter a positive value.")
            expense = float(input("Enter an expense (xxx.xx): $"))
        else:
            # Adds valid expenses to total
            expenses += expense
            # Prompt for next expense
            expense = float(input("Enter an expense (xxx.xx): $"))

    return expenses

def display(budget, expenses):
    """
    Displays the user's budget status (over or under budget).

    Args:
        budget (float): The total budget amount.
        expenses (float): The total amount of expenses.
    """

    # Calculate remaining budget
    budget_left = budget - expenses

    # Display the appropriate message based on if the user is over or under budget
    if budget_left < 0:
        print(f"You are ${abs(budget_left):.2f} over budget.")
    else:
        print(f"You are ${budget_left:.2f} under budget.")

def get_loop():
    """
    Asks the user if they want to run anzother budgeting session.
    Returns True to continue, False to exit.
    """
    loop = input("Would you like to do another budget? (Y/N): ").upper()
    if loop == "Y":
        main()
    else:
        print("Goodbye")

if __name__ == '__main__':
    main()
