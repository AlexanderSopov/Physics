package Vector;

import java.awt.Point;

public class Vectors {

	
	protected static int[] addVectors(int[] v1, int[] v2) {
		int length = v1.length;
		if(length != v2.length)
			throw new NullPointerException();
		
		int[] addedVector = new int[length];
		
		for(int i=0;i<length;i++)
			addedVector[i] = v1[i]+v2[i];

		return addedVector;
	}


	protected static int[] scaleVector(int[] v, float t) {
		int[] scaled = new int [v.length];
		for(int i = 0; i<v.length; i++)
			scaled[i] = (int) (v[i] * t);
		return scaled;
	}


	
	protected static int[] subtractVectors(int[] v1, int[] v2) {
		int length = v1.length;
		if(length != v2.length)
			throw new NullPointerException();
		
		int[] subtractedVector = new int[length];
		
		for(int i=0;i<length;i++)
			subtractedVector[i] = v1[i]-v2[i];

		return subtractedVector;
	}


	protected float dotProduct(int[] v1, int[] v2) {
		int length = v1.length;
		
		if(length != v2.length)
			throw new NullPointerException();
		
		float dotProduct = 0/1;
		
		for(int i=0;i<length;i++)
			dotProduct += v1[i]*v2[i];
		
		return dotProduct;
	}
	
	
}
