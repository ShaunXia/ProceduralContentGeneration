
Population population;
Button button;

void setup() {
  size(1000, 740);
  colorMode(RGB, 1.0);
  int popmax = 6;
  float mutationRate = 0.8;  // A pretty high mutation rate here, our population is rather small we need to enforce variety
  // Create a population with a target phrase, mutation rate, and population max
  population = new Population(mutationRate, popmax);
  // A simple button class
  button = new Button(400, 680, 160, 20, "evolve new generation");
}

void draw() {
  // Display the faces
    background(1.0);
  population.display();
  population.rollover(mouseX, mouseY);
  // Display some text
  textAlign(LEFT);
  fill(0);
  text("Generation #:" + population.getGenerations(), 400, 720);

  // Display the button
  button.display();
  button.rollover(mouseX, mouseY);
}

// If the button is clicked, evolve next generation
void mousePressed() {

  if (button.clicked(mouseX, mouseY)) {
    population.selection();
    population.reproduction();
  }
}

void mouseReleased() {
  button.released();
}