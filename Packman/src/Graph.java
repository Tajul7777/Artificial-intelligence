import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {


    private int x,y,destX,destY;
    int grid[][] =
            {
                    { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                    { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
                    { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                    { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 },
                    { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                    { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
                    { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                    { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 },
                    { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }
            };
    public Node startNode(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Source Coordinate");
        int sourceX=scanner.nextInt();
        int sourceY=scanner.nextInt();
        System.out.println("Enter Destination Coordinate");
        destX=scanner.nextInt();
        destY=scanner.nextInt();
        Node stNode = new Node(0,null,sourceX,sourceY);
        return stNode;

    }

    public boolean reachedDestination(Node curNode){
        if(curNode.currentPosX==destX && curNode.currentPosY==destY) return  true;
        else return false;
    }

    private int simpleHeuristics(Node curNode) {
        return Math.abs(curNode.currentPosX-destX)+Math.abs(curNode.currentPosY-destY);
    }

    public ArrayList<Node> getNeighbours(Node curNode){

        ArrayList<Node> neighbours = new ArrayList<Node>();

        int x=curNode.currentPosX;
        int y=curNode.currentPosY;
        int cost=curNode.cost;

        if(x>0 && grid[x-1][y]==1){
            Node v = new Node(cost+1,curNode,x-1,y);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(x<9 && grid[x+1][y]==1){
            Node v = new Node(cost+1,curNode,x+1,y);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(y>0 && grid[x][y-1]==1){
            Node v = new Node(cost+1,curNode,x,y-1);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(y<9 && grid[x][y+1]==1){
            Node v = new Node(cost+1,curNode,x,y+1);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(x>0 && y>0 && grid[x-1][y-1]==1){
            Node v = new Node(cost+1,curNode,x-1,y-1);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(x<9 && y>0 && grid[x+1][y-1]==1){
            Node v = new Node(cost+1,curNode,x+1,y-1);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(x>0 && y<9 && grid[x-1][y+1]==1){
            Node v = new Node(cost+1,curNode,x-1,y+1);
            v.predictedCost=simpleHeuristics(v);
            neighbours.add(v);
        }
        if(x<9 && y<9 && grid[x+1][y+1]==1) {
            Node v = new Node(cost + 1, curNode, x + 1, y + 1);
            v.predictedCost = simpleHeuristics(v);
            neighbours.add(v);
        }
        return neighbours;
    }
}
