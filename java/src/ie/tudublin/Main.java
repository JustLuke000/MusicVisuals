package ie.tudublin;

public class Main
{	

	public void startVisualization()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startVisualization();			
	}
}