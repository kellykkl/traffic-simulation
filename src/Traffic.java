import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Traffic implements ActionListener, Runnable, ChangeListener{

	JFrame frame = new JFrame("Traffic Simulation");
	Road road = new Road();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton toggle = new JButton("Toggle Lights");
	Container sliderContainer = new Container();
	JLabel sliderLabel = new JLabel("Traffic Flow", JLabel.CENTER);
	JSlider trafficflow = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	Container south = new Container();
	
	boolean running = false;
	boolean hor_green = false; //by default, horizontal road gets red light
	int traffic = 50;
	
	public Traffic() {
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		south.setLayout(new GridLayout(1,4));
		south.add(toggle);
		toggle.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		sliderContainer.setLayout(new GridLayout(2,1));
		sliderContainer.add(sliderLabel);
		sliderContainer.add(trafficflow);
		trafficflow.addChangeListener(this);
		south.add(sliderContainer);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new Traffic();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(start)) {
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) {
			running = false;
		}
		if (event.getSource().equals(toggle)) {
			hor_green = !hor_green;
		}
		
	}

	@Override
	public void run() {
		while (running == true) {
			road.step(traffic, hor_green);
			frame.repaint();
			try {
				Thread.sleep(50);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent event) {
	    JSlider source = (JSlider)event.getSource();
	    if (!source.getValueIsAdjusting()) {
	        traffic = (int)source.getValue();
	    }
		
	}
	


}
