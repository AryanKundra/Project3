package main;

import cse332.exceptions.NotYetImplementedException;
import cse332.graph.GraphUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    static final int X = GraphUtil.INF;

    public static ArrayList<HashMap<Integer,Integer>> parse(int[][] adjMatrix) {
        ArrayList<HashMap<Integer,Integer>> adjList = new ArrayList<>();

        for (int v = 0; v < adjMatrix.length; v++) {
            HashMap<Integer, Integer> neighbors = new HashMap<>();

            for (int w = 0; w < adjMatrix[0].length; w++) {
                if (adjMatrix[v][w] != Integer.MAX_VALUE) {
                    neighbors.put(w, adjMatrix[v][w]);
                }
            }
            adjList.add(neighbors);
        }

        return adjList;
    }
    /**
     * Parse an adjacency matrix into an adjacency list with incoming edges instead of outgoing edges.
     *
     * @param adjMatrix Adjacency matrix
     * @return Adjacency list of maps from node to weight with incoming edges
     */
    public static ArrayList<HashMap<Integer,Integer>> parseInverse(int[][] adjMatrix) {
        ArrayList<HashMap<Integer,Integer>> adjList = new ArrayList<>();

        for (int v = 0; v < adjMatrix.length; v++) {
            HashMap<Integer, Integer> neighbors = new HashMap<>();

            for (int w = 0; w < adjMatrix[0].length; w++) {
                if (adjMatrix[w][v] != Integer.MAX_VALUE) {
                    neighbors.put(w, adjMatrix[w][v]);
                }
            }

            adjList.add(neighbors);
        }

        return adjList;
    }

}


