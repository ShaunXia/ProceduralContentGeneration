import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Individual implements Comparable<Individual>
{
	static final int INDIVIDUAL_WIDTH = 300;
	static final int INDIVIDUAL_HEIGHT = 300;

	private ArrayList<ColoredShape> shapes;
	private int fitness;

	/**
	 * Create a random individual
	 */
	public Individual()
	{
		int numShapes = (int) (Math.random() * 25 + 1);
		shapes = new ArrayList<ColoredShape>();
		for (int i = 0; i < numShapes; i++)
		{
			shapes.add(createRandomShape());
		}
		fitness = calculateFitness();
	}

	private int calculateFitness()
	{
		return (int) (Math.random() * 100);
	}

	private ColoredShape createRandomShape()
	{
		if (Math.random() < .75)
		{
			int left = (int) (Math.random() * INDIVIDUAL_WIDTH);
			int top = (int) (Math.random() * INDIVIDUAL_HEIGHT);
			int width = (int) (Math.random() * (INDIVIDUAL_WIDTH - left) + 1);
			int height = (int) (Math.random() * (INDIVIDUAL_HEIGHT - top) + 1);
			return new ColoredShape(getRandomColor(), new Rectangle(left, top,
					width, height));
		} else
		{
			int left = (int) (Math.random() * INDIVIDUAL_WIDTH);
			int top = (int) (Math.random() * INDIVIDUAL_HEIGHT);
			int width = (int) (Math.random() * (INDIVIDUAL_WIDTH - left) + 1);
			int height = (int) (Math.random() * (INDIVIDUAL_HEIGHT - top) + 1);
			return new ColoredShape(getRandomColor(), new Ellipse2D.Float(left,
					top, width, height));
		}
	}

	private Color getRandomColor()
	{
		return new Color((int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	public int getNumberOfShapes()
	{
		return shapes.size();
	}

	public String toString()
	{
		return "" + fitness;
	}

	public ArrayList<ColoredShape> getShapes()
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
		} else if (fitness == arg0.fitness)
		{
			return 0;
		} else
		{
			return -1;
		}

	}

}
