
class PatternSquareCircle {

  float radius, strokW;
  DNA dna;
  color[] palette = {
    #E08E79, #F1D4AF, #ECE5CE, #C5E0DC, #145281, #D5E1EA, #bfe6a8, #2f7e70, #7e2f3d, #cfa8e6
  };
  color[] bg = {
    #07426F, #6C4509, #3D096C, #6C0918, #7C7576, #FFF7F8, #07426F, #6C4509, #3D096C, #6C0918, #7C7576, #FFF7F8
  };
  int count=0;
  int frame_w=300;
  int frame_h=300;
  PatternSquareCircle(DNA _dna) {
    dna=_dna;
    count=0;

  }
  void display()
  {
    noFill();
    rectMode(CORNER);
    strokW = 2;
    radius = frame_w/2.5;
    
   // fill(bg[dna.pattern2_genes2[count]]);
   // rect(0, 0, frame_h, frame_h);
    drawRect(frame_w/2, frame_h/2, radius, dna.pattern2_genes1[count]);
    println(count);
  }
  void drawRect(float x, float y, float radius, int num) {
    
    float new_x, new_y;
    int new_num;
 
    strokeWeight(strokW);
    //println(sw);
    fill(255, 50);
    stroke(palette[dna.pattern2_genes2[count]], 200);
    rect(x, y, radius/2, radius/2, radius/5 );
    //stroke(palette[dna.pattern2_genes3[count]], 200);
    if(dna.pattern2_genes4[count]==1)
      ellipse(x, y, radius, radius);
      
    strokW = 2;
    if (num > 0) {
      new_num = num-1;
      new_x = x;
      new_y = y-radius/2;
      drawRect(new_x, new_y, radius/2, new_num);
      new_x = x +radius/2;
      new_y = y ;
      drawRect(new_x, new_y, radius/2, new_num);
      new_x = x;
      new_y = y +radius/2;
      drawRect(new_x, new_y, radius/2, new_num);
      new_x = x-radius/2;
      new_y = y;
      drawRect(new_x, new_y, radius/2, new_num);
    }
        count++;

  }
}