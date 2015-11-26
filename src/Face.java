import java.awt.Color;

public class Face {
	public int[] index;
	public Color color;
	
	
	public Face(int[] i, Color c) {
		index = i;
		color = c;
	}
	
	public String toString() {
		String a = "";
		for (int j=0; j<index.length; j++) {
			a = a + index[j] + " ";
		}
		a = a + "\n" + color;
		return a;
	}
}


