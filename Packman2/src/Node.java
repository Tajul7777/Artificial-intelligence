public class Node implements Comparable<Node>{
    int cost,predictedCost, x, y;
    Node parent;


    public Node(int cost, int x, int y) {
        super();
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + predictedCost;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (predictedCost != other.predictedCost)
            return false;
        return true;
    }

    public String toString() {
        return ""+x+" "+y;
    }
    @Override
    public int compareTo(Node o) {
        int ownPriority = predictedCost+cost;
        int otherPriority = o.predictedCost+o.cost;
        return Integer.compare(ownPriority,otherPriority);
    }
}
