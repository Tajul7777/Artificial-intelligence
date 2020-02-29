import java.util.ArrayList;
import java.util.Scanner;

public class Graph {


    static int x,y, d1, d2;
    int matrix[][] = new int[20][20];
    public Node startNode(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter matrix size x,y");
        x=scanner.nextInt();
        y=scanner.nextInt();
        System.out.println("Enter Matrix");

        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                matrix[i][j]=scanner.nextInt();
            }
        }

        System.out.println("Source");

        int s1=scanner.nextInt();
        int s2=scanner.nextInt();
        System.out.println("Destination");
        d1 =scanner.nextInt();
        d2 =scanner.nextInt();
        Node stNode = new Node(0,s1,s2);
        return stNode;
    }

    public boolean reachedDestination(Node curNode){
        if(curNode.x== d1 && curNode.y== d2)
            return  true;
        else
            return false;
    }

    private int simpleHeuristics(Node curNode) {

        int a=Math.abs(curNode.x-d1);
        int b=Math.abs(curNode.y-d2);
        return a+b;
    }

    public ArrayList<Node> getNeighbours(Node curNode){

        ArrayList<Node> neighbours = new ArrayList<Node>();


        if(curNode.x>0)
        {
            if(matrix[curNode.x-1][curNode.y]==1)
            {
                Node node = new Node(curNode.cost,curNode.x-1,curNode.y);
                node.predictedCost=simpleHeuristics(node);
                neighbours.add(node);
            }
        }
        if(curNode.x<x-1)
        {
            if(matrix[curNode.x+1][curNode.y]==1)
            {
                Node node = new Node(curNode.cost,curNode.x+1,curNode.y);
                node.predictedCost=simpleHeuristics(node);
                neighbours.add(node);
            }
        }
        if(curNode.y>0)
        {
            if(matrix[curNode.x][curNode.y-1]==1)
            {
                Node node = new Node(curNode.cost,curNode.x,curNode.y-1);
                node.predictedCost=simpleHeuristics(node);
                neighbours.add(node);
            }
        }
        if(curNode.y<y-1)
        {
            if(matrix[curNode.x][curNode.y+1]==1)
            {
                Node node = new Node(curNode.cost,curNode.x,curNode.y+1);
                node.predictedCost=simpleHeuristics(node);
                neighbours.add(node);
            }
        }

        return neighbours;
    }
}
