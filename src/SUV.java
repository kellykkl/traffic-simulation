import java.awt.Color;
import java.awt.Graphics;

public class SUV extends Vehicle{
	public SUV(int newx, int newy) {
		super(newx, newy);
		width = 50;
		height = 20;
		speed = 8;
	}
	
	public void paintMeHor(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	public void paintMeVert(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, height, width);
	}

}
