import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;


public class TestIndividual
{
	
	@Test
	public void randomConstructor()
	{
		Individual x = new Individual();
		int howMany = x.getNumberOfShapes();
		ArrayList<ColoredShape> shapes = x.getShapes();
		assertTrue(howMany>=1);
		assertEquals(howMany, shapes.size());
		for (int i=0;i<howMany;i++)
		{
			assertNotNull(shapes.get(i));
		}
	}

	@Test
	public void copyConstructor()
	{
		Individual x = new Individual();
		Individual y = new Individual(x);
		assertEquals(x.getShapes().size(), y.getShapes().size());
		ColoredShape x1 = x.getShapes().get(1);
		ColoredShape y1 = y.getShapes().get(1);
		assertEquals(x1,y1);
		assertNotSame(x1,y1);
	}
}
