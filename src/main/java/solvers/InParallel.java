package solvers;

import cse332.exceptions.NotYetImplementedException;
import cse332.graph.GraphUtil;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;
import paralleltasks.ArrayCopyTask;
import paralleltasks.RelaxInTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class InParallel implements BellmanFordSolver {

    public List<Integer> solve(int[][] adjMatrix, int source) {
        ArrayList<HashMap<Integer, Integer>> adjList = Parser.parseInverse(adjMatrix);


        int[] Dis = new int[adjMatrix.length];
        int[] Path = new int[adjMatrix.length];

        for(int i = 0; i< adjMatrix.length; i++){
            Dis[i] = Integer.MAX_VALUE;
            Path[i] = -1;
        }
        Dis[source] = 0;

        ForkJoinPool POOL = new ForkJoinPool();

        for(int n = 0; n<adjMatrix.length; n++){
            for (int v = 0; v<adjMatrix.length; v++){
                int[] Distwo = new int[Dis.length];
                POOL.invoke(new ArrayCopyTask(Dis,Distwo, 0, Dis.length));
                POOL.invoke(new RelaxInTask(Dis, Path, Distwo, adjList, v));
            }
        }
        return GraphUtil.getCycle(Path);
    }

}
