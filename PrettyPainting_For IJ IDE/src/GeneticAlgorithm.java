import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.*;
import java.nio.channels.*;

import org.junit.experimental.theories.Theories;

public class GeneticAlgorithm {

	public static void main(String[] args) {
		FileLock lck = null;
		try {
			lck = new FileOutputStream("mylock").getChannel().tryLock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(lck == null) {
			System.out.println("A previous instance is already running....");
			System.exit(1);
		}
		System.out.println("This is the first instance of this program...");


		GeneticAlgorithm alg = new GeneticAlgorithm();
		//alg.setSize(800, 800);
	//	alg.setVisible(true);
            //get individual from database
		alg.getGenerationFromDatabase();
		if (individuals.size()==0)
			alg.generatePopulation();
		else {
			alg.makeNextGeneration();
			System.out.println("GEt next");
		}
		alg.storeCurrentGenToDatabase();
//		for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
//			alg.makeNextGeneration();
//		}


	}
    static final String WRITE_OBJECT_SQL = "INSERT INTO genimage(imgURL,vote,generation,isshow,dna1,dna2,dna3,dna4,dna5,dna6) VALUES (?,?,?,?,?,?,?,?,?,?)";
    static final String READ_OBJECT_SQL = "SELECT * FROM genimage WHERE isshow=1";

	private static final int NUM_GENERATIONS = 100;
	static int POPULATION_SIZE = 12;
	private static int PERCENT_TO_TRIM = 30;
	private static ArrayList<Individual> individuals;
    private int currentGeneration=0;

	public GeneticAlgorithm() {
		individuals = new ArrayList<Individual>();
	}



	public void  storeCurrentGenToDatabase()
    {
		currentGeneration++;
		DataBaseUtil db = new DataBaseUtil();
		float[][] dnaToStore;
		String updateShow = "UPDATE genimage SET isshow=0 WHERE isshow=1";
		db.executeSql(updateShow);

        for (int i=0;i<individuals.size();++i)
        {

			dnaToStore=individuals.get(i).DNA.clone();

			try {

				String imgURL = "src/genimages/G_"+currentGeneration+"_"+i+".png";
				individuals.get(i).setImgURL(imgURL);
				PreparedStatement preparedStatement = db.con.prepareStatement(WRITE_OBJECT_SQL);
				preparedStatement.setString(1,individuals.get(i).imgURL);
				preparedStatement.setInt(2,0);
				preparedStatement.setInt(3,currentGeneration);
				preparedStatement.setInt(4,1);
				preparedStatement.setString(5,DataBaseUtil.floatArr2string(dnaToStore[0]));
				preparedStatement.setString(6,DataBaseUtil.floatArr2string(dnaToStore[1]));
				preparedStatement.setString(7,DataBaseUtil.floatArr2string(dnaToStore[2]));
				preparedStatement.setString(8,DataBaseUtil.floatArr2string(dnaToStore[3]));
				preparedStatement.setString(9,DataBaseUtil.floatArr2string(dnaToStore[4]));
				preparedStatement.setString(10,DataBaseUtil.floatArr2string(dnaToStore[5]));
				individuals.get(i).DrawIndividual();
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		updateShow = "UPDATE genimage SET vote=0,generation="+currentGeneration+" WHERE imgURL='status'";
		db.executeSql(updateShow);

		db.disconnect();

		System.out.println("Store Done");
    }

    public void getGenerationFromDatabase()
    {
        DataBaseUtil db = new DataBaseUtil();
		currentGeneration =0;

        try{
            PreparedStatement preparedStatement = db.con.prepareStatement(READ_OBJECT_SQL);
            ResultSet rsResultSet = preparedStatement.executeQuery();
            while(rsResultSet.next())
            {
				float [][]inDna = new float[10][1024];
				int imgID = rsResultSet.getInt("id");
				inDna[0]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna1")).clone();
				inDna[1]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna2")).clone();
				inDna[2]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna3")).clone();
				inDna[3]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna4")).clone();
				inDna[4]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna5")).clone();
				inDna[5]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna6")).clone();
//				inDna[6]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna7")).clone();
//				inDna[7]=DataBaseUtil.string2floatArr(rsResultSet.getString("dna8")).clone();
				int vote = rsResultSet.getInt("vote");
				currentGeneration=rsResultSet.getInt("generation");

				Individual idv = new Individual(inDna,vote);

				individuals.add(idv);
            }
			preparedStatement.close();

        }catch (Exception e) {
			//logger.error("bytes2Msg error!", e);
		}
		System.out.println("Get Done");
        db.disconnect();
    }

	void makeNextGeneration() {
		trimPopulation();
		// pick a few individuals, copy them, mutate the copy and add it to the
		// population

		fillPopulation();
//		System.out.println(this);

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
//
//		int totalFitness = calculateTotalFitness();
//		int target = (int) (Math.random() * totalFitness);
//		int fitnessSoFar = individuals.get(0).getFitness();
//		int currentIndividual = 0;
//		while (fitnessSoFar < target) {
//			currentIndividual++;
//			fitnessSoFar = fitnessSoFar + individuals.get(currentIndividual).getFitness();
//		}
//		return individuals.get(currentIndividual);
		return individuals.get((int)(Math.random()*individuals.size()));
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
