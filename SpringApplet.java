import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;






public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener {

	public static final int WIDTH=800; // definiuje szerokosc oraz wysokosc appletu, które ustawiam w konfiguracjach RUN,
	public static final int HEIGHT=600; // zmiennych tych bede uzywac przy rysowaniu wektorów
	
	private int yPocz = HEIGHT/2;
	
	private SimTask simtask;
	private Timer timer1;
	private SimEngine silnik1;
	public int mousedragged;
	Dimension d;
	private JButton button1,buttonR;
	
	private JTextField fieldC,fieldK,fieldG,fieldL,fieldY,fieldX;
	private JLabel labelC,labelK,labelG,labelL,labelY,labelX;
	

	
	private static final long serialVersionUID = 1L;


	
	public void init()
	{
		d = getSize();
		setLayout(null);
		silnik1 = new SimEngine(2,20,10,(WIDTH+200)/2,HEIGHT/2,1,60,20,(WIDTH+200)/2);
		simtask = new SimTask(silnik1,this);
		timer1 = new Timer();
		timer1.scheduleAtFixedRate(simtask, 0, 1000);
		mousedragged = 0;
		button1 = new JButton("Potwiedz Dane");
		button1.setBounds(0,10,200,30);
		button1.addActionListener(this);
		fieldC = new JTextField();
		fieldC.setBounds(110, 50, 90, 30);
		labelC = new JLabel("Wprowadz C");
		labelC.setBounds(0,45,110,30);
		fieldK = new JTextField();
		fieldK.setBounds(110,90,90,30);
		labelK = new JLabel("Wprowadz K");
		labelK.setBounds(0,85,110,30);
		fieldG = new JTextField();
		fieldG.setBounds(110,130,90,30);
		labelG = new JLabel("Wprowadz G");
		labelG.setBounds(0,125,110,30);
		fieldL = new JTextField();
		fieldL.setBounds(110,170,90,30);
		labelL = new JLabel("Wprowadz L");
		labelL.setBounds(0,165,110,30);
		fieldY = new JTextField();
		fieldY.setBounds(110, 210, 90, 30);
		labelY = new JLabel("Pos Y");
		labelY.setBounds(0, 210, 110, 30);
		fieldX = new JTextField();
		fieldX.setBounds(110, 250, 90, 30);
		labelX = new JLabel("Pos X");
		labelX.setBounds(0, 250, 110, 30);
		buttonR = new JButton("Resetuj");
		buttonR.setBounds(0,290,200,30);
		buttonR.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						fieldC.setText("");
						fieldG.setText("");
						fieldK.setText("");
						fieldL.setText("");
						fieldX.setText("");
						fieldY.setText("");
					}
			
				}
		);
		add(button1);
		add(buttonR);
		add(fieldC);
		add(fieldK);
		add(fieldG);
		add(fieldX);
		add(fieldL);
		add(fieldY);
		add(labelC);
		add(labelK);
		add(labelG);
		add(labelL);
		add(labelY);
		add(labelX);
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	// kod do zadania dodatkowego
	// rozmiary okna appletu ustawiam w opcjach kompilacj
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(200, 0, d.width, d.height);

		Vector2D wektorG = new Vector2D(silnik1.getXMasa(),silnik1.getYMasa(),silnik1.getXMasa(), silnik1.getYMasa()+(silnik1.getMasa()*silnik1.getG()));
		Polygon grotG = new Polygon(wektorG.getPolygonX(),wektorG.getPolygonY(),5);
		
		//rysowanie siatki wspolrzednych
		for(int j = 0; j<HEIGHT;j+=12)
		{
			for(int i = 200; i<WIDTH;i+=12)
			{
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0+i, silnik1.getYZawK()+j, 0+i, silnik1.getYZawK()+6+j);
			g.drawLine(i+3,j+3+silnik1.getYZawK(),i+9,j+3+silnik1.getYZawK());
			}
		}
		
		g.setColor(Color.RED);
		g.fillOval(silnik1.getXMasa()-silnik1.getROval(),silnik1.getYMasa()-silnik1.getROval(),2*silnik1.getROval(),2*silnik1.getROval());
	
		
		//rysowanie sprêzyny
		
		
		for(int i = 10; i<silnik1.zaczepieniezMasa(); i+=10)
		{
			g.setColor(Color.BLUE);
			g.drawLine(silnik1.getXZawK()-10, silnik1.getYZawK()+(i-10),silnik1.getXZawK()+10, silnik1.getYZawK()+(i));
			g.drawLine(silnik1.getXZawK()+10, silnik1.getYZawK()+(i-10),silnik1.getXZawK()-10, silnik1.getYZawK()+(i));
			
		}
		
		
		
		for(int i = 200; i<WIDTH;i+=10)
		{
			g.setColor(Color.BLACK);
			g.drawLine(200, silnik1.getYZawK(),WIDTH,	silnik1.getYZawK());
			g.drawLine(0+i,silnik1.getYZawK(), 10+i, 0);
		}
		g.setColor(Color.GREEN);
		g.drawLine((int)wektorG.x1,(int)wektorG.y1,(int)wektorG.x2,(int)wektorG.y2);
		g.fillPolygon(grotG);
		g.setColor(Color.BLACK);
		g.drawString("Wektor G", wektorG.getXString(), wektorG.getYString());
		

	
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX();
	
		int y = e.getY();
		

		
		if(x <= silnik1.getXMasa() + silnik1.getROval() && x >= silnik1.getXMasa() - silnik1.getROval())
		{
			if(y<= silnik1.getYMasa() + silnik1.getROval() && y>= silnik1.getYMasa() - silnik1.getROval())
			{
			
			silnik1.resetuj();
			timer1.cancel();
			timer1.purge();
			mousedragged = 1;
			}
		}
		
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
		if(mousedragged ==1)
		{
			
		
		timer1 = new Timer();
		timer1.scheduleAtFixedRate(new SimTask(this.silnik1,this), 0, 1000);
		silnik1.setYMasa(yPocz);
		mousedragged = 0;
		e.consume();
				
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	
		if(mousedragged == 1)
		{		
		int yReleased= e.getY();
		silnik1.setYMasa(yReleased);
		repaint();
		}
		e.consume();
		
		
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		timer1.cancel();
		timer1.purge();
		int z = Integer.parseInt(fieldC.getText());
		silnik1.setC(z);
		int w = Integer.parseInt(fieldK.getText());
		silnik1.setK(w);
		int j = Integer.parseInt(fieldG.getText());
		silnik1.setG(j);
		int l = Integer.parseInt(fieldL.getText());
		silnik1.setL(l);
		int y = Integer.parseInt(fieldY.getText());
		silnik1.setYMasa(y);
		this.yPocz = y;
		int x = Integer.parseInt(fieldX.getText());
		silnik1.setXMasa(x);
		silnik1.setXZawK(x);
		repaint();
		
	}

	
	
	
}