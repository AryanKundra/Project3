package solvers;

import cse332.graph.GraphUtil;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;

import java.util.List;


public class OutSequential implements BellmanFordSolver {

    public List<Integer> solve(int[][] adjMatrix, int source) {
        int V = adjMatrix.length;

        int[] D = new int[V];

        int[] copiedD = new int[V];

        int[] P = new int[V];

        for (int i = 0; i < V; i++) {
            D[i] = GraphUtil.INF;
            P[i] = -1;
        }
        D[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {

                System.arraycopy(D, 0, copiedD, 0, V);

                for (int v = 0; v < V; v++) {
                    if (adjMatrix[j][v] != GraphUtil.INF) {
                        int w = adjMatrix[j][v];
                        if (D[j] != GraphUtil.INF && copiedD[j] + w < D[v]) {
                            D[v] = copiedD[j] + w;
                            P[v] = j;
                        }

                    }

                }

            }

        }
        return GraphUtil.getCycle(P);


    }
}