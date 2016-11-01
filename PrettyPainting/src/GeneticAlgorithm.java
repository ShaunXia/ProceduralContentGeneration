import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm
{
	private static final int NUM_GENERATIONS = 100;
	static int POPULATION_SIZE = 12;
	private static int PERCENT_TO_TRIM = 30;
	private static ArrayList<Individual> individuals;

	public static void main(String[] args)
	{
		GeneticAlgorithm alg = new GeneticAlgorithm();
		alg.generatePopulation();
		for (int generation = 0; generation < NUM_GENERATIONS; generation++)
		{
			alg.makeNextGeneration();
		}

	}

	void makeNextGeneration()
	{
		trimPopulation();
		// pick a few individuals, copy them, mutate the copy and add it to the
		// population
		fillPopulation();

	}

	private void fillPopulation()
	{
		while (individuals.size() < POPULATION_SIZE)
		{
			if (Math.random() < 0.33)
			{
				if (Math.random() < 0.5)
				{
					Individual newGuy = new Individual(pickRandomIndividual());
					newGuy.mutate();
					individuals.add(newGuy);
				} else
				{
					Individual mom = pickRandomIndividual();
					Individual dad = pickRandomIndividual();
					while (mom == dad)
					{
						dad = pickRandomIndividual();
					}
					individuals.add(new Individual(pickRandomIndividual(),
							pickRandomIndividual()));
				}
			} else
			{
				individuals.add(new Individual());
			}
		}
		Collections.sort(individuals);
	}

	private Individual pickRandomIndividual()
	{
		int totalFitness = calculateTotalFitness();
		int target = (int) (Math.random() * totalFitness);
		int fitnessSoFar = individuals.get(0).getFitness();
		int currentIndividual = 0;
		while (fitnessSoFar < target)
		{
			currentIndividual++;
			fitnessSoFar = fitnessSoFar
					+ individuals.get(currentIndividual).getFitness();
		}
		return individuals.get(currentIndividual);
	}

	private int calculateTotalFitness()
	{
		int sum = 0;
		for (Individual i : individuals)
		{
			sum = sum + i.getFitness();
		}
		return sum;
	}

	private void trimPopulation()
	{
		int lastToKeep = (int) ((double) (100 - PERCENT_TO_TRIM) / 100 * POPULATION_SIZE);
		while (individuals.size() > lastToKeep)
		{
			individuals.remove(individuals.size() - 1);
		}
	}

	void generatePopulation()
	{
		individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATION_SIZE; i++)
		{
			individuals.add(new Individual());
		}
		Collections.sort(individuals);
		System.out.println(this);
	}

	public Individual getIndividual(int i)
	{
		return individuals.get(i);
	}

	public String toString()
	{
		String result = "";
		for (Individual x : individuals)
		{
			result = result + " " + x;
		}
		return result;
	}
}
