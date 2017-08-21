package com.peoplenet.mazesolver.Solver;

/**
 * Created by grewa on 8/11/2017.
 */
public class  Coordinate {
    private int x;
    private int y;
    private Coordinate previous;

    public Coordinate(){
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate c = (Coordinate) obj;
        return c != null && this.getX() == c.getX() && this.getY() == c.getY();
    }
}
