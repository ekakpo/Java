/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math3030;

import java.util.Scanner;

/**
 *
 * @author Kheops
 */


public class Math3030 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Point P = InputOutput.promptforPoint();
        Line l = InputOutput.promptforLine();
        InputOutput.printPointOnLine_AndPlane(l.goesThrough(P),l,P);
        InputOutput.printQuestion3(P, l);
        InputOutput.printQuestion4(P, l);        
    }
}


class Point
{
    double p1, p2, p3;
    
    Point(double p1, double p2, double p3)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    Vector findVector(Point endPoint)
    {
        Vector outputVector = new Vector(endPoint.p1-this.p1,endPoint.p2-this.p2,endPoint.p3-this.p3);
        return outputVector;
    }
    
}

class Vector
{
    double v1, v2, v3;
    
    Vector(double v1, double v2, double v3)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    
    Vector cross(Vector rightVector)
    {
        double x, y, z;
        x = y = z = 0;
        x = ((v2*rightVector.v3) - (rightVector.v2*v3));
        y = ((v1*rightVector.v3) - (rightVector.v1*v3));
        z = ((v1*rightVector.v2) - (rightVector.v1*v2));
        
        Vector v = new Vector(x,y,z);return v;
        
    }
    
    Point findCorrespondingEndpoint(Point p)
    {
        Point q;
        
        double x, y, z;
        x = y = z = 0;
        x = v1 + p.p1;
        y = v2 + p.p2;
        z = v3 + p.p3;
        q = new Point(x,y,z);
        
        return q; 
    }
}

class Line
{
    Point initialPoint;
    Vector directionVector;
    
    Line(Point init, Vector dirVec)
    {
        initialPoint = init;
        directionVector = dirVec;
    }
    
    boolean goesThrough(Point p)
    {
        boolean answer = false;
        
        if((p.p1 - initialPoint.p1)/directionVector.v1 ==  (p.p2 - initialPoint.p2)/directionVector.v2 && 
                (p.p1 - initialPoint.p1)/directionVector.v1 == (p.p3 - initialPoint.p3)/directionVector.v3)
        {
            answer = true;
        }
        
        return answer;
    }
    
    Plane findPlane(Point p)
    {
        Vector P0P = initialPoint.findVector(p);
        Vector nVector = P0P.cross(directionVector);
        return new Plane(nVector,this.initialPoint);
    }
    
    
    String returnString()
    {
        String output = "";
        
        output = String.format("\n   x = %.1f + (%.1f)t\nl: y = %.1f + (%.1f)t\n   z = %.1f + (%.1f)t",
                    initialPoint.p1,directionVector.v1,initialPoint.p2,directionVector.v2,
                    initialPoint.p3,directionVector.v3);
        
        return output;
    }    
}

class Plane
{
    Vector normalVector;
    Point pIP;
    
    Plane(Vector normVec, Point p)
    {
        normalVector = normVec;
        pIP = p;
    }
    
    String returnPlane()
    {
        String output = "";
        
        output = String.format("Plane: %.1f(x - %.1f) - %.1f(y - %.1f) + %.1f(z - %.1f) = 0",normalVector.v1,pIP.p1,
                normalVector.v2,pIP.p2,normalVector.v3,pIP.p3);
        
        return output;
    }
}

class InputOutput
    {
        static Point promptforPoint()
        {
            System.out.println("Enter the coordinates of the point separated by spaces: "); 
            Scanner scan = new Scanner(System.in);
            Point P = new Point(Double.parseDouble(scan.next()),Double.parseDouble(scan.next()),Double.parseDouble(scan.next()));
            return P;
        }
        
        static Line promptforLine()
        {
            System.out.println("Enter the coordinates of the initial point: "); 
            Scanner scan = new Scanner(System.in);
            Point P = new Point(Double.parseDouble(scan.next()),Double.parseDouble(scan.next()),Double.parseDouble(scan.next()));
            
            System.out.println("Enter the coordinates of the direction vector: ");
            Vector V = new Vector(Double.parseDouble(scan.next()),Double.parseDouble(scan.next()),Double.parseDouble(scan.next()));
            
            Line l = new Line(P,V);
            return l;
        }
        
        static void printPointOnLine_AndPlane(boolean input, Line l, Point p)
        {
            if (input)
                System.out.println("The point is on the line.");
            else
            {
                System.out.println("The point is not on the line.");
                System.out.println("The plane that contains L and P is: " + l.findPlane(p).returnPlane());
            }
        }
        
        static void printQuestion3(Point p, Line l)
        {
            Plane plane = new Plane(l.directionVector,p);
            System.out.println("The plane that passes through P perpendicular to L is: " + plane.returnPlane());
        }
        
        static void printQuestion4(Point P, Line l)
        {
            Vector P0P = new Vector(P.p1 - l.initialPoint.p1, P.p2 - l.initialPoint.p2, P.p3 - l.initialPoint.p3);
            Point newInitialPoint = P0P.findCorrespondingEndpoint(P);
            Line line = new Line (newInitialPoint,l.directionVector);
            System.out.println("The line that is symmetric to L about P is : " + line.returnString());
        }        
    }