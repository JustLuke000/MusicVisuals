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
        mv.colorMode(3);
        mv.background(0);
        mv.noFill();
        mv.lights();

        mv.calculateAverageAmplitude();
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 1, 0, 255, 0), 255, 255);
        
        mv.translate(0, 0, -250);
            
        float boxSize = 50 + (mv.getAmplitude() * 300);
        smoothedBoxSize = PApplet.lerp(smoothedBoxSize, boxSize, 0.2f);
        //k = 10000;


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
            if (zoomOut > -100)
            {
                mv.rotateY(angle);
                mv.rotateX(angle);

                // Atom
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

                // Larger Sphere
                mv.pushMatrix();
                    mv.sphere(800); 
                mv.popMatrix();
            }
            else
            {
                mv.camera();


                mv.pushMatrix();
                mv.translate(mv.width/2, mv.height/2);
                for(int i = 0 ; i < mv.getAudioBuffer().size(); i ++)
                {
                    mv.stroke(PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255), 255, 255);
                    
                    mv.rotate(angle);
                    mv.pushMatrix();
                        mv.line(i, cy - mv.height / 2, i, cy + cy * mv.getAudioBuffer().get(i) - mv.height / 2);
                        mv.rotate(angle2);
                        mv.line(i, cy - mv.height / 2, i, cy + cy * mv.getAudioBuffer().get(i) - mv.height / 2);
                        mv.rotate(angle3);
                        mv.line(i, cy - mv.height / 2, i, cy + cy * mv.getAudioBuffer().get(i) - mv.height / 2);
                    mv.popMatrix();
                    
                    mv.circle(0, 0, smoothedBoxSize / 2);
                }
                mv.popMatrix();

            }
        }
        
        angle += 0.001f;
        angle1 -= 0.01f;
        angle2 += 0.02f;
        angle3 += 0.005f;
        update();

    }

    public void update()
    {
        if (k <= loadingTime)
        {
            loadingX = PApplet.map(k, 0, loadingTime, -200, 200);
            k += 1;
        }
        else
        {
            if ( k <= 3.5 * loadingTime)
            {
                zoomOut += 6;
                k += 2;
            }
            else
            {
                if (zoomOut > -100)
                {
                    zoomOut -= 15;
                }
                
            }
            
        }
           
    }
}