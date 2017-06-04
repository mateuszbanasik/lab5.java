import java.util.TimerTask;

public class SimTask extends TimerTask{


	@SuppressWarnings("unused")
	private SimEngine pole1;
	private SpringApplet pole2;
	private double odstep;
	
	public SimTask()
	{
		
	}
	
	public SimTask(SimEngine pole1, SpringApplet pole2)
	{
		this.pole1 = pole1;
		this.pole2 = pole2;
		this.odstep = odstep();
	}
	

	@Override
	
	
	public void run() {
		this.odstep+=1;
		System.out.println("MInelo czasu" + this.odstep);
		pole2.repaint();
	}

	
	public double odstep()
	{
		return this.odstep;
	}
	

}