import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import processing.core.PApplet;

public class Runner extends PApplet
{

	private static final int SPACE = 50;

	private static final int SCREEN_WIDTH = 3 * Individual.INDIVIDUAL_WIDTH + 3
			* SPACE;
	private static final int SCREEN_HEIGHT = 3 * Individual.INDIVIDUAL_HEIGHT
			+ 3 * SPACE;
	GeneticAlgorithm alg;

	public void setup()
	{
		alg = new GeneticAlgorithm();
		alg.generatePopulation();
	}

	public void draw()
	{
		drawArt();
		alg.makeNextGeneration();
	}

	public void settings()
	{
		size(SCREEN_WIDTH, SCREEN_HEIGHT);

	}

	private void drawArt()
	{
		background(0xFFFFFF);
		for (int i = 0; i < GeneticAlgorithm.POPULATION_SIZE; i++)
		{
			Individual x = alg.getIndividual(i);

			int row = i / 3;
			int column = i % 3;
			drawIndividual(x, row, column);
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// System.exit(1);
	}

	private void drawIndividual(Individual x, int row, int column)
	{
		int xOffset = column * (Individual.INDIVIDUAL_WIDTH + SPACE);
		int yOffset = row * (Individual.INDIVIDUAL_HEIGHT + SPACE);
		drawABox(xOffset, yOffset, Individual.INDIVIDUAL_WIDTH,
				Individual.INDIVIDUAL_HEIGHT, Color.WHITE);
		
		ArrayList<ColoredShape> things = x.getShapes();

		for (ColoredShape coloredShape : things)
		{
			if (coloredShape.getShape().getClass() == java.awt.Rectangle.class)
			{
				Rectangle s = (Rectangle) coloredShape.getShape();
				drawABox(xOffset + s.x, yOffset + s.y, s.width, s.height,
						coloredShape.getColor());
			}
			
			if (coloredShape.getShape().getClass() == java.awt.geom.Ellipse2D.Float.class)
			{
				Ellipse2D.Float s = (Ellipse2D.Float) coloredShape.getShape();
				drawAnOval(xOffset + s.x + s.width / 2, yOffset + s.y
						+ s.height / 2, s.width, s.height,
						coloredShape.getColor());
			}
		}

	}

	private void drawABox(float x, float y, float boxWidth, float boxHeight,
			Color color)
	{
		pushMatrix();
		fill(color.getRed(), color.getGreen(), color.getBlue(), 128);
		rect(x, y, boxWidth, boxHeight);
		popMatrix();
	}

	private void drawAnOval(float x, float y, float ovalWidth,
			float ovalHeight, Color color)
	{
		pushMatrix();
		fill(color.getRed(), color.getGreen(), color.getBlue(), 128);
		ellipse(x, y, ovalWidth, ovalHeight);
		popMatrix();
	}

	public static void main(String args[])
	{
		PApplet.main(new String[]
		{ "Runner" });
	}
}
