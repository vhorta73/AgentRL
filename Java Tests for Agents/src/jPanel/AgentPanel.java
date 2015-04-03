package jPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * The Agent's Panel.
 * 
 * @author Vasco
 *
 */
public class AgentPanel extends JPanel implements Runnable {
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Final pane width.
	 */
	private final int WIDTH;
	
	/**
	 * Final pane height.
	 */
	private final int HEIGHT;
	
	/**
	 * Pane scale
	 */
	private int SCALE;

	// Agent pane thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// Agent State Manager
	private AgentStateManager asm;
	
	public AgentPanel(int width, int height, int scale) {
		super();
		WIDTH  = width;
		HEIGHT = height;
		SCALE  = scale;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if ( thread == null ) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) g;
		running = true;
		asm = new AgentStateManager();
	}
	
    public void see(double[][] data) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		for( int i = 0 ; i < WIDTH; i++ ) {
			for(int j = 0 ; j < HEIGHT; j++) {
				float c = (float) data[i][j];
				g.setColor(new Color(c, c, c));
				g.fillRect(i,  j,  1,  1);
			}
		}
    }
    
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// Loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			try {
				Thread.sleep(wait);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				
		}
	}
	
	private void update() {
		asm.update();
	}
	
	private void draw() {
		asm.draw();
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	
	
//	public AgentPanel(String name, BufferedImage img, int width, int height) {
//		frame = new JFrame(name);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		panel = new JPanel() {
//    		/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//	    	protected void paintComponent(Graphics g) {
////		  	    Graphics2D g2d = (Graphics2D) g;
////                g2d.clearRect(0, 0, getWidth(), getHeight());
////                g2d.setRenderingHint(
////                   RenderingHints.KEY_INTERPOLATION,
////                   RenderingHints.VALUE_INTERPOLATION_BILINEAR);
////                g2d.scale(2, 2);
////                g2d.drawImage(img, 0, 0, this);
//    		}
//	    };
//    	panel.setPreferredSize(new Dimension(width, height));
//		frame.getContentPane().add(panel);
//        frame.pack();
//        frame.setVisible(true);
//	}
	
//	public void 
	
}
