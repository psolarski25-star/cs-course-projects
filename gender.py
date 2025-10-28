# Get inputs from the user
female_count = int(input("Enter number of females: "))
male_count = int(input("Enter number of males: "))
total_count = male_count + female_count

# Calculate female and male percentages, and round results
female_per = round(((female_count / total_count) * 100), 2)
male_per = round(((male_count / total_count) * 100), 2)

# Display results
print("The percentage of females is", str(female_per) + "%")
print("The percentage of males is", str(male_per) + "%")