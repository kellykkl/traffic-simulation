import java.awt.Color;
import java.awt.Graphics;

public class Semi extends Vehicle{
	
	public Semi(int newx, int newy) {
		super(newx, newy);
		width = 65;
		height = 30;
		speed = 5;
	}
	
	public void paintMeHor(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
	public void paintMeVert(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, height, width);
	}
}
