package com.javarush.task.task23.task2312;

import java.awt.event.KeyEvent;
import static javafx.scene.input.KeyCode.T;

public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;
    public static Room game;
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Snake getSnake() {
        return this.snake;
    }
    
    public Mouse getMouse() {
        return this.mouse;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    
    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }
    
    public void run() {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //пока змея жива
        while (snake.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                    //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                    //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //двигаем змею
            print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }

        System.out.println("Game Over!");
    }
    
    public void print() {
        int[][] fild = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fild[i][j]='.'; }
        }
        // System.out.println(fild);
        //Создаем массив, куда будем "рисовать" текущее состояние игры

        fild[snake.getY()][snake.getX()]='X';
        for (int i = 1; i < snake.getSections().size(); i++) {
            int x = snake.getSections().get(i).getX();
            int y = snake.getSections().get(i).getY();
            fild[y][x]='x';
        }//Рисуем все кусочки змеи
        fild[mouse.getY()][mouse.getX()]= '^';//Рисуем мышь

        //Выводим все это на экран
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print((char) fild[i][j]);}
            System.out.println();
        }
    }
    
    public void createMouse() {
       mouse = new Mouse((int) (Math.random() * width), (int) (Math.random() * height));
    }
    
    public void eatMouse() {
        createMouse();
    }
    
    public void sleep() {
        try {
        if (snake.getSections().size() < 11) Thread.sleep(500);
        else if (snake.getSections().size() > 11 && snake.getSections().size() < 15) Thread.sleep(300);
        else if (snake.getSections().size() > 15) Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Snake snake = new Snake(5, 5);
        game = new Room(50, 50, snake);
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
        
    }
}
