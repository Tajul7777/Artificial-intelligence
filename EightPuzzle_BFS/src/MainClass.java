import java.util.*;

public class MainClass {

    static int res=0;
    static Node node;
    static boolean f;

    public static void main(String[] args) {
        int[][] state = {
                {8,7,6},
                {5,0,3},
                {2,4,1}
        };

        solve(state);
    }

    static boolean check(int[][] arr)
    {
        int cnt=0;

        for(int i=0;i<3;i++)
            for (int j=0;j<3;j++) {
                if (arr[i][j] != cnt) return false;
                cnt++;
            }


        return  true;
    }

    static void swap(int[][] state,int a,int b,int c,int d)
    {

        int temp =state[a][b];
        state[a][b]=state[c][d];
        state[c][d]=temp;
    }
    static void printSolution(Node node)
    {
        if(node==null) return;;
        printSolution(node.parent);
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(node.state[i][j] + " ");
            System.out.println("");
        }
        System.out.println("");
    }
    static void solve(int arr[][])
    {
        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> hashSet = new HashSet<Node>();

        Node u = new Node(arr,0,null);

        queue.add(u);
        hashSet.add(u);

        while (!queue.isEmpty()){
            node = queue.poll();

            int[][] state = new int[3][3];

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    state[i][j]=node.state[i][j];

            int cost=node.cost;

            if(check(state)){
                res=cost;
                f=true;
                break;
            }

            int row=0,col=0;
            boolean flg=false;
            for(row=0;row<3;row++) {
                for (col = 0; col < 3; col++) {
                    if (state[row][col] == 0) {
                        flg = true;
                        break;
                    }
                }
                if (flg) break;
            }

            if(row>0){
                swap(state,row,col,row-1,col);
                Node v = new Node(state,cost+1,node);
                if(!hashSet.contains(v)){
                    queue.add(v);
                    hashSet.add(v);
                }
                swap(state,row,col,row-1,col);
            }
            if(row<2){
                swap(state,row,col,row+1,col);
                Node v = new Node(state,cost+1,node);
                if(!hashSet.contains(v)){
                    queue.add(v);
                    hashSet.add(v);
                }
                swap(state,row,col,row+1,col);
            }
            if(col>0){
                swap(state,row,col,row,col-1);
                Node v = new Node(state,cost+1,node);
                if(!hashSet.contains(v)){
                    queue.add(v);
                    hashSet.add(v);
                }
                swap(state,row,col,row,col-1);
            }
            if(col<2){
                swap(state,row,col,row,col+1);
                Node v = new Node(state,cost+1,node);
                if(!hashSet.contains(v)){
                    queue.add(v);
                    hashSet.add(v);
                }
                swap(state,row,col,row,col+1);
            }

        }

        if(f) {
            System.out.println("Total cost: " + res);
            System.out.println("Solution:");
            printSolution(node);
        }
        else System.out.println("Failed");
    }
}
