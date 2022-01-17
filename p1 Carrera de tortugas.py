import turtle
import random 

s= turtle.Screen()
s.title("Turtle random racing")
s.bgcolor("yellow")

p1= turtle.Turtle()
p2= turtle.Turtle()

#Player 1 objet atributes
p1.hideturtle()
p1.color("blue","blue")
p1.shape("turtle")
p1.shapesize(2,2,2)
p1.pensize(3)
p1.speed(10)

#player 2 objet atributes
p2.hideturtle()
p2.color("red", "red")
p2.shape("turtle")
p2.shapesize(2,2,2)
p2.pensize(3)
p2.speed(10)

#Player 1 making rute
p1.penup()
p1.goto(300,150)
p1.pendown()
p1.circle(40)
p1.penup()
p1.goto(-300,190)
p1.pendown()
p1.showturtle()

#P2 making rute
p2.penup()
p2.goto(300,-150)
p2.pendown()
p2.circle(40)
p2.penup()
p2.goto(-300,-110)
p2.pendown()
p2.showturtle()

#Gameplay
dado = [1,2,3,4,5,6]

def tuki(a,b):
    print("Tuki")
    pass

def getTurno():
        turno1 = 0
        turno1 = random.choice(dado)
        print("Tu numero es: ",turno1,"\n Avanzas ",turno1 * 20)
        return turno1 * 20
        pass

for i in range(20):
    if p1.pos() >= (270,190):
        print("P1 gana")
        break
    elif p2.pos() >= (270,-110):
        print("P2 gana")
        break
    else:
        turtle.listen()
        turtle.onscreenclick(tuki)        
        p1.forward(getTurno())
        p2.forward(getTurno()) 
    pass


turtle.done()

