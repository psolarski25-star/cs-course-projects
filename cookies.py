# Make constants
SUGAR_PER_BATCH = 1.5
BUTTER_PER_BATCH = 1
FLOUR_PER_BATCH = 2.75

# Function to see how many cookies
def ask_cookies():
    '''Ask for number of cookies wanted.'''
    return int(input("How many cookies do you want? "))

# Main function
def main():
    '''Call on ask_cookies function to get number of cookies
    wanted. Then, calculate the number of cups of sugar, butter,
    and flour for that number of cookies, and print it.'''
    orig_cookies = ask_cookies()
    cookies = orig_cookies / 48
    print("You will need", cookies * SUGAR_PER_BATCH, "cups of sugar for",
          orig_cookies, "cookies.")
    print("You will need", cookies * BUTTER_PER_BATCH, "cups of butter for",
          orig_cookies, "cookies.")
    print("You will need", cookies * FLOUR_PER_BATCH, "cups of flour for",
          orig_cookies, "cookies.")

# Call main function
main()
