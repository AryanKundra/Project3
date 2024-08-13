package paralleltasks;

import cse332.exceptions.NotYetImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RelaxInTask extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    private final int[] Dis;
    private final int[] Path;
    private final int[] Distwo;
    private final ArrayList<HashMap<Integer,Integer>> adjList;
    private final int v;


    public RelaxInTask(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList, int v) {
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

            if (Dis[w] < Integer.MAX_VALUE && Dis[v] > Distwo[w] + edgeCost){
                Dis[v] = Distwo[w] +edgeCost;
                Path[v] = w;
            }
        }

    }

    /*
    public static void sequential(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList, int v) {
        for (int i = 0; i < adjList.size(); i++){
            RelaxInTask task = new RelaxInTask(Dis, Path, Distwo, adjList, i);
            task.compute();
        }
    }

    public static void parallel(int[] Dis, int[] Path, int[] Distwo, ArrayList<HashMap<Integer, Integer>> adjList) {
        RelaxInTask task = new RelaxInTask(Dis, Path, Distwo, adjList, 0);
        task.fork();
        task.join();
    }
*/


}
