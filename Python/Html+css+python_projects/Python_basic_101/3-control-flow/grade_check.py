# Grade check
# Itt már a behúzás is fontos, mert a feltétel vizsgálatának a belső sorában kell lennie.
print("Grade check!")
grade = int(input("Pls give ma a grade:"))  # Marad a szám formátumban.

if grade > 60:
    print("You passed!")
else:
    print("Better luck next time!")
