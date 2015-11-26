import java.awt.*;

public class Scene
{
  private GObject[] obj;

  public Scene(String[] fileName){
	  
	  int len=fileName.length;
	  obj= new GObject[len];
	  for(int i=0; i<len;i++){
		  obj[i]=new GObject(fileName[i]);
	  }
  }
  
  public void transform(Matrix m){
	  for (int i=0; i<obj.length; i++) {
		  obj[i].transform(m);

	  }
  }

  
  
  public void draw(Camera c, Graphics g){
	  
	  for (int i=0; i<obj.length; i++) {
		  GObject a = obj[i];
		  
		  for (int j=0; j<a.face.length; j++) {
			  Face b = a.face[j];
			  Point3D p1 = a.vertex[b.index[0]];
			  Point3D p2 = a.vertex[b.index[1]];
			  Point3D p3 = a.vertex[b.index[2]];
			  Point3D p4 = new Point3D(0,0,0);
			  if (b.index.length > 3) {
				  p4 = a.vertex[b.index[3]];
				  p4 = c.project(p4);
			  }
			  Vector3D vpn = c.getVPN();
			  p1 = c.project(p1);
			  p2 = c.project(p2);
			  p3 = c.project(p3);
			  
			  if (p1.isFrontFace(p1, p2, p3, vpn) == true) {
				  int[] x = new int[b.index.length];
				  x[0] = (int)p1.x;
				  x[1] = (int)p2.x;
				  x[2] = (int)p3.x;
				  if (x.length > 3) {
					  x[3] = (int)p4.x;
				  }
				  int[] y = new int[b.index.length];
				  y[0] = (int)p1.y;
				  y[1] = (int)p2.y;
				  y[2] = (int)p3.y;
				  if (y.length > 3) {
					  y[3] = (int)p4.y;
				  }
				 
				  g.setColor(b.color);
				  
				  g.fillPolygon(x, y, x.length);
				  
			  }
			  
		  }
		  
		  
	  }
  }

  public String toString(){/* Make it look nice to save your debugging time! */
	  
	  String answer = "";
	  
	  for (int i=0; i<obj.length; i++){
		  GObject go = obj[i];
		  answer = answer + go.vertex.length +"\n";
		  for (int l=0; l<go.vertex.length; l++) {
			  Point3D a = go.vertex[l];
			  answer = answer + a.toString() + "\n";
		  }
		  answer = answer + "\n" +"\n" + go.face.length;
		  for (int m=0; m<go.face.length; m++) {
			  Face a = go.face[m];
			  answer = answer + a.toString() + "\n";
		  }
		
	  }
	  return answer;
  }
  
  
}
