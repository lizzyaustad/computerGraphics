
public class Point3D {
public double x, y, z;
    
	public Point3D(double X, double Y, double Z){
	    x = X;
	    y = Y;
	    z = Z;
	}
    
	public double distance(Point3D p) {
	    double d = Math.sqrt(Math.pow(x-p.x,2)+Math.pow(y-p.y,2)+Math.pow(z-p.z,2));
	    return d;
	}
    
	public Point3D transform(Matrix m){
		Point3D b = new Point3D(0, 0, 0);
		b.x = m.m[0][0]*x + m.m[1][0]*y + m.m[2][0]*z + m.m[3][0];
		b.y = m.m[0][1]*x + m.m[1][1]*y + m.m[2][1]*z + m.m[3][1];
		b.z = m.m[0][2]*x + m.m[1][2]*y + m.m[2][2]*z + m.m[3][2];
		return b;
	}
	
	public String toString(){
		String a = "(" + x + ", " + y + ", " + z + ").";
		return a;
	}
	
	public Vector3D vector(Point3D p){/* return the vector that is between this point and p.*/
		Vector3D c = new Vector3D(0, 0, 0);
		c.x = p.x - x;
		c.y = p.y - y;
		c.z = p.z - z;
		return c;
	}
	
	public Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3){
		Vector3D v1 = p1.vector(p2);
		Vector3D v2 = p1.vector(p3);
		Vector3D n = v1.crossProduct(v2);		
		return n;
	}
	
	public boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn){
		Vector3D n = faceNormal(p1, p2, p3);
		double dp = n.dotProduct(vpn);
		if (dp >= 0){
			return true;
		}
		else return false;
	}
  
}
