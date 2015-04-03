package test;

import jPanel.AgentPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Environment implements Runnable {

	public static void main(String[] arg) {
		Environment env = new Environment();
		env.run();
	}

	@Override
	public void run() {
		int WIDTH = 250;
		int HEIGHT = 250;

		double[][] data = new double[WIDTH][HEIGHT];
		Random r = new Random();
		for(int i = 0; i < WIDTH; i++ ) {
			for ( int j = 0; j < HEIGHT; j++) {
				data[i][j] = r.nextDouble();
			}
		}
		
		final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		for( int i = 0 ; i < WIDTH; i++ ) {
			for(int j = 0 ; j < HEIGHT; j++) {
				float c = (float) data[i][j];
				g.setColor(new Color(c, c, c));
				g.fillRect(i,  j,  1,  1);
				data[i][j] = r.nextDouble();
			}
		}
		
AgentPanel ap = new AgentPanel(WIDTH, HEIGHT, 2);		
//		JFrame frame = new JFrame("Image test");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JPanel panel = new JPanel() {
//			private static final long serialVersionUID = 1L;
//			@Override
//			protected void paintComponent(Graphics g) {
//				  Graphics2D g2d = (Graphics2D) g;
//	                g2d.clearRect(0, 0, getWidth(), getHeight());
//	                g2d.setRenderingHint(
//	                   RenderingHints.KEY_INTERPOLATION,
//	                   RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//	                // Or _BICUBIC
//	                g2d.scale(2, 2);
//	                g2d.drawImage(img, 0, 0, this);
//			}
//		};
//	
//    	panel.setPreferredSize(new Dimension(WIDTH*2, HEIGHT*2));
//        frame.getContentPane().add(panel);
//        frame.pack();
//        frame.setVisible(true);
//		
		
//		List<Action> actions = new LinkedList<Action>();
//		actions.add(Action.DOWN);
//		actions.add(Action.IDLE);
//		actions.add(Action.LEFT);
//		actions.add(Action.LOWER_LEFT);
//		actions.add(Action.LOWER_RIGHT);
//		actions.add(Action.RIGHT);
//		actions.add(Action.UP);
//		actions.add(Action.UPPER_LEFT);
//		actions.add(Action.UPPER_RIGHT);
//
//		State s01 = new StateImpl(actions);
//		State s02 = new StateImpl(actions);
//		State s03 = new StateImpl(actions);
//		State s04 = new StateImpl(actions);
//		State s05 = new StateImpl(actions);
//
//		ActionResponse ar01 = new ActionResponseImpl(s02, 0.0);
//		ActionResponse ar02 = new ActionResponseImpl(s03, 0.0);
//		ActionResponse ar03 = new ActionResponseImpl(s04, 0.0);
//		ActionResponse ar04 = new ActionResponseImpl(s05, 1.0);
//
//		Agent agent = new AgentImpl(ar01, 0.8);
//		agent.addEvent(s01, Action.DOWN, s03, 0.0);
//		agent.addEvent(s03, Action.LEFT, s02, 0.0);
//		agent.addEvent(s02, Action.UP, s04, 0.0);
//		agent.addEvent(s04, Action.RIGHT, s05, 1.0);
//		agent.addEvent(s01, Action.RIGHT, s03, 0.0);
//		agent.addEvent(s03, Action.LEFT, s02, 0.0);
//		agent.addEvent(s02, Action.UP, s04, 0.0);
//		agent.addEvent(s04, Action.RIGHT, s05, 10.0);
		
//		PathTrack pt = new PathTrackImpl(0.8);
//		pt.addEvent(s01, Action.DOWN, s02, 0.0);
//		pt.addEvent(s02, Action.DOWN, s03, 0.0);
//		pt.addEvent(s03, Action.DOWN, s05, 0.0);
//		pt.addEvent(s04, Action.DOWN, s05, 1.0);
		
//System.out.println(agent.getBestAction(s01));		
		
//		Value v = new ValueImpl();
//		Double weight = 1.0;
//		Double gamma = 0.8;
//		v.setSampleRate(100);
//		for(int i = 0 ; i< 3; i++ ) {
//			v.addWeightedSample(1.0, weight);
//			weight *= gamma;
//		System.out.println("Total samples: "+v.getTotalSamples()
//				+" MovingAve: "+v.getMovingAverage()
//				+" MovAvNorm: "+v.getMovingAverageNormalised()
//				+" Max: "+v.getMax()
//				+" Min: "+v.getMin());
//		}
//		
//		List<Action> actions = new LinkedList<Action>();
//		actions.add(Action.DOWN);
//		State s1 = new StateImpl(actions);
//		actions.add(Action.UP);
//		State s2 = new StateImpl(actions);
//		actions.add(Action.LEFT);
//		State s3 = new StateImpl(actions);
//
//		ActionResponse ar1 = new ActionResponseImpl(s1, 1.0);
//		ActionResponse ar2 = new ActionResponseImpl(s2, 2.0);
//		ActionResponse ar3 = new ActionResponseImpl(s3, 3.0);
//
//		Agent agent = new AgentImpl(ar1);
//		for(int i = 0; i < 10000; i++ ) {
//		    agent.addEvent(s1, Action.UP, s2, Math.random()*10);
//		    agent.addEvent(s1, Action.DOWN, s2, Math.random()*11);
//		    agent.addEvent(s1, Action.LEFT, s2, Math.random()*12);
////		    agent.update(ar1);
////		    agent.update(ar2);
////		    agent.update(ar3);
//
//		}
//
////		System.out.println(""+agent.getBestAction(s1));
//		List<Action> list = agent.getBestActions(s1);		
//		list.stream().forEach(a -> System.out.println(a));
	}
}
