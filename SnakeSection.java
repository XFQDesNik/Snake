package com.javarush.task.task23.task2312;


public class SnakeSection {
    private int x;
    private int y;
    
     public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public SnakeSection(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
