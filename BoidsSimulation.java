
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This application simulation the flocking of animals.
 * This source file updates and renders the boid instances.
 * 
 * Started: 11th August 2015, Time: 2329
 * @author Jung Woo (Noel) Park, Philip Anderson
 * Finished:
 */
public class BoidsSimulation {
    @SuppressWarnings("unchecked")
    private final ArrayList<Boid> boids = new ArrayList();
    private final Random random = new Random();

    /** limit for boid particles */
    private final int LIMIT = 1000;
    private final double SPEED_LIMIT = 5.0;

    public void initBoids(int nBoids) {

	if (nBoids > LIMIT) throw new IllegalArgumentException();

	boids.clear();
	
	for (int i = 0; i < nBoids; i += 1) boids.add(new Boid(random));
    }

    public void update() {
	for(Boid b : boids) {
	    b.getVelocity().add(rule1(b));
	    b.getVelocity().add(rule2(b));
	    b.getVelocity().add(rule3(b));
	    //limitSpeed(b);
	    b.getPosition().add(b.getVelocity());
	}
    }

    public void render(Graphics g) {
	boids.forEach(boid -> boid.display(g));
    }

    private Vector2 rule1(Boid boid) {
	Vector2 centre = new Vector2();

	for(Boid b : boids) {
	    if(boid != b) centre.add(b.getPosition());
	}

	centre.divide(boids.size() - 1);
	centre.subtract(boid.getPosition());
	//centre.normalise();
	//centre.divide(2500); // what is this?
	centre.divide(100);
	
	return centre;
    }

    private Vector2 rule2(Boid boid) {
	Vector2 result = new Vector2();

	for(Boid b : boids) {
	    if(b == boid) continue;
	    
	    Vector2 difference = Vector2.subtract(b.getPosition(), boid.getPosition());
	    
	    if(difference.getLength() < 7) {
		difference.inverse();
		result.subtract(difference);
	    }
	    
	}
	
	if(boid.getPosition().x < 10) result.add(new Vector2(1, 0));
	if(boid.getPosition().x > 1810) result.add(new Vector2(-1, 0));
	if(boid.getPosition().y < 10) result.add(new Vector2(0, 1));
	if(boid.getPosition().y > 1070) result.add(new Vector2(0, -1));
	
	if(result.x != 0 || result.y != 0) result.normalise();
	
	//result.divide(1000);
	result.divide(8);
	
	return result;
    }

    private Vector2 rule3(Boid boid) {
	Vector2 velocity = new Vector2();
	
	for(Boid b : boids) {
	    if(boid != b) velocity.add(b.getVelocity());
	}
	
	velocity.divide(boids.size() - 1);
	velocity.subtract(boid.getVelocity());
	//velocity.normalise();
	//velocity.divide(3000);
	velocity.divide(100);
	
	return velocity;
    }
    
    private void limitSpeed(Boid b) {
	if(b.getVelocity().getLength() > SPEED_LIMIT) {
	    b.getVelocity().normalise();
	    b.getVelocity().multiply(SPEED_LIMIT);
	}
    }
}
