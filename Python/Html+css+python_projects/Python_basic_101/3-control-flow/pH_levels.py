# pH Level
# Itt már a behúzás is fontos, mert a feltétel vizsgálatának a belső sorában kell lennie.
print("pH Level check!")
grade = int(input("pH Level:"))  # Marad a szám formátumban.

if grade > 7:
    print("Basic")
elif grade < 7:
    print("Acidic")
else:
    print("Neutral")
