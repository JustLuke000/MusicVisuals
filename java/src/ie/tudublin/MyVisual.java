package ie.tudublin;

public class MyVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;

    public void settings()
    {
        size(1024, 500, P3D);
        fullScreen();                   // Use this to make fullscreen

    }

    public void setup()
    {
        startMinim();
        loadAudio("alliwant.mp3");      // Call loadAudio to load an audio file to process 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);

        getAudioPlayer().play();
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
        background(0);
        try
        {
            calculateFFT();             // Call this if you want to use FFT data
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();      // Call this is you want to use frequency bands
        calculateAverageAmplitude();    // Call this is you want to get the average amplitude
        
        wf.render();
    }
}
