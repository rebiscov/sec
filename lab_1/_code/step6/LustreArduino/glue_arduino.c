//Interfacing with arduino led + 7segment

#include "glue_arduino.h"

int led = 13; // Synchronized with the shield led
int led_on = 1; // led state
int button = 10; //button
int button_state= 0;

// TODO : add some more global variables

//Global variables for 7 segment
int a = 1;  //For displaying segment "a"
int b = 2;  //For displaying segment "b"
int c = 3;  //For displaying segment "c"
int d = 4;  //For displaying segment "d"
int e = 5;  //For displaying segment "e"
int f = 6;  //For displaying segment "f"
int g = 7;  //For displaying segment "g"

int counter = 0;


void turnOff() //turn off the 7seg (CC)
{
  int i;
  for (i = a; i <= g; i++){ // this could be prettier
    digitalWrite(i,HIGH); // change into HIGH for common anode
  }
}


void setup() {
  //Setup for LED on pin
  pinMode(led, OUTPUT);

  led_on=1;//true

  //TODO implement the rest !

  for (int i = a; i <= g; i++)
    pinMode(i, OUTPUT);

  pinMode(button, INPUT);

  return;
}

void displayDigit(int digit)
{
  turnOff();
  if(digit!=1 && digit != 4)
    digitalWrite(1,LOW);
  if(digit != 5 && digit != 6)
    digitalWrite(2,LOW);
  if(digit !=2)
    digitalWrite(3,LOW);
  if(digit != 1 && digit !=4 && digit !=7)
    digitalWrite(4,LOW);
  if(digit == 2 || digit ==6 || digit == 8 || digit==0)
    digitalWrite(5,LOW);
  if(digit != 1 && digit !=2 && digit!=3 && digit !=7)
    digitalWrite(6,LOW);
  if (digit!=0 && digit!=1 && digit !=7)
    digitalWrite(7,LOW);

  return; // TODO!
}
