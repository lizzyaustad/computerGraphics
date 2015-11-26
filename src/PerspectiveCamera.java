public class PerspectiveCamera extends Camera
{
  public Vector3D getVPN(){/*return the view plan normal vector*/
	  Vector3D a = new Vector3D(0,0,0);
	  if ((cop.z - vrp.z) > 0){
		 a.z = 1;
	  }
	  else {
		  a.z = -1;
	  }
	  return a;
  }

  protected Point3D projectionTransform(final Point3D p){
	  Matrix pro = new Matrix();
	  pro.setIdentity();
	  double d = cop.distance(vrp);
	  Point3D result = new Point3D(0,0,0);
	  result.x = p.x / (p.z/d);
	  result.y = p.y / (p.z/d);
	  result.z = p.z / (p.z/d);
	  
	  if (cop.distance(new Point3D(0,0,0)) != 0){
		  result.x = p.x / (p.z/d + 1);
		  result.y = p.y / (p.z/d + 1);
		  result.z = p.z / (p.z/d + 1);
	  }	
	 
	 
	  
	  return result; 
	  
  }

  public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_){
	  super (xmin_, xmax_, ymin_, ymax_);
  }

  public void setupCOP(Point3D cop_){
	  cop.x = cop_.x;
	  cop.y = cop_.y;
	  cop.z = cop_.z;
  }     

  private Point3D cop=new Point3D(0,0,-4); //centre of projection
  
  protected Point3D cameraTransform(final Point3D p){
	  Point3D result = new Point3D(0,0,0);
	  
	  //normalize vpn
	  double magn = vpn.L2norm();
	  Vector3D n = new Vector3D(vpn.x/magn, vpn.y/magn, vpn.z/magn);
	  
	  Vector3D mu = vuv.crossProduct(n);
	  //normalize mu
	  double magmu = mu.L2norm();
	  Vector3D munorm = new Vector3D(mu.x/magmu, mu.y/magmu, mu.z/magmu);
	  
	  Vector3D v = n.crossProduct(munorm);
	  Matrix translation = new Matrix();
	  translation.setTranslation((0-vrp.x), (0-vrp.y), (0-vrp.x));
	  
	  Matrix rotation = new Matrix();
	  rotation.setIdentity();
	  rotation.setRot(0, munorm.x, munorm.y, munorm.z);
	  rotation.setRot(1, v.x, v.y, v.z);
	  rotation.setRot(2, n.x, n.y, n.z);
	  
	  Matrix transformation = rotation.multiply(translation);
	  result = p.transform(transformation);
	  
	  return result;
  }

  public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vuv_){
	  vrp = vrp_;
	  vpn = vpn_;
	  vuv = vuv_;
  }

  private Matrix m=new Matrix(); //camera transformation matrix
  { m.setIdentity(); 
  }
   
  private Point3D vrp=new Point3D(0,0,0); //view reference point: the origin of camera coordinating system
   
  private Vector3D vpn=new Vector3D(0,0,1), vuv=new Vector3D(0,1,0);  //view plane normal (axis n) and the view up vector (axis v)
}