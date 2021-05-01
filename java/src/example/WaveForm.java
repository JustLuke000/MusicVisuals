package example;

import java.util.concurrent.CyclicBarrier;

import javax.swing.Box;

import processing.core.PApplet;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    MyVisual mv;
    float cy = 0;

    public WaveForm(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    float angle = 0;
    float angle1 = 0;
    float angle2 = 0;
    float angle3 = 0;
    int scaler = 0;
    float smoothedBoxSize = 0;
    boolean twocubes = false;
    long startMillis = System.currentTimeMillis();
    long endMillis = startMillis + 10000;
    int k = 0;
    float loadingX;
    float loadingY;
    int loadingTime = 400;

    public void render()
    {

        mv.colorMode(PApplet.HSB);
        mv.calculateAverageAmplitude();
        mv.background(0);
        mv.noFill();
        mv.lights();
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 1, 0, 255, 0), 255, 255);
        mv.camera(0, 0, 1, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);
            
        float boxSize = 50 + (mv.getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = PApplet.lerp(smoothedBoxSize, boxSize, 0.2f);

        if(k <= loadingTime)
        {
                // "Atom"
                mv.pushMatrix();
                    mv.sphere(10); 

                    mv.pushMatrix();
                    mv.rotateY(angle1);
                    mv.rotateX(angle1);
                    mv.strokeWeight(1); 
                    mv.circle(0, 0, smoothedBoxSize - 10);
                    mv.popMatrix();


                    mv.pushMatrix();
                    mv.rotateY(angle2);
                    mv.rotateX(angle2);
                    mv.strokeWeight(1); 
                    mv.circle(0, 0, smoothedBoxSize);
                    mv.popMatrix();

                    mv.pushMatrix();
                    mv.rotateY(angle3);
                    mv.rotateX(angle3);
                    mv.strokeWeight(1); 
                    mv.circle(0, 0, smoothedBoxSize + 10);
                    mv.popMatrix();
                mv.popMatrix();

                // Loading Bar
                mv.rect(-200, 50, 400, 50);
                mv.pushMatrix();
                    mv.fill(PApplet.map(mv.getSmoothedAmplitude(), 1, 0, 255, 0), 255, 255);
                    mv.rect(-200, 50, loadingX+1 + 200, 50);
                mv.popMatrix();
                mv.fill(255);
                mv.text("Loading Visual...",-45,80);
        
            //scaler += 1;
            angle += 0.01f;
            angle1 -= 0.01f;
            angle2 += 0.02f;
            angle3 += 0.005f;
            update();
        }
        else
        {
            mv.pushMatrix();
            mv.rotateX(angle);
            mv.rotateZ(angle);       
            mv.box(smoothedBoxSize);  
            mv.popMatrix();
            angle += 0.01f;
        }
    }

    public void update()
    {
        if (k <= loadingTime)
        {
            loadingX = mv.map(k, 0, loadingTime, -200, 200);
            loadingY = 50;
            k += 1;
        }
           
    }
}