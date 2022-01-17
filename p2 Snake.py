import turtle
import time
import random

delay = 0.1
marcador = 0
marcador_alto = 0

#Pantalla
s = turtle.Screen()
s.setup(650,650)
s.title("Snake")
s.bgcolor("gray")

#Jugador
j = turtle.Turtle()
j.speed(1)
j.shape("square")
j.penup()
j.goto(0,0)
j.direction = 'stop'
j.color("green")
cuerpo = []

#comida
c = turtle.Turtle()
c.shape("circle")
c.color("orange")
c.penup()
c.goto(0,100)
c.speed(0)

#Marcador de puntaje
texto = turtle.Turtle()
texto.speed(0)
texto.color('black')
texto.penup()
texto.hideturtle()
texto.goto(0,-260)
texto.write("Score: 0 \nHigh Score: 0",align='center', font=("verdana", 24 , "normal"))

#Funciones de movimiento
def arriba():
    j.direction = 'up'

def abajo():
    j.direction = 'down'

def izquierda():
    j.direction = 'left'

def derecha():
    j.direction = 'right'

#Logica de movimiento
def movimiento():
    if j.direction == 'up':
        y = j.ycor()
        j.sety(y + 20)

    if j.direction == 'down':
        y = j.ycor()
        j.sety(y - 20)

    if j.direction == 'right':
        x = j.xcor()
        j.setx(x + 20)

    if j.direction == 'left':
        x = j.xcor()
        j.setx(x - 20)

#Entradas por teclado
s.listen()
s.onkeypress(arriba, 'Up')
s.onkeypress(abajo, 'Down')
s.onkeypress(izquierda, 'Left')
s.onkeypress(derecha, 'Right')

#Reinicia el juego al punto de partida
def reset():
        time.sleep(2)       
        for i in cuerpo:
            i.clear()
            i.hideturtle()
        j.home()
        j.direction = 'stop'
        cuerpo.clear()
        marcador = 0
        texto.clear()
        texto.write("Score: {} \nHigh Score: {} ".format(marcador,marcador_alto),align='center', font=("verdana", 24 , "normal"))    
pass

#Funcionamiento del juego
while True:
    s.update()

    #si tocamos el final del mapa
    if j.xcor() > 300 or j.xcor() < -300 or j.ycor() > 300 or j.ycor() < -300:
        reset()
        pass

    #cuando comemos    
    if j.distance(c) < 20:
       
        #generamos nueva comida
        x = random.randint(-250,250)
        y = random.randint(-250,250)
        c.goto(x,y)

        #alargamos el cuerpo de la serpiente
        nuevo_cuerpo = turtle.Turtle()
        nuevo_cuerpo.shape("square")
        nuevo_cuerpo.color("red")
        nuevo_cuerpo.penup()
        nuevo_cuerpo.goto(0,0)
        nuevo_cuerpo.speed(0)
        cuerpo.append(nuevo_cuerpo)
        
        #Actualizando puntajes
        marcador += 10
        if (marcador > marcador_alto):
            marcador_alto = marcador
        texto.clear()
        texto.write("Score: {} \nHigh Score: {} ".format(marcador,marcador_alto),align='center', font=("verdana", 24 , "normal"))

    #Hacer que el cuerpo siga la cabeza 
    total = len(cuerpo)
    for index in range(total-1,0,-1):
        x = cuerpo[index-1].xcor()
        y = cuerpo[index-1].ycor()
        cuerpo[index].goto(x,y)    
    if total > 0:
        x = j.xcor()
        y = j.ycor()
        cuerpo[0].goto(x,y)
        
    movimiento()
    #Si tocamos alguna parte de nuestro cuerpo
    for i in cuerpo:
        if i.distance(j) <20:
            reset()
    
    #Para que no se nos valla al pingo    
    #time.sleep(delay)

turtle.done()