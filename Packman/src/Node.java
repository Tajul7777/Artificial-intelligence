import java.util.Arrays;

public class Node implements Comparable<Node>{
    int cost,predictedCost,currentPosX,currentPosY;
    Node parent;

    Node(int cost, Node parent, int currentPosX, int currentPosY){

        this.cost=cost;
        this.parent=parent;
        this.currentPosX=currentPosX;
        this.currentPosY=currentPosY;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (predictedCost != node.predictedCost) return false;
        if (currentPosX != node.currentPosX) return false;
        return currentPosY == node.currentPosY;
    }

    @Override
    public int hashCode() {
        int result = predictedCost;
        result = 31 * result + currentPosX;
        result = 31 * result + currentPosY;
        return result;
    }


    @Override
    public int compareTo(Node o) {
        int ownPriority = predictedCost+cost;
        int otherPriority = o.predictedCost+o.cost;
        return Integer.compare(ownPriority,otherPriority);
    }
}
