import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

}
