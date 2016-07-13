import java.util.Random;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * This application simulation the flocking of animals.
 * This source file describes a boid entity.
 * 
 * Started: 11th August 2015, Time: 2329
 * @author Jung Woo (Noel) Park, Philip Anderson
 * Finished:
 */
public class Boid {
    
    public final static double DIAMETER = 5.0;
    
    private final Vector2 position = new Vector2();
    private final Vector2 velocity = new Vector2();
    private final Color color;
    
    public Boid(Random random) {
	this.color = new Color(random.nextInt(246)+10,
				random.nextInt(246)+10, 
				random.nextInt(246)+10);

	double angle = random.nextDouble() * 2 * Math.PI;
	
	this.velocity.x = Math.sin(angle) / 10;
	this.velocity.y = Math.cos(angle) / 10;
	this.position.x = random.nextDouble() * 1920.0;
	this.position.y = random.nextDouble() * 1080.0;

    }
    
    public void display(Graphics g){
	Graphics2D display = (Graphics2D) g;

	display.setColor(this.color);
	display.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				 RenderingHints.VALUE_ANTIALIAS_ON);
	
	display.fill(new Ellipse2D.Double(position.x, position.y,
					  DIAMETER, DIAMETER)); 
    }

    public Vector2 getVelocity() {
	return velocity;
    }
    
    public Vector2 getPosition() {
	return position;
    }
}
