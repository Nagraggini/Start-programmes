# Cyclone💖
print("Cyclone (the height requirement is 137 cm and the cost is 10 credits)")

height = 0
credits = 0

height = int(input("Pls give me the height:"))
credits = int(input("Pls give me the credits:"))

if height > 137 and credits > 10:
    print("Enjoy the ride!")
elif credits <= 10 and height > 137:
    print("You don't have enough credits.")
elif height <= 137 and credits > 10:
    print("You are not tall enough to ride.")
else:
    print("You have not met either requirement.")
