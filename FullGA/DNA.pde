class DNA { //<>//

  // The genetic sequence
  int rndNum=4096;  //Arbitrary length, dna length
  float[] genes = new float[rndNum];  //genes 1
  int[] rnd1 = new int[rndNum];  // genes 2
  int[] rnd2 = new int[rndNum];  // genes 3
  int[] rnd3 = new int[rndNum];  // genes 3
  
  
  int[] pattern2_genes1 = new int[rndNum];
  int[] pattern2_genes2 = new int[rndNum];
  int[] pattern2_genes3 = new int[rndNum];
  int[] pattern2_genes4 = new int[rndNum];

  //Constructor (makes a random DNA)
  DNA() {
    // DNA is random floating point values between 0 and 1 (!!)
    for (int i = 0; i < rndNum; i++) {
      genes[i] = random(0, 1);
      rnd1[i]=int(random(100));
      rnd2[i]=int(random(0, 6));
      rnd3[i]=int(random(10, 30));
      

      pattern2_genes1[i]=int(random(5));
      pattern2_genes2[i]=int(random(10));
      pattern2_genes3[i]=int(random(10));
      pattern2_genes4[i]=int(random(2));
      
    }
  }

  DNA(float[] newgenes, int _rnd1[], int _rnd2[], int _rnd3[]) {
    genes = newgenes;
    rnd1=_rnd1;
    rnd2=_rnd2;
    rnd3=_rnd3;
  }
  DNA(float[] newgenes, int _rnd1[], int _rnd2[], int _rnd3[],int _p2_g1[],int _p2_g2[],int _p2_g3[],int _p2_g4[]) {
    genes = newgenes;
    rnd1=_rnd1;
    rnd2=_rnd2;
    rnd3=_rnd3;
    
    pattern2_genes1=_p2_g1;
    pattern2_genes2=_p2_g2;
    pattern2_genes3=_p2_g3;
    pattern2_genes4=_p2_g4;
  }


  // Crossover
  // Creates new DNA sequence from two (this & 
  DNA crossover(DNA partner) {
    float[] child = new float[genes.length];

    int[] child_rnd1 = new int[rndNum];
    int[] child_rnd2 = new int[rndNum];
    int[] child_rnd3 = new int[rndNum];

  int[] child_pattern2_genes1 = new int[rndNum];
  int[] child_pattern2_genes2 = new int[rndNum];
  int[] child_pattern2_genes3 = new int[rndNum];
  int[] child_pattern2_genes4 = new int[rndNum];

    int crossover = int(random(rndNum));

    for (int i = 0; i < genes.length; i++) {
      if (i > crossover) 
      {
        child[i] = genes[i];
        child_rnd1[i]=rnd1[i];
        child_rnd2[i]=rnd2[i];
        child_rnd3[i]=rnd3[i];
        child_pattern2_genes1[i] = pattern2_genes1[i];
        child_pattern2_genes2[i] = pattern2_genes2[i];
        child_pattern2_genes3[i] = pattern2_genes3[i];
        child_pattern2_genes4[i] = pattern2_genes4[i];
      } else {
        child[i] = partner.genes[i];
        child_rnd1[i]=partner.rnd1[i];
        child_rnd2[i]=partner.rnd2[i];
        child_rnd3[i]=partner.rnd3[i];
        
        child_pattern2_genes1[i] = partner.pattern2_genes1[i];
        child_pattern2_genes2[i] = partner.pattern2_genes2[i];
        child_pattern2_genes3[i] = partner.pattern2_genes3[i];
        child_pattern2_genes4[i] = partner.pattern2_genes4[i];
      }
    }
    DNA newgenes = new DNA(child, child_rnd1, child_rnd2, child_rnd3,child_pattern2_genes1,child_pattern2_genes2,child_pattern2_genes3,child_pattern2_genes4);
    return newgenes;
  }

  // Based on a mutation probability, picks a new random character in array spots
  void mutate(float m) {
    for (int i = 0; i < genes.length; i++) {
      if (random(1) < m) {
        genes[i] = random(0, 1);
        rnd1[i]=int(random(100));
        rnd2[i]=int(random(0, 6));
        rnd3[i]=int(random(10, 30));
        
      pattern2_genes1[i]=int(random(5));
      pattern2_genes2[i]=int(random(10));
      pattern2_genes3[i]=int(random(10));
      pattern2_genes4[i]=int(random(2));
      
      }
    }
  }
}