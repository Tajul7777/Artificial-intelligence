import java.util.*;

public class A_Star {

    static Node finalNode = null;
    public static void main(String[] args) {

        Graph g = new Graph();
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        HashSet<Node> marked = new HashSet<Node>();
        Node stNode = g.startNode();
        pq.add(stNode);
        marked.add(stNode);
        while (!pq.isEmpty()){
            Node u = pq.poll();
            if(g.reachedDestination(u)){
                finalNode = u;
                break;
            }
            ArrayList<Node> neighbours = g.getNeighbours(u);
            for(int i=0;i<neighbours.size();i++){
                Node v = neighbours.get(i);

                if(marked.contains(v) == false){
                    pq.add(v);
                    marked.add(v);
                }

            }
        }

        if(finalNode==null)
            System.out.println("Failed");
        else{
            System.out.println("Total Cost: "+finalNode.cost);
            System.out.println("Solution:");
            printSolution(finalNode);
        }
    }

    static void printSolution(Node node)
    {
        if(node==null) return;;
        printSolution(node.parent);
        System.out.print("("+node.currentPosX+","+node.currentPosY+")");
        if(node!=finalNode) System.out.print(" -> ");
    }

}
