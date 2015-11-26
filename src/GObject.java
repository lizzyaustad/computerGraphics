import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class GObject {
	
	
	public Point3D[] vertex;
	public Face[] face;
	
	public GObject(Point3D[] v, Face[] f) {
		vertex = v;
		face = f;
	}
	
	public GObject(String fileName) {
		File file = new File(fileName);
		
		try {
			
		Scanner scanner = new Scanner(file);
			int numvertices = scanner.nextInt();
			Point3D[] vertices = new Point3D[numvertices];
			for (int i=0; i<numvertices; i++) {
				Point3D a = new Point3D (scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
				vertices[i] = a;
			}
			
			int numfaces = scanner.nextInt();
			Face[] faces = new Face[numfaces];
			for (int j=0; j<numfaces; j++) {
				int vinface = scanner.nextInt();
				int[] index = new int[vinface];
				for (int k=0; k<vinface; k++) {
					index[k] = scanner.nextInt();
				}
				Color c = new Color(scanner.nextFloat(), scanner.nextFloat(), scanner.nextFloat());
				Face a = new Face(index, c);
				faces[j] = a;
			}
			vertex = vertices;
			face = faces;
			
			
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public String toString() {
		String answer = "";
		answer = answer + vertex.length +"\n";
		for (int l=0; l<vertex.length; l++) {
			Point3D a = vertex[l];
			answer = answer + a.toString() + "\n";
		}
		answer = answer + "\n" +"\n" + face.length;
		for (int m=0; m<face.length; m++) {
			Face a = face[m];
			answer = answer + a.toString() + "\n";
		}
		return answer;
	}
	
	public void transform(Matrix m) {
		for (int i=0; i<vertex.length; i++) {
			Point3D a = vertex[i];
			vertex[i] = a.transform(m);
		}
	}
}

