import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.experimental.theories.Theories;

public class GeneticAlgorithm extends JFrame {

	public static void main(String[] args) {
		GeneticAlgorithm alg = new GeneticAlgorithm();
		//alg.setSize(800, 800);
	//	alg.setVisible(true);
        int firstRun = 1;
        if (firstRun==1)
		    alg.generatePopulation();
        else
            //get individual from database

		for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
			alg.makeNextGeneration();
		}

	}
    static final String WRITE_OBJECT_SQL = "INSERT INTO genimage(idv_value) VALUES (?)";
    static final String READ_OBJECT_SQL = "SELECT * FROM genimage WHERE isshow=1";

	private static final int NUM_GENERATIONS = 100;
	static int POPULATION_SIZE = 12;
	private static int PERCENT_TO_TRIM = 30;
	private static ArrayList<Individual> individuals;
    private int currentGeneration=0;

	public GeneticAlgorithm() {
//		JPanel jPanel = new JPanel();
//		add(jPanel);
//		NowDraw nowDraw = new NowDraw();
//		add(nowDraw);
	}

	class NowDraw extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);



		}
	}


	public void  storeCurrentGenToDatabase()
    {
        for (int i=0;i<individuals.size();++i)
        {
            individuals.get(i);
        }
    }
    public void getGenerationFromDatabase()
    {
        DataBaseUtil db = new DataBaseUtil();

        try{

            PreparedStatement preparedStatement = db.con.prepareStatement(READ_OBJECT_SQL);
            ResultSet rsResultSet = preparedStatement.executeQuery();
            while(rsResultSet.next())
            {

            }

        }catch (Exception e) {
            //logger.error("bytes2Msg error!", e);
        }
    }

	void makeNextGeneration() {
		trimPopulation();
		// pick a few individuals, copy them, mutate the copy and add it to the
		// population

		fillPopulation();
		System.out.println(this);

	}

	private void fillPopulation() {
		while (individuals.size() < POPULATION_SIZE) {
			if (Math.random() < 0.33) {
				if (Math.random() < 0.5) {
					Individual newGuy = new Individual(pickRandomIndividual());
					newGuy.mutate();
					individuals.add(newGuy);
				} else {
					Individual mom = pickRandomIndividual();
					Individual dad = pickRandomIndividual();
					while (mom == dad) {
						dad = pickRandomIndividual();
					}
					individuals.add(new Individual(pickRandomIndividual(), pickRandomIndividual()));
				}
			} else {
				individuals.add(new Individual());
			}
		}
		Collections.sort(individuals);
	}

	private Individual pickRandomIndividual() {
		int totalFitness = calculateTotalFitness();
		int target = (int) (Math.random() * totalFitness);
		int fitnessSoFar = individuals.get(0).getFitness();
		int currentIndividual = 0;
		while (fitnessSoFar < target) {
			currentIndividual++;
			fitnessSoFar = fitnessSoFar + individuals.get(currentIndividual).getFitness();
		}
		return individuals.get(currentIndividual);
	}

	private int calculateTotalFitness() {
		int sum = 0;
		for (Individual i : individuals) {
			sum = sum + i.getFitness();
		}
		return sum;
	}

	private void trimPopulation() {
		int lastToKeep = (int) ((double) (100 - PERCENT_TO_TRIM) / 100 * POPULATION_SIZE);
		while (individuals.size() > lastToKeep) {
			individuals.remove(individuals.size() - 1);
		}
	}

	void generatePopulation() {
		individuals = new ArrayList<Individual>();
		for (int i = 0; i < POPULATION_SIZE; i++) {
			individuals.add(new Individual());
		}
		Collections.sort(individuals);
		System.out.println(this);
	}

	public Individual getIndividual(int i) {
		return individuals.get(i);
	}

	public String toString() {
		String result = "";
		for (Individual x : individuals) {
			result = result + " " + x;
		}
		return result;
	}

	public void increaseFitness(int imageNumber) {
		individuals.get(imageNumber).increaseFitness();
	}
}
