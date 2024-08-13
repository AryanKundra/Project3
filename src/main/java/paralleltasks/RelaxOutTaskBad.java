package paralleltasks;

import cse332.exceptions.NotYetImplementedException;
import paralleltasks.RelaxInTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RelaxOutTaskBad extends RecursiveAction {

    private final int[] Dis;
    private final int[] Path;
    private final int[] Distwo;
    private final ArrayList<HashMap<Integer,Integer>> adjList;
    private final int v;


    public RelaxOutTaskBad(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList, int v) {
        this.Dis = Dis;
        this.Path = Path;
        this.Distwo = Distwo;
        this.adjList = adjList;
        this.v = v;
    }

    protected void compute() {
        HashMap<Integer, Integer> neighbors = adjList.get(v);

        for (int w: neighbors.keySet()){
            int edgeCost = neighbors.get(w);

            if (Dis[v] < Integer.MAX_VALUE && Dis[w] > Distwo[v] + edgeCost){
                Dis[w] = Distwo[v] +edgeCost;
                Path[w] = v;
            }
        }

    }
/*
    public static void sequential(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList, int v) {
        RelaxOutTaskBad task = new  RelaxOutTaskBad(Dis, Path, Distwo, adjList, v);
            task.compute();
    }

    public static void parallel(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList, int v) {
        RelaxOutTaskBad task = new RelaxOutTaskBad(Dis, Path, Distwo, adjList, v);
        task.fork();
        task.join();
    }
*/

}

