# -*- coding: utf-8 -*-
"""
Garrett Burns
CS521 Final project
18Aug18
"""

class construct:
    def __init__(self, construct_quantity):
        self.construct_quantity = construct_quantity
        
    def check_quantity(self):
        if self.construct_quantity > 0 and self.construct_quantity <= 6:
            return self.construct_quantity
        else:
            self.construct_quantity = int(input("Try again. Value must be between 1 and 6: "))
            if self.construct_quantity > 0 and self.construct_quantity <= 6:
                return self.construct_quantity
            else:
                print("Quantity not in range. Program aborting.")
                import sys
                sys.exit(0)


def getOrderList():
    construct_id_input = input("Please enter the IDs for the constructs you plan to filter, separated by a comma.")
    construct_id_list = construct_id_input.split(',')
    return construct_id_list

def getConcentrationList():
    construct_concentration_list = []
    for x in construct_id_list:
        print("Please enter a concentration for construct ", str(x))
        concentration = float(input())
        construct_concentration_list.append(concentration)
    return construct_concentration_list

def getFiltersList():
    number_of_filters_list = []
    for x in construct_concentration_list:
        number_of_filters = 1 + int(x / 5)
        number_of_filters_list.append(number_of_filters)
    return number_of_filters_list
    
def showResults():
    count = 0
    while count < quantity_filtered:
        print("Construct ID: ", construct_id_list[count])
        print("Construct Concentration: ", construct_concentration_list[count], "mg")
        print("Number of filters needed: ", number_of_filters_list[count])
        print()
        count+= 1
        
def check_quantity_match():
    if quantity_filtered == len(construct_id_list):
        return True
    else:
        print("The quantity of constructs you wish to filter does not match the amount of construct IDs you have input.")
        import sys
        sys.exit(0)

quantity_filtered = int(input("Please enter the number of constructs you would like to filtrate. Please note that a maximum of 6 constructs may be filtered. "))
quantity_filtered = construct(quantity_filtered).check_quantity()
construct_id_list = getOrderList()
check_quantity_match()
construct_concentration_list = getConcentrationList()
number_of_filters_list = getFiltersList()
showResults()
input()