import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



public class Individual implements Comparable<Individual>
{

	private static int SCREEN_WIDTH = 1024;
	private static int SCREEN_HEIGHT = 760;
	
	private ArrayList<Shape> shapes;
	private int fitness;
	
	/**
	 * Create a random individual
	 */
	public Individual()
	{
		int numShapes = (int)(Math.random()*25 + 1);
		shapes = new ArrayList<Shape>();
		for (int i=0; i<numShapes; i++)
		{
			shapes.add(createRandomShape());
		}
		fitness = calculateFitness();
	}

	private int calculateFitness()
	{
		return (int)(Math.random()*100);
	}

	private Shape createRandomShape()
	{
		if (Math.random() < .75)
		{
			int left = (int)(Math.random()*SCREEN_WIDTH);
			int top = (int) (Math.random()*SCREEN_HEIGHT);
			int width = (int) (Math.random()*(SCREEN_WIDTH-left)+1);
			int height = (int) (Math.random()*(SCREEN_HEIGHT-top)+1);
			return new Rectangle(left, top, width, height);
		} else
		{
			int left = (int)(Math.random()*SCREEN_WIDTH);
			int top = (int) (Math.random()*SCREEN_HEIGHT);
			int width = (int) (Math.random()*(SCREEN_WIDTH-left)+1);
			int height = (int) (Math.random()*(SCREEN_HEIGHT-top)+1);
			return new Ellipse2D.Float(left, top, width, height);
		}
	}

	public int getNumberOfShapes()
	{
		return shapes.size();
	}

	public String toString()
	{
		return ""+fitness;
	}
	public ArrayList<Shape> getShapes()
	{
		return shapes;
	}

	public int getFitness()
	{
		return fitness;
	}

	@Override
	public int compareTo(Individual arg0)
	{
		if (fitness < arg0.fitness)
		{
			return 1;
		}
		else if (fitness == arg0.fitness)
		{
			return 0;
		} else
		{
			return -1;
		}
			
	}

}
