import java.util.Arrays;

public class Node implements Comparable<Node>{
    int[][] state = new int[3][3];
    int cost,predictedCost;
    Node parent;
    Node(int[][] state,int cost,Node parent){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                this.state[i][j]=state[i][j];
        this.cost=cost;
        this.parent=parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (predictedCost != node.predictedCost) return false;
        return Arrays.deepEquals(state, node.state);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(state);
        result = 31 * result + predictedCost;
        return result;
    }

    @Override
    public int compareTo(Node o) {
        int ownPriority = predictedCost+cost;
        int otherPriority = o.predictedCost+o.cost;
        return Integer.compare(ownPriority,otherPriority);
    }
}
