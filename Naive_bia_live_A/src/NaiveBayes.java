import javax.swing.*;
import java.awt.*;
import java.beans.FeatureDescriptor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by swakkhar on 4/10/17.
 */
public class NaiveBayes {
	public int totDataPoint = 5000;
	public int totFeature = 400;
    public  int X[][]=new int [5000][400];
    public  int y[] = new int[5000];
    public  void readData() throws IOException {
        BufferedReader readerX = new BufferedReader(new FileReader("data//X.csv"));
        BufferedReader readerY = new BufferedReader(new FileReader("data//Y.csv"));
        String xStr=null;
        String yStr=null;
        int i=0;
        while (true)
        {
            xStr=readerX.readLine();
            yStr=readerY.readLine();
            if (xStr==null)break;

            int j=0;
            for (String x:xStr.split(",")) {
                X[i][j]=Integer.parseInt(x);
                j++;
            }
            y[i]=Integer.parseInt(yStr);
            i++;

        }
    }

    public static void main(String[] args) throws IOException {
        NaiveBayes nb = new NaiveBayes();
        nb.readData();
        
        nb.predict(4500);

        //new DigitDisplay(nb.X[0]);
    }

	private void predict(int predictionIndex) {
		// TODO Auto-generated method stub
		int finalClass = -1;
		double maxProb = Double.MIN_VALUE;
		for(int classConsidered = 1;classConsidered<=10;classConsidered++){
			double predProb = getClassScore(classConsidered,predictionIndex);
			
			//Do yourself
			//update the value of finalClass and maxProb
			if(predProb>maxProb) {
				maxProb=predProb;
				finalClass=classConsidered;
			}
		}
		System.out.println(finalClass);
	}

	private double getClassScore(int classConsidered, int predictionIndex) {
		// TODO Auto-generated method stub
		double classProb = 1.0;
		
		int numClassMember = countClassMember(classConsidered,predictionIndex);
		classProb *= (numClassMember + 0.1)(/totDataPoint-1);
		
		for(int j=0;j<totFeature;j++){
			classProb *= calcProbForThatFeatureInTheClass(classConsidered,j,predictionIndex);
		}
		
		return classProb;
	}
    
	//!!!!Do Yourself
	private double calcProbForThatFeatureInTheClass(int classConsidered, int featureIndex,
			int predictionIndex) {
		int numClsMbr=countClassMember(classConsidered, predictionIndex);
		int cnt=0;
		for(int i=0;i<totDataPoint;i++) {
			if(i!=predictionIndex && y[i]==classConsidered && X[i][featureIndex]==X[predictionIndex][featureIndex])
				cnt++;
		}
		double p = (cnt+0.1)/(numClsMbr+0.1);
		return p;
	}

	private int countClassMember(int classConsidered, int predictionIndex) {
		// TODO Auto-generated method stub
		int memCount = 0;
		
		for(int i=0;i<totDataPoint;i++){
			if(i == predictionIndex){
				continue;
			}
			
			if(y[i] == classConsidered){
				memCount++;
			}
		}
		
		return memCount;
	}
}
class DigitDisplay extends JFrame
{
    int [][] data= new int[20][20];
    public DigitDisplay(int [] d)
    {
        super("Digit Display");
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
                data[i][j]=d[i*20+j];
        setSize(400,400);
        setVisible(true);
    }
    public void paint(Graphics g)
    {
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
            {
                if(data[i][j]==1)
                    g.fillRect(i*20,j*20,20,20);
            }
    }
}
