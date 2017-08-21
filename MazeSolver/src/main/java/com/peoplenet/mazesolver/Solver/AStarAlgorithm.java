package com.peoplenet.mazesolver.Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by grewa on 8/12/2017.
 */
public class AStarAlgorithm implements MazeSolvingAlgorithm {
    private char[][]maze;
    private Coordinate start, end;

    @Override
    /**
     * Executes A* path finding algorithm to find the shortest path.
     */
    public Vertex execute(char[][] maze, Vertex start, Vertex end) {
        this.maze = maze;
        start.setDistance(0);
        start.setPrevious(null);
        Comparator comp = (Object o1, Object o2) -> {
            Vertex v1 = (Vertex) o1;
            Vertex v2 = (Vertex) o2;
            int h1 = v1.getHeuristic(end);
            int d1 = v1.getDistance();
            int h2 = v2.getHeuristic(end);
            int d2 = v2.getDistance();
            return (h1 + d1) - (h2 + d2);
        };

        PriorityQueue<Vertex> pq = new PriorityQueue<>(comp);
        List<Vertex> closedVertex = new ArrayList<>();
        pq.add(start);

        while(!pq.isEmpty()){
            Vertex cur = pq.remove();
            closedVertex.add(cur);

            if(cur.getCoordinate().getX() == end.getCoordinate().getX() && cur.getCoordinate().getY() == end.getCoordinate().getY()){
                //Solution found
                return cur;
            }
            else{
                List<Vertex> children = expand(cur);
                for(Vertex child : children){
                    child.setPrevious(cur);
                    child.setDistance(cur.getDistance()+1);
                    if(!pq.contains(child) && !closedVertex.contains(child)) {
                        pq.add(child);
                    }
                }
            }
        }
        //Unable to find a solution
        return null;
    }

    /**
     * Returns a list of child vertices of the root vertex.
     * Child vertices are all possible moves that can be made in the puzzle.
     *
     * @param root The root vertex
     * @return List of child vertices.
     */
    private List<Vertex> expand(Vertex root){
        List<Vertex> children = new ArrayList<>();
        int x = root.getCoordinate().getX();
        int y = root.getCoordinate().getY();
        addChildCoordinate(children, x-1, y);
        addChildCoordinate(children, x+1, y);
        addChildCoordinate(children, x, y-1);
        addChildCoordinate(children, x, y+1);
        return children;
    }

    /**
     * Adds child vertex to the list if the vertex can be reached and that it does not already occur on the path.
     *
     * @param children list of child vertices
     * @param x x coordinate of possible child vertex
     * @param y y coordinate of possible child vertex
     */
    private void addChildCoordinate(List<Vertex> children, int x, int y){
        Vertex vertex = makeMove(x, y);
        if(vertex != null && !occursOnPath(vertex, vertex.getPrevious()))
            children.add(vertex);
    }

    /**
     * Attempt to move to position (x,y)
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return Vertex representing the move made, or null if move cannot be made.
     */
    private Vertex makeMove(int x, int y){
        if(x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze[y][x] != '#') {
            return new Vertex(new Coordinate(x,y));
        }
        return null;
    }

    /**
     * Check if the vertex cur is already on the current path.
     * @param cur current vertex
     * @param predecessor a vertex preceding the current vertex.
     * @return true if cur is already in the path. False otherwise.
     */
    private boolean occursOnPath(Vertex cur, Vertex predecessor){
        if(predecessor == null){
            return false;
        }
        else if(cur.getCoordinate().equals(predecessor.getCoordinate())){
            return true;
        }
        else{
            return occursOnPath(cur, predecessor.getPrevious());
        }
    }
}
