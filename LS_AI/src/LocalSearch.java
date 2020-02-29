import java.util.ArrayList;
import java.util.Collections;

public class LocalSearch {

    public static void main(String[] args) {


        //simpleLOcalSearch();
        //beamSearch();
        simulatedAnnealing();

    }

    private static void simulatedAnnealing() {
        Graph G = new Graph();
        Node stNode = G.startNode();
        Node curNode = stNode;

        int temperature = 10000;

        int tempCounter = 0;

        while(temperature > 0){
            ArrayList<Node> neighbours = G.neighbourList(curNode);

            int bestIndex = findBestNei(neighbours);

            Node bestNeighbour = neighbours.get(bestIndex);

            if(bestNeighbour.score > curNode.score){
                curNode = bestNeighbour;
            }else{
                double delE = curNode.score - bestNeighbour.score;
                double expo = -1*(delE/temperature);
                double crossPoint = Math.exp(expo);

                double randomDraw = Math.random();
                if(randomDraw < crossPoint){
                    curNode = bestNeighbour;
                }

            }

            if(tempCounter == 5){
                temperature --;
                tempCounter = 0;
            }else{
                tempCounter++;
            }


        }
        System.out.println(curNode.toString());


    }

    private static int findBestNei(ArrayList<Node> neighbours) {

        int bestIndex = 0;

        for(int i=1;i<neighbours.size();i++){
            if(neighbours.get(i).score > neighbours.get(bestIndex).score){
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    private static void beamSearch() {

        Graph G = new Graph();
        Node stNode = G.startNode();

        ArrayList<Node> bestSols = new ArrayList<>();
        bestSols.add(stNode);
        int numBestSolsStored = 20;

        int numIter = 10000;
        Node globalBest = null;

        for(int i=0;i<numIter;i++){
            if(bestSols.isEmpty()){
                break;
            }

            Node curBest = bestSols.remove(bestSols.size()-1);
            if(globalBest == null || curBest.score > globalBest.score){
                globalBest = curBest;
            }
            if(curBest.score == 0){
                break;
            }

            ArrayList<Node> neighbours = G.neighbourList(curBest);

            for(int j=0;j<neighbours.size();j++){
                Node curNei = neighbours.get(j);
                if(bestSols.contains(curNei)){
                    continue;
                }

                if(bestSols.size() < numBestSolsStored){
                    bestSols.add(curNei);
                    Collections.sort(bestSols);
                }else{
                    if(curNei.score > bestSols.get(0).score){
                        bestSols.remove(0);
                        bestSols.add(curNei);
                        Collections.sort(bestSols);
                    }
                }
            }
        }

        System.out.println(globalBest.toString());
    }

    private static void simpleLOcalSearch() {

        Graph g = new Graph();

        Node stNode = g.startNode();

        int numIter = 10000;
        Node curNode = stNode;

        for (int i = 0; i < numIter; i++) {
            ArrayList<Node> neighbours = g.neighbourList(curNode);

            int bestNeiScore = Integer.MIN_VALUE;
            Node bestNeighbour = findBestNei(neighbours);

            for (int j = 0; j < neighbours.size(); j++) {
                Node curNei = neighbours.get(j);

                int curNeiScore = curNei.getScore();

                if (curNeiScore > bestNeiScore) {
                    bestNeiScore = curNeiScore;
                    bestNeighbour = curNei;
                }
            }

            if (bestNeiScore > curNode.getScore()) {
                curNode = bestNeighbour;
            }
        }

        System.out.println(curNode.toString());
    }

}
