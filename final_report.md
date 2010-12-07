# Team Edward Final Project Report #
<small>Steven! Lorenzen, Andrew Nguyen, Vien Nguyen</small>

## Includes ##
- UML Class Diagram
- UML Sequence Diagram
- Use Cases
- Questions 1 - 4

## Questions ##

### 1: Which materials from this course did you apply on the project? ###
For this project we used Java inheritance and interface implementation for our `MancalaGUI` class, Java inheritance for our `Board` class, Java abstract class definition and implementation for our `BoardLayout` `ClassicLayout` and `OMGPoniesLayout`. As well as anonymous classes and inheritance in `MDialog` and basics of the Swing and awt graphics libraries.

### 2: Which materials from prerequisite courses did you apply to the project?  ###
We used Java fundamentals such as class and method definition as well as elementary data structures such as multidimensional arrays throughout 

### 3: Which topics did you have to learn through self-study in order to complete the project?  ###
Advanced Swing and awt library foundation classes such as `JDialog`, `Image` and `ImageIO`. We also took advantage of the source code management (SCM) tool Mercurial and bitbucket.org project hosting as well as use of the Inkscape vector graphics program.

### 4: Which key concepts of this course did you use in conducting this project?  ###
This project made heavy use of the MVC pattern. Our data model was contained in the `Mancala` class, we used the `Board` class as our view to display the game state and the `MancalaGUI` class handled interaction between them. Thus the Model and View were completely orthogonal and had no dependency between them. We also used the Strategy pattern with our abstract class `BoardLayout` and its two instantiations `ClassicLayout` and `OMGPoniesLayout` to provide slightly different looks for our `Board`.

