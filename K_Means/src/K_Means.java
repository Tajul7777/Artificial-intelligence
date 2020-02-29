import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class K_Means {
	
	
    double [][] X = null;
    int numClusteres = 0;
    int totDataPoints = 10000;
    int numFeatures = 2;
    double[][] centroids = null;
    int []clusterId = null;
	private int numIteration = 5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		K_Means km = new K_Means(4);
		km.readData();
		//DO YOURSELF!!!!
		km.assignValuesToCentroid();
		//Do YOURSELF!!!!
		km.assignClusters();
	}

	public K_Means(int numClusteres) {
		super();
		this.numClusteres = numClusteres;
		X = new double[totDataPoints][numFeatures];
		clusterId = new int[totDataPoints];
		this.centroids = new double[numClusteres][numFeatures];
	
	}

	private void assignValuesToCentroid() {
		//randomly pick a data point for each centroid
		//assign the values of that data point to the centroid
		Random rand = new Random();

		for(int i=0;i<numClusteres;i++){
			int ind = rand.nextInt(totDataPoints);
			centroids[i][0]=X[ind][0];
			centroids[i][1]=X[ind][1];


		}
		
		
	}

	private void assignClusters() {

		for(int i=0;i<numIteration ;i++){
			//DO YOURSELF!!!
			updateClusterIdForDataPoints();
			//DO YOURSELF!!!
			updateCentroids();
		}
		
	}

	private void updateCentroids() {

		//Go through each of the centroids
			//find all the data points that are assigned to that centroid
			//calculate the average value of the features of those data points
			//assign the calculated average values to those centroids

		int[][] sum = new int[numClusteres][numFeatures];
		int[] freq = new int[numClusteres];
		Arrays.fill(sum,0);
		Arrays.fill(freq,0);
		for(int i=0;i<totDataPoints;i++){
			sum[clusterId[i]][0]+=X[i][0];
			sum[clusterId[i]][1]+=X[i][1];
			freq[clusterId[i]]++;
		}


		for(int i=0;i<numClusteres;i++) {
			centroids[i][0]=sum[i][0]/freq[i];
			centroids[i][1]=sum[i][1]/freq[i];
		}
	}

	//DO YOURSELF
	private void updateClusterIdForDataPoints() {

		//for each of the data points:
		    //go through each of the centroids
		    //calculate distance between the data point and that centroid
		    //remember the centroid with minimum distance and set centroidId accordingly for that datapoint

		double minDistance=Integer.MAX_VALUE;
		for(int i=0;i<totDataPoints;i++){
			for(int j=0;j<numClusteres;i++){
				double distance=Math.abs(X[i][0]-centroids[j][0]) + Math.abs(X[i][1]-centroids[j][1]);
				if(distance<minDistance){
					clusterId[i]=j;
					minDistance=distance;
				}
			}

		}

	}

	private void readData()  {

		try {
			int curLineNo = 0;
			BufferedReader br = new BufferedReader(new FileReader(new File("3D_spatial_network.txt")));
			
			while(curLineNo < totDataPoints){
				String line = br.readLine();
			    String[] words = line.split(",");
			    X[curLineNo][0] = Double.parseDouble(words[2]);
			    X[curLineNo][1] = Double.parseDouble(words[3]);
			    curLineNo++;
				
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		//for(int i=0;i<totDataPoints;i++)System.out.println(Arrays.toString(X[i]));
	}

}
