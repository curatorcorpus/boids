
public class Vector2 {
    
    public double x, y;   
    
    public static Vector2 subtract(Vector2 v1, Vector2 v2) {
	return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }
     
    public Vector2() {
	this.x = 0;
	this.y = 0;
    }
    
    public Vector2(double x, double y) {
	this.x = x;
	this.y = y;
    }
    
    public void add(Vector2 vec) {
	this.x += vec.x;
	this.y += vec.y;
    }
    
    public void subtract(Vector2 vec) {
	this.x -= vec.x;
	this.y -= vec.y;
    }
    
    public double getLength() {
	return Math.sqrt(x * x + y * y);
    }
    
    public void divide(double scalarVal) {
	this.x /= scalarVal;
	this.y /= scalarVal;
    }
    
    public void multiply(double scalarVal) {
	this.x *= scalarVal;
	this.y *= scalarVal;
    }
    
    public void inverse() {
	this.x = 1 / this.x;
	this.y = 1 / this.y;
    }
    
    public void normalise() {
	double length = getLength();
	this.x /= length;
	this.y /= length;
    }
    
    public String toString() {
	return "Point X: " + x + " Point Y: " + y;
    }
}
