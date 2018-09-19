
public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	//constructor 1 
	
	/**
	 * Create a Body from parameter
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity 
	 * @param yv initial y velocity 
	 * @param mass of object 
	 * @param filename of image for object animation
	 */
	
	public Body(double xp, double yp, double xv,
			double yv, double mass, String filename) {
		

		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		
	}
	
	// second constructor
	/**
	 * copy constructor: copy instance variables from
	 * one body to this body 
	 * @param b
	 */
	
	public Body(Body b) {
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		myFileName = b.myFileName;
		
	}
		
	
	/**
	 * return x coordinate position of this Body
	 * @return value of x position
	 */

	public double getX() {
		return myXPos;
		
	}
	
	/**
	 * return y coordinate position of this Body
	 * @return value of y position
	 */

	public double getY()  {
		return myYPos;
 
	} 
	/**
	 * return x-velocity of this Body
	 * @return value of x-velocity
	 */
	
	public double getXVel() {
		return myXVel;
		
	}
	
	/**
	 * return y-velocity of this Body
	 * @return value of y-velocity
	 */

	public double getYVel() {
		return myYVel;
		
	}
	/**
	 * return mass of this Body
	 * @return value of mass
	 */

	public double getMass() {
		return myMass; 

	}
	
	/**
	 * get the filename
	 * @return name of the file
	 */
	
	public String getName() {
		return myFileName;
	
	
	}
	/**
	 * return Distance between this body and the other
	 * @param b the other body to which distance is calculated 
	 * @return distance between this body and b
	 */
  
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow( b.myXPos - this.myXPos,2)+Math.pow(b.myYPos - this.myYPos, 2));
		
	}
	
	/**
	 * return force exerted on this body by the other 
	 * @param p the other body to which force is being exerted by
	 * @return force exerted on this body by p
	 */
	public double calcForceExertedBy(Body p) {
		return ((6.67 * Math.pow(10, -11)) * ((this.myMass * p.myMass) / Math.pow(calcDistance(p), 2)));
	}
	
	/**
	 * return force exerted in the x direction
	 * @param p the other body to which force is being exerted by
	 * @return force exerted on the body in the x direction
	 */
	public double calcForceExertedByX(Body p) {
		return ((calcForceExertedBy(p) * ((p.myXPos - this.myXPos) / calcDistance(p))));
	}
	
	/**
	 * return force exerted in the y direction
	 * @param p the other body to which force is exerted on
	 * @return force exerted on the body in the y direction
	 */
	
	public double calcForceExertedByY(Body p) {
		return((calcForceExertedBy(p) * ((p.myYPos - this.myYPos) / calcDistance(p))));
		
	} 
	/**
	 * return the net force in the x direction
	 * exerted on this body by all the bodies
	 * @param bodies to which the net force is being exerted on this body 
	 * @return net force exerted in x direction on body by bodies
	 */
	
	public double calcNetForceExertedByX(Body [] bodies) {
		double sum = 0.0;
		
		for(Body b: bodies) {
			if (! b.equals(this)) 
				sum += calcForceExertedByX(b); 
		}
		
		return sum;
			
		}
	
	/**
	 * return the net force in the y direction
	 * exerted on this body by all the bodies
	 * @param bodies to which the net force is being exerted on this body
	 * @return net force exerted in y direction on body by bodies
	 */
	
	public double calcNetForceExertedByY(Body [] bodies) {
		double sum = 0.0;
		
		for(Body b: bodies) {
			if (! b.equals(this)) 
				sum += calcForceExertedByY(b);
		
		}
		
		return sum;
		
	}
	
	/**
	 * updates the instance variables of body object called
	 * @param deltaT
	 * @param xforce
	 * @param yforce
	 */
	
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		
		double nvx = myXVel + (deltaT * ax);
		double nvy = myYVel + (deltaT * ay);
		
		double nx = myXPos + (deltaT * nvx);
		double ny = myYPos + (deltaT * nvy);
		
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		
	}
	/**
	 * draw method 
	 */
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
		
		}
	

