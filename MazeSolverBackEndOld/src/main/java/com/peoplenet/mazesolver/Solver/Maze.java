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

    public Maze(){
        init();
    }

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

    public boolean solveMaze(){
        MazeSolvingAlgorithm algorithm = new AStarAlgorithm();
        Vertex v = algorithm.execute(maze, startVertex, endVertex);
        while(v != null){
            solution.add(v);
            v = v.getPrevious();
        }
        solution.sort((o1, o2) -> o1.getDistance() < o2.getDistance() ? -1 : o1.getDistance() > o2.getDistance() ? 1 : 0);
        return !solution.isEmpty();
    }

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

    public void printSolution(){
        System.out.println(getSolutionString());
    }

    public void print(){
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[0].length; x++){
                System.out.print(maze[y][x]);
            }
            System.out.println();
        }
    }
    private void init(){
        startVertex = new Vertex();
        endVertex = new Vertex();
        maze = null;
        solution = new ArrayList<>();
    }

}
