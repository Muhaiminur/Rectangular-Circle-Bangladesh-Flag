/*Classwork 2
-Take input  for 10 lines from a text file ( pair of start and end point)
-Determine slope for each line 
-Implement DDA algorithm
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.jws.soap.SOAPBinding;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Circle_Inside_Rectangular_Bangladesh_Flag implements GLEventListener {

	static GLProfile profile = GLProfile.get(GLProfile.GL2);
	static GLCapabilities capabilities = new GLCapabilities(profile);
	// The canvas
	static GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {
		// getting the capabilities object of GL2 profile

		Circle_Inside_Rectangular_Bangladesh_Flag l = new Circle_Inside_Rectangular_Bangladesh_Flag();
		// creating frame
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(600, 600);

		final JFrame frame = new JFrame("straight Line");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);

	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL2.GL_POINTS);// static field

		drawRect(gl);
		drawCir(gl);
		gl.glEnd();
	}

	public void drawCir(GL2 gl) {
		// input
		double r = 0.25;
		while(r>0){
			r=r-0.0001;
		// initializing
		double x = 0.0, y = r;
		double x0 = 0.0;
		double y0 = 0.0;

		//draw8Lines(gl, x0, y0, x, y);// draw first point

		double p = (5 / 4) - r;

		while (x <= y) {
			if (p < 0) {// midpoint is inside the circle
				p += (2 * x) + 0.0001;
			} else {// midpoint outside the circle
				p += -(2 * y) + (2 * x) + 0.0001;
				y = y - 0.001;
			}
			x = x + 0.001;

			 gl.glColor3f(255f, 0f, 0f);
			 drawPoints(gl, x, y);
		    //draw8Lines(gl, x0, y0, x, y);
		}
		}
		//gl.glEnd();
	}

	public void drawRect(GL2 gl) {
		double left = -0.7;
		double right = 0.7;
		double up = 0.4;
		double down = -0.4;

		while (up >= down) {

			double x1 = left;
			double y1 = up;
			double x2 = right;
			double y2 = up;

			if (x1 > x2) {
				double temp1 = x1;
				double temp2 = y1;
				x1 = x2;
				y1 = y2;
				x2 = temp1;
				y2 = temp2;
			}

			double m = (y2 - y1) / (x2 - x1);
			//System.out.println("Slope m: " + m);

			Double mScale = m / 1000;

			for (; x1 <= x2;) {

				gl.glColor3f(0f, 153f, 0f);
				gl.glVertex2d(x1, y1);

				if (-1 < mScale && mScale < 1) {
					x1 = x1 + 0.001;
					y1 = y1 + mScale;
				} else {
					y1 = y1 + 0.001;
					x1 = x1 + (1 / mScale);
				}
			}

			up = up - 0.001;
		}
		//gl.glEnd();

	}

	public void draw8Lines(GL2 gl, double x1, double y1, double x2, double y2) {
		
		//using DDA algo
		drawLn(gl, x1, y1, x2, y2);// zone 0
		drawLn(gl, x1, y1, x2, -y2);// zone 7
		drawLn(gl, x1, y1, -x2, y2);// zone 3
		drawLn(gl, x1, y1, -x2, -y2);// zone 4
		drawLn(gl, x1, y1, y2, x2);// zone 1
		drawLn(gl, x1, y1, y2, -x2);// zone 6
		drawLn(gl, x1, y1, -y2, x2);// zone 2
		drawLn(gl, x1, y1, -y2, -x2);// zone 5

		//using line
//		drawLine(gl, x1, y1, x2, y2);// zone 0
//		drawLine(gl, x1, y1, x2, -y2);// zone 7
//		drawLine(gl, x1, y1, -x2, y2);// zone 3
//		drawLine(gl, x1, y1, -x2, -y2);// zone 4
//		drawLine(gl, x1, y1, y2, x2);// zone 1
//		drawLine(gl, x1, y1, y2, -x2);// zone 6
//		drawLine(gl, x1, y1, -y2, x2);// zone 2
//		drawLine(gl, x1, y1, -y2, -x2);// zone 5
	}

	// take gl, x and y. then draw points in 8 way sym
	public void drawLn(GL2 gl, double x1, double y1, double x2, double y2) {
		if (x1 > x2) {
			double temp1 = x1;
			double temp2 = y1;
			x1 = x2;
			y1 = y2;
			x2 = temp1;
			y2 = temp2;
		}

		double m = (y2 - y1) / (x2 - x1);
		System.out.println("Slope m: " + m);

		Double mScale = m / 1000;

		for (; x1 <= x2;) {

			gl.glColor3f(255f, 0f, 0f);
			gl.glVertex2d(x1, y1);

			if (-1 < mScale && mScale < 1) {
				x1 = x1 + 0.001;
				y1 = y1 + mScale;
			} else {
				y1 = y1 + 0.001;
				x1 = x1 + (1 / mScale);
			}
		}
		// gl.glEnd();

	}
	
	//take gl, x and y. then draw points in 8 way sym
		public void drawPoints(GL2 gl, double x, double y) {
			gl.glVertex2d(x, y);//zone 0
			
			gl.glVertex2d(x, -y);//zone 7

			gl.glVertex2d(-x, y);//zone 3

			gl.glVertex2d(-x, -y);//zone 4

			gl.glVertex2d(y, x);//zone 1

			gl.glVertex2d(y, -x);//zone 6

			gl.glVertex2d(-y, x);//zone 2

			gl.glVertex2d(-y, -x);//zone 5

		}

	// take gl, x1, y1, x2, y2 and draw line using GL_LINES
	public void drawLine(GL2 gl, double x1, double y1, double x2, double y2) {
		gl.glBegin(GL2.GL_LINES);
		gl.glBegin(1);
		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
		gl.glEnd();
	}

	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	public void init(GLAutoDrawable drawable) {
		// method body
		// 4. drive the display() in a loop
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// method body
	}
	// end of main
}// end of classimport javax.media.opengl.GL2;