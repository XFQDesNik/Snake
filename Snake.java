package com.javarush.task.task23.task2312;

import java.util.List;
import java.util.ArrayList;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;
    
    public List<SnakeSection> getSections() {
        return this.sections;
    }
    
    public void checkBorders(SnakeSection head) {
        if ((head.getX()>=Room.game.getWidth())
                ||(head.getY()>=Room.game.getHeight())
                ||(head.getX()<0)
                ||(head.getY()<0)){
                isAlive=false;
        }
    }
    
    public void checkBody(SnakeSection head) {
        if (sections.contains(head)){
            isAlive=false;
        }
    }
    
    public boolean isAlive () {
        return this.isAlive;
    }
    
    public SnakeDirection getDirection() {
        return this.direction;
    }
    
    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }
    
    public Snake(int x, int y) {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
        
    }
    
    public int getX() {
        return sections.get(0).getX();
    }
    
    public int getY() {
        return sections.get(0).getY();
    }
    
    public void move(int dx, int dy) {
        // Создали кусочек змейки
        SnakeSection head = new SnakeSection(sections.get(0).getX() + dx, sections.get(0).getY() + dy);
        // Получили ссылку на мышку
        Mouse mouse = Room.game.getMouse();
        // Проверили змейку на жизнеспособность
        checkBorders(head);
        checkBody(head);

        if (isAlive) {
            sections.add(0, head);
            if (head.getX() != Room.game.getMouse().getX() ||
                    head.getY() != Room.game.getMouse().getY()) {
                
                
                
                sections.remove(sections.size() - 1);
            }
            else {
                
                 // Если НЕ слопала мышь
                Room.game.eatMouse();
            }
        }
    }
    
    public void move() {
        if (isAlive) {
        if (direction == SnakeDirection.UP) move(0, -1);
        if (direction == SnakeDirection.RIGHT) move(1, 0);
        if (direction == SnakeDirection.DOWN) move(0, 1);
        if (direction == SnakeDirection.LEFT) move(-1, 0);
        }
    }
    
    
}
