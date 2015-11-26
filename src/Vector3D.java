import static java.lang.Math.*;
public class Vector3D implements Cloneable{
    public double x, y, z;
    public Vector3D(double X, double Y, double Z){
    	x = X;
    	y = Y;
    	z = Z;
    }
    
    public String toString() {
    	String a = "(" + x + ", " + y + ", " + z +")";
    	return a;
    }
    
    public Vector3D clone() throws CloneNotSupportedException {
    	Vector3D copy = new Vector3D (x, y, z);
    	copy.x = x;
    	copy.y = y;
    	copy.z = z;
    	return copy;
    }
    
    public double L2norm() {
    	double norm = sqrt(pow(x,2)+pow(y,2)+pow(z,2));
    	return norm;
    }
    
    public double dotProduct(Vector3D v) {
    	double dp = (x*v.x) + (y*v.y) + (z*v.z);
    	return dp;
    }
    
    public Vector3D crossProduct(Vector3D v){
    	Vector3D cp = new Vector3D(x, y, z);
    	double xcp = (y*v.z)- (z*v.y);
    	double ycp = (z*v.x)- (x*v.z);
    	double zcp = (x*v.y)- (y*v.x);
    	cp.x = xcp; 
    	cp.y = ycp;
    	cp.z = zcp;
    	return cp;
    }
    
    public void normalize() {
    	x = x/L2norm();
    	y = y/L2norm();
    	z = z/L2norm();
    }
    
    public Vector3D transform(Matrix m) {
    	Vector3D b = new Vector3D(0, 0, 0);
		b.x = m.m[0][0]*x + m.m[1][0]*y + m.m[2][0]*z;
		b.y = m.m[0][1]*x + m.m[1][1]*y + m.m[2][1]*z;
		b.z = m.m[0][2]*x + m.m[1][2]*y + m.m[2][2]*z;
		return b;
    }
}
