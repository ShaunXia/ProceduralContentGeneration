
// The class for our image, contains DNA sequence, fitness value, position on screen

// Fitness Function f(t) = t (where t is "time" mouse rolls over image)

class Phenotype {

  DNA dna;          // Get DNA
  float fitness;    // fitness value
  float x, y;       // Position on screen
  int wh = 300;      // size of each image that  generate.
  boolean rolloverOn; // Are we rolling over this image?

  Rectangle r;

  // Create a new face
  Phenotype(DNA dna_, float x_, float y_) {
    dna = dna_;
    x = x_; 
    y = y_;
    fitness = 1;
    // Using java.awt.Rectangle (see: http://java.sun.com/j2se/1.4.2/docs/api/java/awt/Rectangle.html)
    r = new Rectangle(int(x), int(y), int(wh), int(wh));
  }

  // Display the face
  void display() {

    colorMode(RGB, 255);  // because this project set colorMode as 0 to 1 , so I change back to 0-255, since pattern use 0-255 mode
    // when I rewrite this code, I will fix this.

    pushMatrix();
    translate(x, y);  //since we have 6 image, this translate real x,y in screen to 0,0 make sure we can show images in one window.  
    noStroke();

    //ColorSquares sq=new ColorSquares(dna); // pass dna to pattern class and draw random image from the dna.
    //sq.display();  // display image.
    
    PatternSquareCircle sqc = new PatternSquareCircle(dna);
    sqc.display();
    
    colorMode(RGB, 1.0);  // change back to 0 - 1, since execpt the image, other code are use 0-1 mode.
    // when I rewrite this code, I will fix this.

    // Draw the bounding box
    stroke(0.25);
    if (rolloverOn) fill(0, 0.25);
    else noFill();
    rectMode(CORNER);
    rect(0, 0, wh, wh);
    popMatrix();

    // Display fitness value
    textAlign(CENTER);
    if (rolloverOn) fill(0);
    else fill(0.25);
    text(int(fitness), x+(int)wh/2, y+(int)wh+16);
  }

  float getFitness() {
    return fitness;
  }

  DNA getDNA() {
    return dna;
  }

  // Increment fitness if mouse is rolling over an image.
  void rollover(int mx, int my) {
    if (r.contains(mx, my)) {
      rolloverOn = true;
      fitness += 0.25;
    } else {
      rolloverOn = false;
    }
  }
}