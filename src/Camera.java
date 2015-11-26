public class Camera {
  public Vector3D getVPN(){/*return a vector that points towards the viewer. Used for face orientation*/
	  Vector3D a = new Vector3D(0,0,1);
	    
	  return a; 
  }

  protected Point3D cameraTransform(final Point3D p){
	  Point3D t = new Point3D (0,0,0);
	  t.x = p.x;
	  t.y = p.y;
	  t.z = p.z;
	  return t;
  }

  protected Point3D projectionTransform(final Point3D p){
	  Point3D t = new Point3D (0,0,1);
	  t.x = p.x;
	  t.y = p.y;
	  return t;
  } 

  private final Point3D viewportTransform(final Point3D p){
	  Point3D t = new Point3D(0, 0, 1);
	  t.x = ax + bx*p.x;
	  t.y = ay + by*p.y;
	  
	  return t;
  }

  public final Point3D project(final Point3D p){
    Point3D temp=cameraTransform(p);
    temp=projectionTransform(temp);
    return viewportTransform(temp);
  }

  public Camera(double xmin_, double xmax_, double ymin_, double ymax_){
	  xmin = xmin_;
	  xmax = xmax_;
	  ymin = ymin_;
	  ymax = ymax_;
  }

  public void setViewport(int width, int height){/*calculate ax, bx, ay, by*/
	  bx = width / (xmax - xmin);
	  ax = 0 - (bx*xmin);
	  by = height / (ymax - ymin);
	  ay = 0 - (by*ymin);				
  }

  public String toString(){/* Make it look nice to save your debugging time! */
	  String answer = "bx: " + bx +"\nax: " +ax + "\nby: " +by + "\nay: " +ay;
	  return answer;		 
  }

  private double xmin, xmax, ymin, ymax;
  private double fcp, bcp;  //NOT USED: front & back clipping planes
  private double ax, bx, ay, by;
}

