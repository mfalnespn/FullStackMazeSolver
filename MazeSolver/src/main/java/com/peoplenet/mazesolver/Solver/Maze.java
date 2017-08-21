package com.peoplenet.mazesolver.Solver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grewa on 8/11/2017.
 */
public class Maze {
    private Vertex startVertex = new Vertex();
    private Vertex endVertex = new Vertex();
    private char[][] maze;
    private List<Vertex> solution;
    private long solutionSteps;

    public Maze(){
        init();
    }

    /**
     * Create a 2 dimensional array representing the maze
     * @param input the text representation of the maze.
     * @return this
     */
    public Maze createMaze(String input){
        init();
        String[] arr = input.split("\n");
        if(arr.length <= 0){
            return null;
        }
        int height = arr.length;
        int width = arr[0].length();

        char[][] maze = new char[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                maze[y][x] = arr[y].charAt(x);
                if(maze[y][x] == 'A') {
                    startVertex.setCoordinate(new Coordinate(x,y));
                }
                if(maze[y][x] == 'B') {
                    endVertex.setCoordinate(new Coordinate(x,y));
                }
            }
        }
        this.maze = maze;
        return this;
    }

    /**
     *
     * @return true if the maze was solved. False otherwise.
     */
    public boolean solveMaze(){
        MazeSolvingAlgorithm algorithm = new AStarAlgorithm();
        Vertex v = algorithm.execute(maze, startVertex, endVertex);
        if(v != null){
            solutionSteps = v.getDistance();
        }
        while(v != null){
            solution.add(v);
            v = v.getPrevious();
        }
        solution.sort((o1, o2) -> o1.getDistance() < o2.getDistance() ? -1 : o1.getDistance() > o2.getDistance() ? 1 : 0);
        return !solution.isEmpty();
    }

    /**
     * @return the string representation of the solved maze.
     */
    public String getSolutionString(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[0].length; x++){
                sb.append(maze[y][x]);
            }
            sb.append('\n');
        }
        if(solution == null) return null;
        for(Vertex vertex : solution){
            if(!vertex.getCoordinate().equals(startVertex.getCoordinate()) && !vertex.getCoordinate().equals(endVertex.getCoordinate())) {
                sb.setCharAt((vertex.getCoordinate().getY()*(maze[0].length+1)) + vertex.getCoordinate().getX(),'@');
            }
        }
        return sb.toString();
    }

    /**
     * @return number of steps in solution
     */
    public long getSolutionSteps() {
        return solutionSteps;
    }

    private void init(){
        startVertex = new Vertex();
        endVertex = new Vertex();
        maze = null;
        solution = new ArrayList<>();
    }

}
