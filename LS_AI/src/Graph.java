import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    public Node startNode() {

        Scanner in = new Scanner(System.in);
        System.out.println("enter queen No:");
        int queenNo = in.nextInt();

        Node stNode = new Node(queenNo);
        return stNode;
    }

    public ArrayList<Node> neighbourList(Node curNode) {
        int numNeighbour = 20;
        ArrayList<Node> neighbours = new ArrayList<>();

        for (int i = 0; i < numNeighbour; i++) {
            int[] copyPos = Arrays.copyOf(curNode.queenPos,
                    curNode.queenPos.length);

            int randIndex = (int) Math.floor(Math.random() * copyPos.length);
            int randVal = (int) Math.floor(Math.random() * copyPos.length);

            copyPos[randIndex] = randVal;

            Node nei = new Node(copyPos);
            neighbours.add(nei);

        }

        return neighbours;
    }
}
