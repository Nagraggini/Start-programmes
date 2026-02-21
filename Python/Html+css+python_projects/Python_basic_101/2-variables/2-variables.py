"""
Number
Integer: Integer(negative, zero and positive) numbers Example: ... -3, -2, -1, 0, 1, 2, 3 ...
Float: Decimal number Example ... -3.5, -2.25, -1.0, 0.0, 1.1, 2.2, 3.5 ...
Complex Example 1 + j, 2 + 4j
String
A collection of one or more characters under a single or double quote.
If a string is more than one sentence then we use a triple quote.
"""

# Python érzékeny a kis- és nagybetűkre.

# Bekérjük a felhasználó nevét és életkorát
# A bemenetet string formátumban tároljuk, de az életkor számként.
print("Bekérjük a felhasználó nevét és életkorát.")
username = input("Enter your name: ")
print(username)

age = int(input("What is your age? "))  # Marad a szám formátumban.
print(age)
