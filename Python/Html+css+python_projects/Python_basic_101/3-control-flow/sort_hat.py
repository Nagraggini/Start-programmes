# Sortingg Hat HP💖
description = """
The Sorting Hat is a magical talking hat at Hogwarts School of Witchcraft and Wizardry. 
The hat decides which of the four "Houses" each first-year student goes to:\n
🦁 Gryffindor
🦅 Ravenclaw
🦡 Hufflepuff
🐍 Slytherin
"""
print(description)

# For result.
Gryffindor = 0
Ravenclaw = 0
Hufflepuff = 0
Slytherin = 0

# First question
q1 = """
  Q1) Do you like Dawn or Dusk?
      1) Dawn
      2) Dusk
      """
a1 = int(input(q1))

if a1 == 1:
    Gryffindor = Gryffindor + 1
    Ravenclaw = Ravenclaw + 1

elif a1 == 2:
    Hufflepuff = Hufflepuff + 1
    Slytherin = Slytherin + 1

else:
    print("Wrong input.")

# Second question
q2 = """
  When I’m dead, I want people to remember me as:
    1) The Good
    2) The Great
    3) The Wise
    4) The Bold
      """
a2 = int(input(q2))

if a2 == 1:
    Hufflepuff = Hufflepuff + 2

elif a2 == 2:
    Slytherin = Slytherin + 2

elif a2 == 3:
    Ravenclaw = Ravenclaw + 2

elif a2 == 4:
    Gryffindor = Gryffindor + 2

else:
    print("Wrong input.")

# Third question
q3 = """
  Q3) Which kind of instrument most pleases your ear?
      1) The violin
      2) The trumpet
      3) The piano
      4) The drum
      """
a3 = int(input(q3))

if a3 == 1:
    Slytherin = Slytherin + 4

elif a3 == 2:
    Hufflepuff = Hufflepuff + 4

elif a3 == 3:
    Ravenclaw = Ravenclaw + 4

elif a3 == 4:
    Gryffindor = Gryffindor + 2

else:
    print("Wrong input.")

# Results
print("\nScore:")
print("Slytherin:", Slytherin)
print("Gryffindor:", Gryffindor)
print("Ravenclaw:", Ravenclaw)
print("Hufflepuff:", Hufflepuff)
