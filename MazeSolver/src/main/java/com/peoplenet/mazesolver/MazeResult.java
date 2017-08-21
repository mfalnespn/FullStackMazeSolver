package com.peoplenet.mazesolver;

public class MazeResult {
    private final long steps;
    private final String solvedMaze;

    public MazeResult(long steps, String content) {
        this.steps = steps;
        this.solvedMaze = content;
    }

    public long getSteps() {
        return steps;
    }

    public String getSolvedMaze() {
        return solvedMaze;
    }
}
