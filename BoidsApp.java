/**
 * BoidsApp.java
 * Started: 11th August 2015, Time: 2329
 * @author Jung Woo Park
 * Finished:
 * 
 * runs the BoidInterfacePanel application
 */

/** import standard java class object libraries */
import javax.swing.*;
import java.awt.*;

public class BoidsApp {
    /** main method */
    public static void main(String args[]){
	// instantiate simulation object
	BoidsSimulation simulation = new BoidsSimulation();
	
	// create main window
	JFrame mainWindow = new JFrame("Boid Control Panel");

	// main window setting
	mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	mainWindow.getContentPane().add(new BoidInterfacePanel(simulation));
	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainWindow.pack();
	mainWindow.setVisible(true);
    }
}
