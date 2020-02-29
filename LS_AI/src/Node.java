import java.util.Arrays;

public class Node implements Comparable<Node>{
    public int[] queenPos;
    public int score = -1;

    public Node(int queenNo) {

        queenPos = new int[queenNo];

        for (int i = 0; i < queenPos.length; i++) {
            int randPOs = (int) Math.floor(Math.random() * queenNo);
            queenPos[i] = randPOs;
        }

        score = getScore();
    }

    public Node(int[] copyPos) {

        queenPos = copyPos;

        score = getScore();
    }

    public int getScore() {

        int rowConflict = 0;
        int diaConflict = 0;

        for (int i = 0; i < queenPos.length; i++) {
            for (int j = i + 1; j < queenPos.length; j++) {

                if (queenPos[i] == queenPos[j]) {
                    rowConflict++;
                }

                int rowDiff = Math.abs(queenPos[i] - queenPos[j]);
                int colDiff = Math.abs(i - j);

                if (rowDiff == colDiff) {
                    diaConflict++;
                }
            }
        }

        int scoreVal = -1 * (rowConflict + diaConflict);
        return scoreVal;
    }



    public String toString() {
        return "Node [queenPos=" + Arrays.toString(queenPos) + ", score="
                + score + "]";
    }


    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return Double.compare(this.score, o.score);
    }


    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(queenPos);
        result = prime * result + score;
        return result;
    }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (!Arrays.equals(queenPos, other.queenPos))
            return false;
        if (score != other.score)
            return false;
        return true;
    }

}
