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
//		for (int i = 0; i < numShapes; i++)
//		{
//			shapes.add(createRandomShape());
//		}
		createRandomShapePattern(INDIVIDUAL_WIDTH/2,INDIVIDUAL_WIDTH/2,(int)(INDIVIDUAL_WIDTH/2.5),(int) (Math.random() * 3)+1);
		fitness = calculateFitness();
	}

	public Individual(Individual guyToCopy)
	{
		shapes = new ArrayList<ColoredShape>(guyToCopy.shapes.size());
		for(ColoredShape s: guyToCopy.shapes) {
		    shapes.add( new ColoredShape(s));
		}
		fitness = calculateFitness();
	}
	public Individual(Individual mom, Individual dad)
	{
		int shapesFromMom = mom.shapes.size()/2;
		int shapesFromDad = dad.shapes.size()/2;
		shapes = new ArrayList<ColoredShape>(shapesFromMom + shapesFromDad);
		for (int i=0;i<shapesFromMom;i++)
		{
			shapes.add(mom.getShapes().get(i));
		}
		for (int i=shapesFromDad; i<dad.shapes.size();i++)
		{
			shapes.add(dad.getShapes().get(i));
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
	
	// To generate pattern.
	private ColoredShape createRandomShapePattern(int x,int y, int radius,int num)
	{
		
		
		
	    int new_x, new_y;
	    int new_num;
		 if (num > 0) {
		      new_num = num-1;
		      new_x = x;
		      new_y = y-radius/2;
		      shapes.add(createRandomShapePattern(new_x, new_y, (int)(radius/2), new_num));
		      new_x = x +radius/2;
		      new_y = y ;
		      shapes.add(createRandomShapePattern(new_x, new_y, (int)(radius/2), new_num));
		      new_x = x;
		      new_y = y +radius/2;
		      shapes.add(createRandomShapePattern(new_x, new_y, (int)(radius/2), new_num));
		      new_x = x-radius/2;
		      new_y = y;
		      shapes.add(createRandomShapePattern(new_x, new_y, (int)(radius/2), new_num));
		    }

		if (Math.random() < .5)
		{
			return new ColoredShape(getRandomColor(), new Rectangle(x, y,
					radius, radius));
		} else
		{
			return new ColoredShape(getRandomColor(), new Ellipse2D.Float(x,
					y, radius, radius));
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

	public void mutate()
	{
		if (Math.random() >= 0.8)
		{
			int number = (int)(Math.random() * shapes.size());
			ColoredShape s = shapes.get(number);
			double optionPicker = Math.random();
			if (optionPicker<0.5)
			{
				s.setColor(getRandomColor());
			} 
			else
			{
				shapes.remove(number);
			}
		} else
		{
			shapes.add(createRandomShape());
		}
		fitness = calculateFitness();
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
