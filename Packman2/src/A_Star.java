import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class A_Star {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Graph g = new Graph();
        //Queue<Node> q = new LinkedList<Node>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        HashSet<Node> marked = new HashSet<Node>();
        //HashMap<Node,Integer> dist = new HashMap<Node, Integer>();
        HashMap<Node,Node> bp = new HashMap<Node,Node>();

        Node stNode = g.startNode();
        pq.add(stNode);
        marked.add(stNode);
        //dist.put(stNode, 0);
        bp.put(stNode, null);

        Node finalNode = null;

        while (!pq.isEmpty()){
            Node u = pq.poll();
            if(g.reachedDestination(u)){
                finalNode = u;
                System.out.println("Reached!!!!");
                System.out.println("Distance" + finalNode.cost);
                break;
            }
            ArrayList<Node> neighbours = g.getNeighbours(u);
            for(int i=0;i<neighbours.size();i++){
                Node v = neighbours.get(i);

                if(marked.contains(v) == false){
                    pq.add(v);
                    marked.add(v);
                    bp.put(v, u);
                }

            }
        }

        if(finalNode == null){
            System.out.println("Failed!!!!");
        }else{
            Node curNode = finalNode;
            Stack<Node> path  = new Stack<Node>();
            while(curNode != null){
                path.add(curNode);

                Node parent = bp.get(curNode);
                curNode = parent;
            }

            while(path.isEmpty() == false){
                System.out.println(path.pop().toString());
            }
        }


    }

}
