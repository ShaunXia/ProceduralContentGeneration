import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Individual implements Comparable<Individual>, Serializable
{
	static final int INDIVIDUAL_WIDTH = 800;
	static final int INDIVIDUAL_HEIGHT = 800;
	
	
	private ArrayList<ColoredShape> shapes;
	private int fitness;
	public int DNA_length = 50;
	public int DNA_num = 7;
	public float[][] DNA=new float[DNA_num][DNA_length];
	public String imgURL="";
	public int randomCCount=0;
	/**
	 * Create a random individual
	 */
	public Individual()
	{
		//shapes = new ArrayList<ColoredShape>();

//		if (Math.random() < 0.5)
		


			for (int i=0;i<DNA_num;++i)
				for (int j=0;j<DNA_length;++j)
				{
					DNA[i][j]= (float) Math.random();
				}




		fitness = 0;
		//DrawIndividual();
	}

	public Individual(Individual guyToCopy)
	{
//		shapes = new ArrayList<ColoredShape>(guyToCopy.shapes.size());
//		for (ColoredShape s : guyToCopy.shapes)
//		{
//			shapes.add(new ColoredShape(s));
//		}

		DNA = guyToCopy.DNA.clone();
		fitness = 0;
	}

	public Individual(Individual mom, Individual dad)
	{
//		int shapesFromMom = mom.shapes.size() / 2;
//		int shapesFromDad = dad.shapes.size() / 2;
//
//		shapes = new ArrayList<ColoredShape>(shapesFromMom + shapesFromDad);
//		for (int i = 0; i < shapesFromMom; i++)
//		{
//			shapes.add(mom.getShapes().get(i));
//		}
//		for (int i = shapesFromDad; i < dad.shapes.size(); i++)
//		{
//			shapes.add(dad.getShapes().get(i));
//		}

		int flag=0;
		for (int i=0;i<DNA_num;++i)
			for (int j =0;j<DNA_length;j=j+2)
			{
				if (flag==0) {
					DNA[i][j] = mom.DNA[i][j];
					DNA[i][j + 1] = mom.DNA[i][j + 1];
					flag=1;
				}else
				{
					DNA[i][j] = dad.DNA[i][j];
					DNA[i][j + 1] = dad.DNA[i][j + 1];
					flag=0;
				}

			}

		fitness = 0;
	}

	public Individual(float[][] inDNA,int vote)
	{
		DNA = inDNA.clone();
		fitness = vote;
	}





	
	private Color getRandomColor()
	{
		randomCCount++;
		if (randomCCount+4>=DNA_length)
			randomCCount=0;
		return new Color((int) (DNA[4][randomCCount+1]* 255),
				(int) (DNA[4][randomCCount+2]* 255), (int) (DNA[4][randomCCount+3] * 255),(int) (DNA[4][randomCCount+4] * 255));

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
		// Mutate for Colorshap
//		if (Math.random() >= 0.8)
//		{
//			int number = (int) (Math.random() * shapes.size());
//			ColoredShape s = shapes.get(number);
//			double optionPicker = Math.random();
//			if (optionPicker < 0.5)
//			{
//				s.setColor(getRandomColor());
//			} else
//			{
//				shapes.remove(number);
//			}
//		} else
//		{
//			shapes.add(createRandomShape());
//		}


		// Mutate for DNA


		for (int i=0;i<DNA_num;++i){
			for (int j=0;j<DNA_length;++j)
			{
				if (Math.random()>0.6)
				{
					DNA[i][j]=(float)(Math.random());
				}
				if (Math.random()>0.6)
				{
					DNA[i][j]=(float)(Math.random());
				}

			}
		}



		fitness = 0;
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

	public void increaseFitness()
	{
		fitness = fitness+1;
	}


	public void DrawIndividual()
	{
//		drawStrips();
		drawOriginal();

	}

	public void drawStrips()
	{
		BufferedImage bImg = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = (Graphics2D) g;    // to windows
		Graphics2D g2d = bImg.createGraphics();  // to image
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		float H = (int)(DNA[0][0]*200+40);
		float S = (int)(DNA[0][1]*20+220);
		float B = 10;
		float start=600;
		float step =(float)(800/1.8/8);

		for (int i = 0; i < 8; ++i) {
			AffineTransform transform = new AffineTransform();
			AffineTransform old_mat = g2d.getTransform();
			g2d.transform(transform);

			float tS = S-i*(240/7);
			if (tS<0)
				tS=0;
			float tB = B + i * (240/7);
			if (tB>=230)
				tB=240;

			int rgb = Color.HSBtoRGB((float)(H/240.0),(float)(tS/240.0), (float)(tB/240.0));


			g2d.setColor(new Color(rgb));
			Rectangle rect2 = new Rectangle(50, (int)(start-30), 700, 60);
			int direction;
			if (DNA[1][i]>0.5)
				direction= -1;
			else
				direction =1;
			int rad = ((int)(DNA[2][i] * 15)+7)* direction;

			g2d.rotate(Math.toRadians(rad), rect2.getX() + rect2.width / 2,
					rect2.getY() + rect2.height / 2);
			g2d.draw(rect2);
			g2d.fill(rect2);
			g2d.setTransform(old_mat);

			start -=step - (DNA[3][i]*(step/2)+(step/-2));

		}

		g2d.setStroke(new BasicStroke(255));
		Ellipse2D ellipse = new Ellipse2D.Double(-50,-55,900,900);
		g2d.draw(ellipse);


		// Draw Background
		for (int i = 0; i < 800 - 1; i += 2) {
			for (int j = 0; j < 800 - 1; j += 2) {
				int gray = (int) (Math.random() * 20) + 75;
				Color ccolor = new Color(gray, gray, gray, 30);
				g2d.setColor(ccolor);
				g2d.fillRect(i, j, 2, 2);
			}
		}

		//Save image
		//imgURL=inimgURL;
		try {
			ImageIO.write(bImg,"PNG",new File(imgURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawStripsCom(Graphics2D g2d)
	{

		float H = (int)(DNA[0][0]*200+40);
		float S = (int)(DNA[0][1]*20+220);
		float B = 10;
		float start=600;
		float step =(float)(800/1.8/8);

		for (int i = 0; i < 8; ++i) {
			AffineTransform transform = new AffineTransform();
			AffineTransform old_mat = g2d.getTransform();
			g2d.transform(transform);

			float tS = S-i*(240/7);
			if (tS<0)
				tS=0;
			float tB = B + i * (240/7);
			if (tB>=230)
				tB=240;

			int rgb = Color.HSBtoRGB((float)(H/240.0),(float)(tS/240.0), (float)(tB/240.0));


			g2d.setColor(new Color(rgb));
			Rectangle rect2 = new Rectangle(50, (int)(start-30), 700, 60);
			int direction;
			if (DNA[1][i]>0.5)
				direction= -1;
			else
				direction =1;
			int rad = ((int)(DNA[2][i] * 15)+7)* direction;

			g2d.rotate(Math.toRadians(rad), rect2.getX() + rect2.width / 2,
					rect2.getY() + rect2.height / 2);
			g2d.draw(rect2);
			g2d.fill(rect2);
			g2d.setTransform(old_mat);

			start -=step - (DNA[3][i]*(step/2)+(step/-2));

		}

		g2d.setStroke(new BasicStroke(255));
		Ellipse2D ellipse = new Ellipse2D.Double(-50,-55,900,900);
		g2d.draw(ellipse);


		// Draw Background
		for (int i = 0; i < 800 - 1; i += 2) {
			for (int j = 0; j < 800 - 1; j += 2) {
				int gray = (int) (Math.random() * 20) + 75;
				Color ccolor = new Color(gray, gray, gray, 30);
				g2d.setColor(ccolor);
				g2d.fillRect(i, j, 2, 2);
			}
		}


	}

	public void drawOriginal()
	{
		BufferedImage bImg = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = (Graphics2D) g;    // to windows
		Graphics2D g2d = bImg.createGraphics();  // to image
		g2d.setColor(new Color(204,255,255));

		g2d.fillRect(0, 0, 800, 800);
		//g2d.setBackground(Color.lightGray);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int numShapes = (int) (DNA[0][0] * 25 + 1);


			if (DNA[0][1]>0.5) {
				for (int i = 0; i < numShapes; i++)
				{
					createRandomShape(g2d,i+3);
				}
			}
			else {

				for (int i = 0; i < numShapes/2; i++)
				{
					if (DNA[0][i]>0.5)
						createRandomShape(g2d,i+3);
				}

				//fitness=10;
				createRandomShapePattern(g2d,1,INDIVIDUAL_WIDTH / 2, INDIVIDUAL_WIDTH / 2, (int) (INDIVIDUAL_WIDTH / 2.5), (int) (DNA[0][2] * 3)+1);
			}
			//else
			//	drawStripsCom(g2d);



		//Save image
		//imgURL=imgURL;
		try {
			ImageIO.write(bImg,"PNG",new File(imgURL));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	private void createRandomShape(Graphics2D g2d,int index)
	{
//		if (Math.random() < .75)
//		{
//			int left = (int) (Math.random() * INDIVIDUAL_WIDTH);
//			int top = (int) (Math.random() * INDIVIDUAL_HEIGHT);
//			int width = (int) (Math.random() * (INDIVIDUAL_WIDTH - left) + 1);
//			int height = (int) (Math.random() * (INDIVIDUAL_HEIGHT - top) + 1);
//			return new ColoredShape(getRandomColor(), new Rectangle(left, top,
//					width, height));
//		} else
//		{
//			int left = (int) (Math.random() * INDIVIDUAL_WIDTH);
//			int top = (int) (Math.random() * INDIVIDUAL_HEIGHT);
//			int width = (int) (Math.random() * (INDIVIDUAL_WIDTH - left) + 1);
//			int height = (int) (Math.random() * (INDIVIDUAL_HEIGHT - top) + 1);
//			return new ColoredShape(getRandomColor(), new Ellipse2D.Float(left,
//					top, width, height));
//		}


		if (DNA[0][index] < .75)
		{
			int left = (int) (DNA[1][index]* INDIVIDUAL_WIDTH);
			int top = (int) (DNA[1][index+1] * INDIVIDUAL_HEIGHT);
			int width = (int) (DNA[1][index+2]* (INDIVIDUAL_WIDTH - left) + 1);
			int height = (int) (DNA[1][index+3] * (INDIVIDUAL_HEIGHT - top) + 1);
			g2d.setColor(getRandomColor());
			Rectangle rect = new Rectangle(left, top, width, height);
			g2d.draw(rect);
			g2d.fill(rect);
			//return new ColoredShape(getRandomColor(), );
		} else
		{
			int left = (int) (DNA[2][index] * INDIVIDUAL_WIDTH);
			int top = (int) (DNA[2][index+1] * INDIVIDUAL_HEIGHT);
			int width = (int) (DNA[2][index+2] * (INDIVIDUAL_WIDTH - left) + 1);
			int height = (int) (DNA[2][index+3] * (INDIVIDUAL_HEIGHT - top) + 1);
			g2d.setColor(getRandomColor());
			Ellipse2D ellipse = new Ellipse2D.Float(left, top, width, height);
			g2d.draw(ellipse);
			g2d.fill(ellipse);
			//return new ColoredShape(getRandomColor(), );
		}

	}

	// To generate pattern.
	private void createRandomShapePattern(Graphics2D g2d,int index, int x, int y, int radius,int num)
	{

		int new_x, new_y;
		int new_num;
		if (num > 0)
		{
			new_num = num - 1;
			new_x = x;
			new_y = y - radius / 2;
			createRandomShapePattern(g2d, index++,new_x, new_y,
					(int) (radius / 2), new_num);
			new_x = x + radius / 2;
			new_y = y;
			createRandomShapePattern(g2d, index++,new_x, new_y,
					(int) (radius / 2), new_num);
			new_x = x;
			new_y = y + radius / 2;
			createRandomShapePattern(g2d, index++,new_x, new_y,
					(int) (radius / 2), new_num);
			new_x = x - radius / 2;
			new_y = y;
			createRandomShapePattern(g2d, index++,new_x, new_y,
					(int) (radius / 2), new_num);
		}

		if (DNA[3][index++] < .5)
		{
			g2d.setColor(getRandomColor());
//			g2d.setStroke(new BasicStroke(6));
			Rectangle rect = new Rectangle(x, y, radius, radius);
			g2d.draw(rect);
			g2d.fill(rect);
//			return );
		} else
		{
			g2d.setColor(getRandomColor());
//			g2d.setStroke(new BasicStroke(4));
			Ellipse2D ellipse = new Ellipse2D.Float(x, y, radius, radius);
			g2d.draw(ellipse);
			g2d.fill(ellipse);
//			g2d.draw(ellipse);
//			return new ColoredShape(getRandomColor(), new Ellipse2D.Float(x, y,
//					radius, radius));
		}
		//index++;
	}


	public void setImgURL(String inURL)
	{
		imgURL = inURL;
	}
}
