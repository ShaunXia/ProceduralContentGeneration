
class ColorSquares{
  
int cols, rows;
color[] colors;
int[] blockSizes = new int[3];
int resolutionMultiplier = 1;
int frame_w=300;
int frame_h=300;

DNA dna;
ColorSquares(DNA _dna)
{
  dna=_dna;
    
   //setup colours array to easily use and change only the colours I want
  //colors=new color[6];
  //colors[0]=color(228,245,177);
  //colors[1]=color(123,176,168);
  //colors[2]=color(99,82,116);
  //colors[3]=color(81,43,82);
  //colors[4]=color(228,245,177); 
  
  //colors[5]=color(167,219,171); //background colour light green colour
  //colors =new color[] {
  //#E08E79, #F1D4AF, #ECE5CE, #C5E0DC, #145281, #D5E1EA, #D5E1EA};
  colors = new color[]{
    #263C8B, #4E74A6, #BDBF78, #BFA524, #2E231F, #D5E1EA
  };

  //create arrray of the sizes we want to use to make it easier to change
  blockSizes[0] = 40*resolutionMultiplier;
  blockSizes[1] = 20*resolutionMultiplier;
  blockSizes[2] = 10*resolutionMultiplier;
  //blockSizes[3] = 5*resolutionMultiplier;
  
  //background(colors[5]);
    
  // Initialize columns and rows, the number of these will differ depending on the size of the block
  // We will start off with multipling the pixels by an Integer and eventually end up at the single pixel level
  int blockSize = blockSizes[0];
    
 
  
}
void display() { //<>//
   fill(colors[5]);
   rect(0,0,frame_h,frame_h);
  int totalRand=0;
  for (int h = 0; h < blockSizes.length; h ++) {
        // Initialize columns and rows, the number of these will differ depending on the size of the block
        // We will start off with multipling the pixels by an Integer and eventually end up at the single pixel level
        cols = frame_w/blockSizes[h];
        rows = frame_h/blockSizes[h];
       
  
    
    // Begin loop for the first and largest set of blocks
    for (int i = 0; i < cols; i++) {
      // Begin loop for rows
      for (int j = 0; j < rows; j++) {
        int r = dna.rnd1[totalRand];
        if (r < 25){ //how often to actually draw the square, .25
        
        // Scaling up to draw a rectangle at (x,y)
        int x = i*blockSizes[h];
        int y = j*blockSizes[h];
  
        fill(colors[dna.rnd2[totalRand]]);
        stroke(70,dna.rnd3[totalRand]); //randomly draw a light stroke
        // For every column and row, a rectangle is drawn at an (x,y) location scaled and sized by videoScale.
        rect(x+3,y+3,blockSizes[h],blockSizes[h]); //the 3 pixel shift is to equal out the spaces on the sides
        }
        else if(r<30){          //if we aren't drawing a coloured square, sometimes draw an empty/stroked square - why? because why not.      
        // Scaling up to draw a rectangle at (x,y)
        int x = i*blockSizes[h];
        int y = j*blockSizes[h];
  
        fill(0,0);
        stroke(70,25);
        //noStroke();
        // For every column and row, a rectangle is drawn at an (x,y) location scaled and sized by videoScale.
        rect(x+3,y+3,blockSizes[h],blockSizes[h]);
        } 
        totalRand++;
      }
    }  //end first loop of colours
      
  }

}

}