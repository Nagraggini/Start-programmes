# coin flip💖
import random

print("Coin flip!")
num = random.randint(0, 1)  # Generates a random number that's either 0 or 1
result = 0

if num > 0.5:
    result = result + 1
    print("Heads")
else:
    print("Tails")
