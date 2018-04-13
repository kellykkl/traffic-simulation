import java.awt.Graphics;

public class Vehicle {

	int x;
	int y;
	int width=0;
	int height=0;
	int speed;
	
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void paintMeHor(Graphics g) {
		
	}
	
	public void paintMeVert(Graphics g) {
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int newx) {
		x = newx;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int newy) {
		y = newy;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
