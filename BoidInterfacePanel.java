
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This application simulation the flocking of animals.
 * This source file set up the boids graphical user interface.
 * 
 * Started: 11th August 2015, Time: 2329
 * @author Jung Woo (Noel) Park, Philip Anderson
 * Finished:
 */
public class BoidInterfacePanel extends JPanel implements Runnable {

    /** create listener instance */
    private ButtonListener actionListener  = new ButtonListener();

    /** creates buttons */
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton createBoidButton = new JButton("Create");
    private JButton flock = new JButton("Flock");
    private JButton unflock = new JButton("Unflock");

    /** creates labels */
    private JLabel controlLabel = new JLabel("Controls", SwingConstants.CENTER);
    private JLabel createBoids = new JLabel("Create Boids", SwingConstants.CENTER);

    /** creates text field */
    private JTextField inputBoidNum = new JTextField();

    /** timer to recall events */
    private Timer run = new Timer(0, actionListener);

    /** constant screen dimensions */
    private final int MONITORWIDTH = 1920, MONITORHEIGHT = 1080;
    private final int PANELWIDTH = 100, PANELHEIGHT = 1080;

    /** boid simulation object */
    private final BoidsSimulation simulation;

    /** Thread */
    private Thread thread;
    private int fps = 0;
    private boolean running = false;
    private String currentFPS = "0";
    
    private Font font = new Font("Arial", Font.PLAIN, 20);
    
    /** constructor method */
    public BoidInterfacePanel(BoidsSimulation simulation){
	// instantiate simulation object
	this.simulation = simulation;

	// create panel
	JPanel controlPanel = new JPanel();
	JPanel graphicsPanel = new JPanel();

	// main panel settings
	graphicsPanel.setPreferredSize(new Dimension(1920, 1080));
	graphicsPanel.setLayout(new GridBagLayout());

	// layout grid bag constraints
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = 0;
	gbc.gridy = 0;

	// create gridlayout
	GridLayout grid = new GridLayout(35, 1);

	// control Panel settings
	controlPanel.setLayout(grid);
	controlPanel.setBackground(new Color(20, 20, 20));
	controlPanel.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));

	// add listeners 
	flock.addActionListener(actionListener);
	unflock.addActionListener(actionListener);
	stopButton.addActionListener(actionListener);
	startButton.addActionListener(actionListener);
	createBoidButton.addActionListener(actionListener);
	
	inputBoidNum.addKeyListener(actionListener);

	// component settings
	controlLabel.setForeground(Color.white);
	createBoids.setForeground(Color.white);

	// add control components
	controlPanel.add(controlLabel);
	controlPanel.add(flock);
	controlPanel.add(unflock);
	controlPanel.add(startButton);
	controlPanel.add(stopButton);
	controlPanel.add(createBoids);
	controlPanel.add(inputBoidNum);
	controlPanel.add(createBoidButton);

	// add to main panel
	graphicsPanel.add(new DrawPanel(), gbc);
	gbc.gridx = MONITORWIDTH - PANELWIDTH;
	graphicsPanel.add(controlPanel, gbc);

	// add to main window
	add(graphicsPanel);
    }

    private void start() {
	thread = new Thread(this);
	thread.start();
    }
    
    public void run() {
	running = true;
	
	long startTime = System.nanoTime();
	
	while (running == true) {
	    if(System.nanoTime() - startTime >= 1000000000) {
		startTime = System.nanoTime();
		currentFPS = String.valueOf(fps);
		fps = 0;
	    }
	}
    }
    
    private class DrawPanel extends JPanel{
	/** drawPanel constructor */
	private DrawPanel(){
	    // draw panel settings
	    setPreferredSize(new Dimension(MONITORWIDTH - PANELWIDTH, MONITORHEIGHT));
	}

	public void paintComponent(Graphics g){
	    // refers to inherited constructor
	    super.paintComponent(g);
	    setBackground(Color.black);
	    
	    simulation.render(g);
	    simulation.update();
	    
	    g.setColor(Color.YELLOW);
	    g.setFont(font);
	    g.drawString(currentFPS, 10, 20);
	}
    }

    private class ButtonListener implements KeyListener, ActionListener{
	/** method detects if event has been detected */
	public void actionPerformed(ActionEvent e){
	    if(e.getSource().equals(run)) {
		repaint();
		fps++;
	    }
	    
	    else if(e.getSource().equals(startButton)) {
		start();
		run.start();
	    }

	    else if(e.getSource().equals(stopButton)) run.stop();

	    else if(e.getSource().equals(createBoidButton)) {
		try{
		    simulation.initBoids(Integer.parseInt(inputBoidNum.getText()));
		    
		} catch(NumberFormatException error){ // catch error if illegal character
		    JOptionPane.showMessageDialog(null, "Error Invalid Integer!");
		} catch(IllegalArgumentException error){ // catch error if boids greater then limit
		    JOptionPane.showMessageDialog(null, "Too many Biods!");
		}
		
		repaint();
	    }
	}

	public void keyPressed(KeyEvent event){
	    if(event.getKeyCode() == KeyEvent.VK_ENTER){
	    }
	}

	// methods that are dormant 
	public void keyTyped(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
    }
}

