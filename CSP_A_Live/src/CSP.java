import java.util.ArrayList;
import java.util.Arrays;


public class CSP {

    int numQueens = 0;
    int[] assignment = null;
    ArrayList<ArrayList<Integer>> remainingValues = null;


    public CSP(int numQ) {
        super();
        numQueens = numQ;
        assignment = new int[numQueens];
        for(int i=0;i<numQueens;i++){
            assignment[i]= -1;
        }
        remainingValues = new ArrayList<ArrayList<Integer>>();


        ArrayList<Integer> domain = new ArrayList<Integer>();
        for(int i=0;i<numQueens;i++){
            domain.add(i);

        }

        for(int i=0;i<numQueens;i++){
            remainingValues.add((ArrayList<Integer>) domain.clone());
        }
        //System.out.println(remainingValues.toString());

        backTrack();




        System.out.println(Arrays.toString(assignment));

    }
    private boolean backTrack() {

        if(everythingAssigned()){
            return true;
        }

        int curIndex = MRV();

        ArrayList<Integer> options = remainingValues.get(curIndex);

        for(int i=0;i<options.size();i++){

            assignment[curIndex] = options.get(i);

            if(assignmentConsistent()){
                ArrayList<ArrayList<Integer>> savedDomain = makeaCopy();
                boolean inference = forwardChecking(remainingValues,curIndex);

                if(inference){
                    boolean result = backTrack();

                    if(result){
                        return  true;

                    }
                }

                remainingValues = savedDomain;
                assignment[curIndex] = -1;
            }else{
                assignment[curIndex] = -1;
            }





        }

        return false;
    }

    private ArrayList<ArrayList<Integer>> makeaCopy() {

        ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>();

        for(int i=0;i<numQueens;i++){
            copy.add((ArrayList<Integer>) remainingValues.get(i).clone());
        }

        return copy;
    }
    //Do yourself
    //method signature partially correct
    private boolean forwardChecking(ArrayList<ArrayList<Integer>> domain, int index) {

        int diagonalRight=assignment[index]+1;
        int diagonalLeft=assignment[index]-1;

        for(int i=index+1;i<numQueens;i++){


            domain.get(i).remove(new Integer(assignment[index]));
            if(diagonalRight<numQueens){
                domain.remove(new Integer(diagonalRight));
            }
            if(diagonalLeft>=0){
                domain.remove(new Integer(diagonalLeft));
            }
            diagonalRight++;
            diagonalLeft--;
        }

        diagonalRight=assignment[index]+1;
        diagonalLeft=assignment[index]-1;
        for(int i=index-1;i>=0;i--){

            domain.get(i).remove(new Integer(assignment[index]));

            if(diagonalRight<numQueens){
                domain.remove(new Integer(diagonalRight));
            }
            if(diagonalLeft>=0){
                domain.remove(new Integer(diagonalLeft));
            }
            diagonalRight++;
            diagonalLeft--;


        }

        for(int i=0;i<numQueens;i++){
            if(domain.get(i).isEmpty())
                return false;
        }

        return true;
    }
    private boolean everythingAssigned() {


        for(int i=0;i<assignment.length;i++){
            if(assignment[i] == -1){
                return false;
            }
        }
        return true;
    }

    //DO YOURSELF!!!!
    private int MRV() {
        int min=Integer.MAX_VALUE,index=0;
        for(int i=0;i<numQueens;i++){
            if(assignment[i]==-1){
                if(remainingValues.get(i).size()<min){
                    min=remainingValues.get(i).size();
                    index=i;
                }
            }
        }
        return index;
    }

    //DO YOURSELF!!!!
    //check only the assigned variables
    private boolean assignmentConsistent() {
        for(int i=0;i<numQueens;i++){
            for(int j=i+1;j<numQueens;j++){
                if(assignment[i]!=-1 && assignment[j]!=-1){
                    if(assignment[i]==assignment[j]) return false;
                    int rowDiff = Math.abs(assignment[i] - assignment[j]);
                    int colDiff = Math.abs(i - j);
                    if(rowDiff == colDiff) return false;
                }
            }
        }
        return true;
    }



}
