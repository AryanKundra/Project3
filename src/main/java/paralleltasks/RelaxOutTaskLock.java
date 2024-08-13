package paralleltasks;

import cse332.exceptions.NotYetImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RelaxOutTaskLock extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 1;
    private final int[] Dis;
    private final int[] Path;
    private final int[] Distwo;
    private final List<HashMap<Integer, Integer>> adjList;
    private final int v;
    private final ReentrantLock lock;


    public RelaxOutTaskLock(int[] Dis, int[] Path, int[] Distwo, List<HashMap<Integer, Integer>> adjList, int v, Lock lock) {

        this.Dis = Dis;
        this.Path = Path;
        this.Distwo = Distwo;
        this.adjList = adjList;
        this.v = v;
        this.lock = (ReentrantLock) lock;

    }

    protected void compute() {
        lock.lock();
        try{
            HashMap<Integer,Integer> neighbor = adjList.get(v);
            for(int w : neighbor.keySet()){
                int edgeCost = neighbor.get(w);
                if (Dis[v] < Integer.MAX_VALUE && Dis[w] > Dis[v] + edgeCost){
                    Dis[w] = Distwo[v] +edgeCost;
                    Path[w] = v;
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public static void sequential() {
        throw new NotYetImplementedException();
    }

    public static void parallel(int[] Dis, int[] Path, int[] Distwo, List<HashMap<Integer, Integer>> adjList, int v, Lock lock) {
        RelaxOutTaskLock task = new RelaxOutTaskLock(Dis, Path, Distwo, adjList, v ,lock);
        task.fork();
        task.join();
    }
}
