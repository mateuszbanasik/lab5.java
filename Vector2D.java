public class Vector2D  {
		
	// 1mpx = 3mm
	
	public double x1; // skladowa x pocz¹tku wektora
	public double y1; // skladowa y poczatku wektora
	public double x2; // skladowa x konca wektora
	public double y2; // skladowa y konca wektora
	private double dlugosc; // zmienna przechowujaca dlugosc wektora
	private double x; // do obliczenia dlugosci wektora x2-x1
	private double y; // do obliczenia dlugosci wektora y2-y1
	
	
	public Vector2D()
	{
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.dlugosc = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(double x1, double y1, double x2, double y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	// metoda sluzaca do zwracania obiektu klasy Vector po zsumowaniu dwoch wektorow 
	
	public Vector2D addVector(Vector2D vektor)
	{
		Vector2D vektor1 = new Vector2D(this.x1+vektor.x1, this.y1+vektor.y1, this.x2+vektor.x2, this.y2+vektor.x2);
		
		return vektor1;
	}
	public void wyswietl()
	{
		System.out.println("Wektor po wykonaniu zadanych operacji \n");
		System.out.println("Ma wspólrzêdne pocz¹tkowe : " + this.x1 + " , "+ this.y1 + " .\nWspo³rzêdne koñca : " +this.x2+ " , " + this.y2 + "\n");
		liczDlugosc();
	}
	
	public Vector2D minusVector(Vector2D vektor)
	{
		Vector2D vektor1 = new Vector2D(this.x1 - vektor.x1, this.y1 - vektor.y1, this.x2 - vektor.x2, this.y2 - vektor.y2);
		return vektor1;	
	}
	
	public Vector2D multiplyVecotr(double stala)
	{
		Vector2D vektor1 = new Vector2D(this.x1, stala*this.y1, this.x2, stala*this.y2);
		return vektor1;
		
	}
	
	// metoda obliczajaca dlugosc
	
	public void liczDlugosc()
	{
		this.x = this.x1 - this.x2 ;
		this.y = this.y1 - this.y2 ;
		this.dlugosc = Math.sqrt((Math.pow(this.x, 2)+Math.pow(this.y, 2)));
		System.out.println("Dlugosc wektora to " + this.dlugosc + " \n");

		
	}
	public double getDlugosc()
	{
		return  this.dlugosc;
	}

	public Vector2D normalizacjaWektora(Vector2D vektor)
	{
		
		liczDlugosc();
		Vector2D vektor1 = new Vector2D(this.x1/getDlugosc(), this.y1/getDlugosc(), this.x2/getDlugosc(),this.y2/getDlugosc());
		return vektor1;
	}
	
	// do zadania dodatkowego
	
	//metoda sluzaca do wyskalowania wektora na mpx 1mm = 3mpx
	public Vector2D changeWektor(Vector2D vektor)
	{
		Vector2D vektor1 = new Vector2D((this.x1*3)  + 10,((SpringApplet.HEIGHT - 10) - (this.y1*3)), (this.x2)*3, (SpringApplet.HEIGHT - this.y2*3));
		return vektor1;
	}
	
	//metody sluzace do zwracania tablicy pktów grotu uzywamy klasy Polygon która rysuje konture
	
	public int[] getPolygonX()
	{
		int xPolyArray[] = {(int)this.x2, (int)this.x2 - 3 , (int)this.x2 , (int)this.x2 +3 , (int)this.x2 };
		
		return xPolyArray;
	}
	
	public int[] getPolygonY()
	{
		int yPolyArray[] = {(int)this.y2, (int)this.y2 , (int)this.y2 + 6, (int)this.y2  , (int)this.y2 };
		
		return yPolyArray;
	}
	
	// metody pozwalajace ustawic podpis wektora na jego koncu 
	
	public int getXString()
	{
		int xString = (int)this.x2 + 10;
		return xString;
			
	}
	
	public int getYString()
	{
		int yString = (int)this.y2 + 2;
		return yString;
	}
	
	
}