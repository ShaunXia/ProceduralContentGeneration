//import java.awt.Color;
//import java.awt.Rectangle;
//import java.awt.geom.Ellipse2D;
//import java.util.ArrayList;
//
//import processing.core.PApplet;
//
//public class Runner extends PApplet
//{
//
//	private static final int NUMBER_OF_COLUMNS = 4;
//
//	private static final int NUMBER_OF_ROWS = 3;
//
//	private static final int SPACE = 50;
//
////	private static final int SCREEN_WIDTH = NUMBER_OF_COLUMNS * Individual.INDIVIDUAL_WIDTH + NUMBER_OF_COLUMNS
////			* SPACE;
////	private static final int SCREEN_HEIGHT = NUMBER_OF_ROWS * Individual.INDIVIDUAL_HEIGHT
////			+ NUMBER_OF_ROWS * SPACE;
//	
//	private static final int SCREEN_WIDTH =800;
//	private static final int SCREEN_HEIGHT = 800;
//	GeneticAlgorithm alg;
//	private int clickCount = 0;
//
//	public void setup()
//	{
//		alg = new GeneticAlgorithm();
//		alg.generatePopulation();
//		//GAInConsole gac = new GAInConsole();
//		//gac.storeIndividual();
//		//gac.getIndividual();
//		
//	}
//
//	public void draw()
//	{
//		drawArt();
//		if (clickCount >4)
//		{
//			alg.makeNextGeneration();
//			clickCount = 0;
//		}
//	}
//
//	public void mouseClicked()
//	{
//		System.out.println("click");
//		clickCount = clickCount + 1;
//		int picRow = mouseY / Individual.INDIVIDUAL_HEIGHT;
//		int picColumn = mouseX / Individual.INDIVIDUAL_WIDTH;
//		int imageNumber = picRow*NUMBER_OF_COLUMNS + picColumn;
//		System.out.println(imageNumber);
//		alg.increaseFitness(imageNumber);
//	}
//	public void settings()
//	{
//		size(SCREEN_WIDTH, SCREEN_HEIGHT);
//
//	}
//
//	private void drawArt()
//	{
//		background(0xCCFFFF);
//		for (int i = 0; i < GeneticAlgorithm.POPULATION_SIZE; i++)
//		{
//			Individual x = alg.getIndividual(i);
//
//			int row = i / 4;
//			int column = i % 4;
//			//drawIndividual(x, row, column);
//			drawIndividual();
//		}
//		try
//		{
//			Thread.sleep(1000);
//		} catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
//		// System.exit(1);
//	}
//
//	private void drawIndividual(Individual x, int row, int column)
//	{
//		int xOffset = column * (Individual.INDIVIDUAL_WIDTH + SPACE);
//		int yOffset = row * (Individual.INDIVIDUAL_HEIGHT + SPACE);
//		drawABox(xOffset, yOffset, Individual.INDIVIDUAL_WIDTH,
//				Individual.INDIVIDUAL_HEIGHT, Color.WHITE);
//		
//		ArrayList<ColoredShape> things = x.getShapes();
//
//		for (ColoredShape coloredShape : things)
//		{
//			if (coloredShape.getShape().getClass() == java.awt.Rectangle.class)
//			{
//				Rectangle s = (Rectangle) coloredShape.getShape();
//				drawABox(xOffset + s.x, yOffset + s.y, s.width, s.height,
//						coloredShape.getColor());
//			}
//			
//			if (coloredShape.getShape().getClass() == java.awt.geom.Ellipse2D.Float.class)
//			{
//				Ellipse2D.Float s = (Ellipse2D.Float) coloredShape.getShape();
//				drawAnOval(xOffset + s.x + s.width / 2, yOffset + s.y
//						+ s.height / 2, s.width, s.height,
//						coloredShape.getColor());
//			}
//		}
////		
////		save("1.jpg");
//
//	}
//	
//	
//	private void drawIndividual()
//	{
//		drawStrips();
//
//	}
//	
//	
//	private void drawABox(float x, float y, float boxWidth, float boxHeight,
//			Color color)
//	{
//		pushMatrix();
//		fill(color.getRed(), color.getGreen(), color.getBlue(), 128);
//		rect(x, y, boxWidth, boxHeight);
//		popMatrix();
//	}
//
//	private void drawAnOval(float x, float y, float ovalWidth,
//			float ovalHeight, Color color)
//	{
//		pushMatrix();
//		fill(color.getRed(), color.getGreen(), color.getBlue(), 128);
//		ellipse(x, y, ovalWidth, ovalHeight);
//		popMatrix();
//	}
//	
//	private void drawStrips()
//	{
//		int sw = 2;           
//		int sz = 800;
//		float diam = (float) (600/1.8);
//		float start;
//		int iter = 8;
//		float step = diam/iter;
//		int [] ch = { -1, 1 };
//		int wdt = 65;
//		
//		 start = diam + diam/2; 
//
//		  float H = random(0, 100);
//		  float S = random(90, 100);
//		  float B = 10;
//
//		  for (int i = 0; i<iter; ++i) {
//		    pushMatrix();
//		    translate(0, width/2);
//		    rotate(radians(random(7, 22)* ch[(int)random(ch.length)]));
//		    fill (H, S-i*(100/iter), B+i*((100-8)/iter));
//		    rect(0, 0, sz, wdt);
//		    start-= step - random(-step/2, step/2);
//		    popMatrix();
//		    
//		  }
//
//		  stroke(10, 3, 90);
//		  noFill();
//		  strokeWeight(1024);
//		  //ellipse(width/2-7, height/2, (diam+sz/2), (diam+sz/2));
//		//rect(width/2-7, height/2, (diam+sz/2), (diam+sz/2));
//		  paper(10);  // browser value
//		 // paper(30);  // normal value
//	}
//	private void paper(int in_val) {
//		  noStroke();
//		  for (int i = 0; i<width-1; i+=2) {
//		    for (int j = 0; j<height-1; j+=2) {
//		      fill(random(85-10, 85+10), in_val);
//		      rect(i, j, 2, 2);
//		    }
//		  }
//
//		  for (int i = 0; i<30; i++) {
//		    fill(random(40, 60), random(in_val*2.5f, in_val*3.0f));
//		    rect(random(0, width-2), random(0, height-2), random(1, 3), random(1, 3));
//		  }
//		  save("2.jpg");
//		}
//
//	
//	
//	public static void main(String args[])
//	{
//
//		rect(0,0,10,20);
//		//PApplet.main(new String[]
//		//{ "Runner" });
//	}
//}
