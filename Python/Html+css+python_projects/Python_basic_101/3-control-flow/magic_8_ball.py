# Magic 8 Ball
print(
    "Magic 8 Ball app! The Magic 8 Ball is a popular office toy and children's toy invented in the 1940s for fortune-telling and advice seeking."
)
import random

question = ""

question = input("Qustion:")

print("Question:" + question)
print("Magic 8 Ball:")

magic_ball_num = random.randint(1, 9)

if magic_ball_num == 1:
    print("Yes - definitely.")

elif magic_ball_num == 2:
    print("It is decidedly so.")

elif magic_ball_num == 3:
    print("Without a doubt.")

elif magic_ball_num == 4:
    print("Reply hazy, try again.")

elif magic_ball_num == 5:
    print("Ask again later.")

elif magic_ball_num == 6:
    print("Better not tell you now")

elif magic_ball_num == 7:
    print("My sources say no.")

elif magic_ball_num == 8:
    print("Outlook not so good.")

else:
    print("Very doubtful.")
