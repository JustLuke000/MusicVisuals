package ie.tudublin;

import processing.core.PApplet;

public class WaveForm
{
    MyVisual mv;
    float cy = 0;

    public WaveForm(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    private float angle = 0;
    private float angle1 = 0;
    private float angle2 = 0;
    private float angle3 = 0;

    private float smoothedBoxSize = 0;
    private float loadingX;

    private int loadingTime = 400;
    private int zoomOut = 1;
    private int k = 0;

    public void render()
    {
        mv.camera(0, 0, zoomOut, 0, 0, -1, 0, 1, 0);
        mv.colorMode(PApplet.HSB);
        mv.background(0);
        mv.noFill();
        mv.lights();

        mv.calculateAverageAmplitude();
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 1, 0, 255, 0), 255, 255);
        
        mv.translate(0, 0, -250);
            
        float boxSize = 50 + (mv.getAmplitude() * 300);
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
        
            
        }
        else
        {
            mv.rotateY(angle);
            mv.rotateX(angle);

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

                mv.pushMatrix();
                    mv.sphere(800); 
                mv.popMatrix();
        }
        
        angle += 0.01f;
        angle1 -= 0.01f;
        angle2 += 0.02f;
        angle3 += 0.005f;
        update();

    }

    public void update()
    {
        if (k <= loadingTime)
        {
            loadingX = mv.map(k, 0, loadingTime, -200, 200);
            k += 1;
        }
        else
        {
            zoomOut += 1;
        }
           
    }
}