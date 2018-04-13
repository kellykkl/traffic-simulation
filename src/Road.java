import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Road extends JPanel{
	
	final int LANE_HEIGHT = 70;
	final int ROAD_WIDTH = 1000;
	final int ROAD_HEIGHT = 800;
	ArrayList<Vehicle> hor_cars = new ArrayList<Vehicle>();
	ArrayList<Vehicle> vert_cars = new ArrayList<Vehicle>();
	
	public Road() { //constructor
		super(); //call JPanel's constructor
	}
	
	public void addCarHor(Vehicle v) {
		hor_cars.add(v);
	}
	
	public void addCarVert(Vehicle v) {
		vert_cars.add(v);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		//draw horizontal road
		for (int a = getHeight()/2; a<(getHeight()/2 + 3*LANE_HEIGHT); a = a + LANE_HEIGHT) {
			for (int b=0; b < getWidth() ; b = b+40) {
				g.fillRect(b, a, 30, 5);
			}
		}
		//draw vertical road
		for (int a = getWidth()/2; a<(getWidth()/2 + 3*LANE_HEIGHT); a = a + LANE_HEIGHT) {
			for (int b=0; b < getHeight() ; b = b+40) {
				g.fillRect(a, b, 5, 30);
			}
		}
		//draw black boxes in intersection
		g.setColor(Color.BLACK);
		g.fillRect(getWidth()/2-5, getHeight()/2+5, 2*LANE_HEIGHT+10, 2*LANE_HEIGHT-5);
		g.fillRect(getWidth()/2+5, getHeight()/2, 2*LANE_HEIGHT-5, 2*LANE_HEIGHT+10);
		//draw cars
		for (int a=0; a<hor_cars.size();a++) {
			hor_cars.get(a).paintMeHor(g);
		}
		for (int a=0; a<vert_cars.size();a++) {
			vert_cars.get(a).paintMeVert(g);
		}
	}
	
	public void step(int traffic, boolean hor_green) {
		
		//add cars to horizontal road randomly
		Random r = new Random();
		double rand = r.nextDouble();
		if (rand > 1-0.001*(double)traffic) {
			int cartype = r.nextInt(3);
			if (cartype==0) {
				Semi v = new Semi(10,getHeight()/2 + 20);
				for (int x = 10; x < ROAD_WIDTH/2-20; x = x+20) {
					for (int y = getHeight()/2 + 20; y < (getHeight()/2+20+2*LANE_HEIGHT); y = y+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_hor(x,y,v.getWidth(),v)==false) {
							hor_cars.add(v);
							return;
						}
					}
				}

			}else if (cartype==1) {
				SUV v = new SUV(10,getHeight()/2 + 20); 
				for (int x = 10; x < ROAD_WIDTH/2-20; x = x+20) {
					for (int y = getHeight()/2 + 20; y < (getHeight()/2+20+2*LANE_HEIGHT); y = y+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_hor(x,y,v.getWidth(),v)==false) {
							hor_cars.add(v);
							return;
						}
					}
				}
			}else {
				Sports v = new Sports(10,getHeight()/2 + 20); 
				for (int x = 10; x < ROAD_WIDTH/2-20; x = x+20) {
					for (int y = getHeight()/2 + 20; y < (getHeight()/2+20+2*LANE_HEIGHT); y = y+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_hor(x,y,v.getWidth(),v)==false) {
							hor_cars.add(v);
							return;
						}
					}
				}
			}
		}

		//add cars to vertical road randomly
		Random r2 = new Random();
		double rand2 = r.nextDouble();
		if (rand2 > 1-0.001*(double)traffic) {
			int cartype = r2.nextInt(3);
			if (cartype==0) {
				Semi v = new Semi(getWidth()/2 + 25,20); 
				for (int y = 10; y < ROAD_HEIGHT/2-50; y = y+20) {
					for (int x = getWidth()/2 + 20; x < (getWidth()/2+20+2*LANE_HEIGHT); x = x+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_vert(x,y,v.getWidth(),v)==false) {
							vert_cars.add(v);
							return;
						}
					}
				}
			}else if (cartype==1) {
				SUV v = new SUV(getWidth()/2 + 25,20); 
				for (int y = 10; y < ROAD_HEIGHT/2-50; y = y+20) {
					for (int x = getWidth()/2 + 20; x < (getWidth()/2+20+2*LANE_HEIGHT); x = x+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_vert(x,y,v.getWidth(),v)==false) {
							vert_cars.add(v);
							return;
						}
					}
				}
			}else {
				Sports v = new Sports(getWidth()/2 + 25,20); 
				for (int y = 10; y < ROAD_HEIGHT/2-50; y = y+20) {
					for (int x = getWidth()/2 + 20; x < (getWidth()/2+20+2*LANE_HEIGHT); x = x+LANE_HEIGHT) {
						v.setX(x);
						v.setY(y);
						if (collision_vert(x,y,v.getWidth(),v)==false) {
							vert_cars.add(v);
							return;
						}
					}
				}
			}
		}
		
		for (int a=0; a<hor_cars.size();a++) {
			Vehicle v = hor_cars.get(a);
			//if you have to stop
			if ((hor_green == false && v.getX()+v.getWidth()<=getWidth()/2 
					&& v.getX()+v.getWidth()+v.getSpeed()>getWidth()/2) //red light, you will run the red light if you move
					|| (hor_green == true && v.getX()+v.getWidth()<=getWidth()/2 //green light but there are cars in the intersection
							&& v.getX()+v.getWidth()+v.getSpeed()>getWidth()/2 && vert_inters()==true)) { 
				//do nothing
			}else {
				if (collision_hor(v.getX()+v.getSpeed(),v.getY(),v.getWidth(),v)==false) {
					v.setX(v.getX() + v.getSpeed());
				}else {
					if (v.getY()==getHeight()/2+20 //im in top lane and no one in bottom lane
							&& collision_hor(v.getX()+v.getSpeed(),v.getY()+LANE_HEIGHT,v.getWidth(),v)==false){
						v.setY(v.getY()+LANE_HEIGHT);
					}else if(v.getY()==getHeight()/2+20+LANE_HEIGHT
							&& collision_hor(v.getX()+v.getSpeed(),v.getY()-LANE_HEIGHT,v.getWidth(),v)==false){
						v.setY(v.getY()-LANE_HEIGHT);
					}
				}				
			}
			

			if (v.getX() > ROAD_WIDTH) {
				hor_cars.remove(a); 
			}
		}

		for (int a=0; a<vert_cars.size();a++) {
			Vehicle v = vert_cars.get(a);
			
			if ((hor_green == true && v.getY()+v.getWidth()<=getHeight()/2 
					&& v.getY()+v.getWidth()+v.getSpeed()>getHeight()/2) //red light, you will run the red light if you move
					|| (hor_green == false && v.getY()+v.getWidth()<=getHeight()/2 //green light but there are cars in the intersection
							&& v.getY()+v.getWidth()+v.getSpeed()>getHeight()/2 && hor_inters() == true)) { 
				//do nothing 
				
			}else {
				if (collision_vert(v.getX(),v.getY()+v.getSpeed(),v.getHeight(),v)==false) {
					v.setY(v.getY() + v.getSpeed());
				}else {
					if (v.getX()==getWidth()/2+20 //im in left lane and no one in right lane
							&& collision_vert(v.getX()+LANE_HEIGHT,v.getY()+v.getSpeed(),v.getWidth(),v)==false){
						v.setX(v.getX()+LANE_HEIGHT);
					}else if(v.getX()==getWidth()/2+20+LANE_HEIGHT
							&& collision_vert(v.getX()-LANE_HEIGHT,v.getY()+v.getSpeed(),v.getWidth(),v)==false){
						v.setX(v.getX()-LANE_HEIGHT);
					}
				}				
			}
			

			if (v.getY() > ROAD_WIDTH) {
				vert_cars.remove(a);
			}
		}
	}
	
	public boolean collision_hor(int x, int y, int width, Vehicle v) {
		for (int a=0; a < hor_cars.size(); a++) {
			Vehicle u = hor_cars.get(a);
			if (y == u.getY()) { //if i'm in the same lane
				if (u.equals(v) == false) { //if i'm not checking myself
					if (x < u.getX() + u.getWidth() //if my left side is to the left of his right side
						&& x + v.getWidth() > u.getX()) { //and my right side is to the right of his left side 
						return true;
					}
				}
			}
		}
		
		return false;
	}

	
	public boolean collision_vert(int x, int y, int height, Vehicle v) {
		for (int a=0; a < vert_cars.size(); a++) {
			Vehicle u = vert_cars.get(a);
			if (x == u.getX()) { //if i'm in the same lane
				if (u.equals(v) == false) { //if i'm not checking myself
					if (y < u.getY() + u.getWidth() //if my top side is above his bottom side
						&& y + v.getWidth() > u.getY()) { //and my bottom side is below his top side
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean hor_inters() {
		for (int a=0; a<hor_cars.size(); a++) {
			Vehicle u = hor_cars.get(a);
			if (u.getX()+u.getWidth() > getWidth()/2 && u.getX()< getWidth()/2 + 2*LANE_HEIGHT) {
				return true;
			}
		}
		return false;
	}
	
	public boolean vert_inters() {
		for (int a=0; a<vert_cars.size(); a++) {
			Vehicle u = vert_cars.get(a);
			if (u.getY()+u.getWidth() > getHeight()/2 && u.getY()< getHeight()/2 + 2*LANE_HEIGHT) {
				return true;
			}
		}
		return false;
	}
}
