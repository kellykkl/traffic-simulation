import java.awt.Color;
import java.awt.Graphics;

public class Sports extends Vehicle{
	public Sports(int newx, int newy) {
		super(newx, newy);
		width = 30;
		height = 15;
		speed = 12;
	}
	
	public void paintMeHor(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	public void paintMeVert(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, height, width);
	}
}
