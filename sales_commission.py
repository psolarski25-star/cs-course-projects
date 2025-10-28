# Commission rates based on a given sales amount

def get_sales():
    return int(input('Enter the sales amount: $'))

def commission_15(sales):
    return sales * .15

def commission_5(sales):
    return (sales * .05) + 500

def commission_25(sales):
    return (sales - 250) * .25

def display(comm_15, comm_5, comm_25):
    print("1. $", comm_15)
    print("2. $", comm_5)
    print("3. $", comm_25)

def main():
    sales = get_sales()
    comm_15 = commission_15(sales)
    comm_5 = commission_5(sales)
    comm_25 = commission_25(sales)
    display(comm_15, comm_5, comm_25)

main()
