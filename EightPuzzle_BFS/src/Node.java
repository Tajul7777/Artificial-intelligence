import java.util.Arrays;

public class Node {
    int[][] state = new int[3][3];
    int cost;
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

        return Arrays.deepEquals(state, node.state);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }
}
