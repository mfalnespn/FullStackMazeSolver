package com.peoplenet.mazesolver.Solver;

/**
 * Created by grewa on 8/12/2017.
 */
public interface MazeSolvingAlgorithm {
    public Vertex execute(char[][] maze, Vertex start, Vertex end);
}
