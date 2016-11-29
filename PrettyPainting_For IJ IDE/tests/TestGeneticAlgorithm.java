import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestGeneticAlgorithm
{
	
	@Test
	public void testInitialization()
	{
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.generatePopulation();
		assertTrue(ga.getIndividual(0).getFitness() >= ga.getIndividual(1).getFitness());
	}

}
