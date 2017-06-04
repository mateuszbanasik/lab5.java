public class SimEngine {

private double masa;
private double k;
private double c;
private int lo;
private int xMasa; // srodek masy
private int yMasa; 
private int xZawK;
private int yZawK;
private int xZawC;
private int yZawC;
private int zaczepMasa;
private double v;
private double g = 9.81;
private int rOvalMasa;


Vector2D przyspieszenie;

public SimEngine(double masa, double k, double c, int xMasa, int yMasa, double v, int rOvalMasa,int yZawK, int xZawK)
{
	this.masa = masa;
	this.k = k ;
	this.c = c ; 
	this.lo = getL();
	this.v = v;
	this.xMasa = xMasa;
	this.yMasa = yMasa;
	this.xZawK = xZawK;
	this.yZawK = yZawK;
	this.xZawC = getXZawC();
	this.yZawC = getYZawC();
	this.zaczepMasa = zaczepieniezMasa();
	this.rOvalMasa = rOvalMasa;
}


public int getG()
{
	int g = (int)this.g;
	return g;
}

public void setMasa(double masa)
{
	this.masa = masa;
}

public double getMasa()
{
	return this.masa;
	
}

public void setK(double k)
{
	this.k = k;
}

public double getK()
{
	return this.k;
}

public void setC(double c)
{
	this.c = c;
}

public double getC()
{
	return this.c;
	
}

public void setG(double g)
{
	this.g = g;
}

// wylicza dlugosc sprezyny na podstawie punktu zawieszenia i zaczepienia masy oraz promienia

public void obliczL()
{
	this.lo = getYMasa()-getROval()-getYZawK();
}
public void setL(int l)
{
	this.lo = l;
}

public int getL()
{
	obliczL();
	return this.lo;
}

public void setXMasa(int xMasa)
{
	this.xMasa = xMasa;
}

public int getXMasa()
{
	return this.xMasa;
}

public void setYMasa(int yMasa)
{
	this.yMasa = yMasa;
}

public int	getYMasa()
{
	return this.yMasa;
}


public void setXZawK(int xZawK)
{
	this.xZawK = xZawK;
}

public int	getXZawK()
{
	
	return this.xZawK;
}

public void setYZawK(int yZawK)
{
	
	this.yZawK = yZawK;
}

public int getYZawK()
{
	
	return this.yZawK;
}

public void setXZawC()
{
	this.xZawC = getXMasa();
}

public int	getXZawC()
{
	setXZawC();
	return this.xZawC;
}

public void setYZawC()
{
	this.yZawC = getYZawK();
}

public int	getYZawC()
{
	setYZawC();
	return this.yZawC;
}

public void setV(double v)
{
	this.v = v;
}

public double getV()
{
	return this.v;
}

public double resetuj()
{
	this.v = 0;
	return this.v;
}

public void setROval(int rOval)
{
	this.rOvalMasa = rOval;
}
public int	getROval()
{
	return this.rOvalMasa;
}

public int zaczepieniezMasa()
{
	this.zaczepMasa = getYZawK()+getL();
	return this.zaczepMasa;
}



public void oblicz(double h)
{
	Vector2D wektorG = new Vector2D(getXMasa(),getYMasa(),getXMasa(), getYMasa()+(getMasa()*this.g));
	Vector2D wektorK = new Vector2D(getXZawK(), getYZawK() + (getL()/2),getXZawK(),((getYZawK() + (getL()/2))*getK()*getV()*h));
	Vector2D wektorC = new Vector2D(getXZawC(), getYZawC() + (getL()/2),getXZawC(),((getYZawC() + (getL()/2))*getC()*getV()));
	Vector2D P1 = wektorG.minusVector(wektorK);
	Vector2D P2 = P1.minusVector(wektorC);
	Vector2D P = P2.multiplyVecotr(1/getMasa());
	this.przyspieszenie = P;

}

public Vector2D getPrzyszpieszenie()
{
	
	return this.przyspieszenie;
}

}