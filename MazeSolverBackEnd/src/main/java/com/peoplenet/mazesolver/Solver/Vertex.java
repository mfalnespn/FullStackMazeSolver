package com.peoplenet.mazesolver.Solver;

/**
 * Created by grewa on 8/14/2017.
 */
public class Vertex {
    private Coordinate coordinate;
    private Vertex previous;
    private Vertex next;
    private int distance;
    private boolean isClosed;

    public Vertex(){
        coordinate = new Coordinate();
        previous = null;
    }
    public Vertex(Coordinate c){
        this.coordinate = c;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public void setCoordinate(Coordinate c){
        coordinate = c;
    }

    public int getHeuristic(Vertex end){
        return Math.abs(end.coordinate.getX() - this.coordinate.getX()) + Math.abs(end.coordinate.getY()-this.coordinate.getY());
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public boolean equals(Object obj) {
        Vertex v = (Vertex) obj;
        return (this.getCoordinate() != null && v != null && this.getCoordinate().equals(v.getCoordinate()));
    }
}
