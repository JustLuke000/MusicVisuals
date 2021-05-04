package ie.tudublin;

public class MyVisual extends Visual
{    
    SequentialVisualization sV;

    public void settings()
    {
        size(1024, 500, P3D);
        fullScreen();                   // Use this to make fullscreen
    }

    public void setup()
    {
        startMinim();
        loadAudio("alliwant.mp3");      // Call loadAudio to load an audio file to process 
        sV = new SequentialVisualization(this);
 
        getAudioPlayer().play();
    }

    public void draw()
    {
        background(0);
        calculateFrequencyBands();      // Call this is you want to use frequency bands
        calculateAverageAmplitude();    // Call this is you want to get the average amplitude
    
        sV.render();
    }
}
