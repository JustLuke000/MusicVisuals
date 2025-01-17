# Music Visualiser Project
Name: Luke O'Shea Scanlan

Student Number: C19300696

### Description of the assignment
Visuals of which respond to the amplitude of the inputted music.

### Instructions
All the user has to do is Compile and Run the file, 
the program will begin playing the music and the 
appropriate visual effects 

### How it works
The program takes samples from the mp3 file, from which we Map()
the amplitude to give us a relative colour, stroke and/or size for example:

```Java
PApplet.map(mv.getSmoothedAmplitude(), 1, 0, 255, 0), 255, 255)
```

This means that an amplitude of X-Meters is X-Colour

### Youtube video:
[![Youtube](https://i9.ytimg.com/vi_webp/ZzKDqYMn7YA/mqdefault.webp?time=1620151200000&sqp=CKCXxoQG&rs=AOn4CLDiP2J0Xaf86y3h7uyB9qjBsDtzuQ)](https://youtu.be/ZzKDqYMn7YA)

### What I am most proud of in the assignment
I am seriously happy with my loading screen/bar, 
although it is just theater, it called for a considerable 
amount of trial and error. I was eventually able to complete it 
with the help of the Bullet Class from our YASC program earlier 
in the year.

#### Prototypes (Honourable mentions)
Theses are some of the prototypes I worked with but didn't end up including:

![An image](images/comet.png)

![An image](images/nova.png)

![An image](images/boxes.png)

![An image](images/colouredsphere.png)

It seems that my repository was also cloned which is a shame:
![An image](images/cloned.png)

#Popular
![An image](images/popular.png)

### Checklist
- [x] Fork this repository and use it a starter project for your assignment
- [x] Create a new package named your student number and put all your code in this package.
- [x] You should start by creating a subclass of ie.tudublin.Visual
- [x] There is an example visualiser called MyVisual in the example package Check out 
the WaveForm and AudioBandsVisual for examples of how to call the Processing functions 
from other classes that are not subclasses of PApplet
- [ ] Enjoy Your Summer