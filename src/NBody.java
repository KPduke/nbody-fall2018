	

/**
 * @author Karly Pearson IN 201 @kep37
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));

			
		int n = s.nextInt();
		double r = s.nextDouble();
		
		s.close();
		
		return r;	
	}	
		
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// read # bodies, create array, ignore radius
			int n = s.nextInt();
			
			Body[] bodies = new Body[n];
			
			s.nextDouble();
			int nb = n; // # bodies to be read
			
			for(int k=0; k < nb; k++) {
				double xp = s.nextDouble();
				double yp = s.nextDouble();
				double xv = s.nextDouble();
				double yv = s.nextDouble();
				double mass = s.nextDouble();
				String filename = s.next();
					
				
				Body one = new Body(xp, yp, xv, yv, mass, filename);
				bodies[k] = one;
				// construct new body object and add to array	
			}
			
			s.close();
			
			// return array of body objects read
			return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			double[] xforce = new double[bodies.length];
			double[] yforce = new double[bodies.length];
			
			// create double arrays xforces and yforces
			// to hold forces on each body
		
			for(int k=0; k < bodies.length; k++) {
				xforce[k] = bodies[k].calcNetForceExertedByX(bodies);
				yforce[k] = bodies[k].calcNetForceExertedByY(bodies);
				
			// loop over all bodies, calculate
			// net forces and store in xforces and yforces
			
			}
			
			for(int k = 0; k < bodies.length; k ++) {
				bodies[k].update(dt, xforce[k], yforce[k]);
			}
				
			// loops over all bodies and call update
			// with dt and corresponding xforces, yforces values
		
				
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			 {
			for (Body b : bodies) {
				b.draw();
			}
			// loops over all bodies and call draw on each one
			
			StdDraw.show(10);
		}
	
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}

		
	}
	
}
