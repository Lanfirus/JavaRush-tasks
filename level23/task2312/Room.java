package com.javarush.task.task23.task2312;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Main class of the program
 */
public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    /**
     * Main method of the game
     */
    public void run() {
        //Create "Keyboard observer" object and start it
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //while snake is alive
        while (snake.isAlive()) {
            //"observer" keeps data about pressing the keys
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //if "q" is pressed - quit from the game
                if (event.getKeyChar() == 'q') return;

                //If "left arrow" - move snake to the left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                    //If "right arrow" - move snake to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                    //If "up arrow" - move snake upwards
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                    //If "down arrow" - move snake downwards
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //move snake
            print();        //Display current state of the game
            sleep();        //pause between turns
        }

        System.out.println("Game Over!");
    }

    /**
     * Display current state of the game
     */
    public void print() {
        //Create array from where the game will be printed
        int[][] matrix = new int[height][width];

        //Draw all sections of the snake
        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections) {
            matrix[snakeSection.getY()][snakeSection.getX()] = 1;
        }

        //Draw head of the game. "4" if snake is dead
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

        //Draw mouse
        matrix[mouse.getY()][mouse.getX()] = 3;

        //Print all on the screen
        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(symbols[matrix[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Method is called when mouse has been eaten
     */
    public void eatMouse() {
        createMouse();
    }

    /**
     * Create new mouse
     */
    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }


    public static Room game;

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }

    private int initialDelay = 520;
    private int delayStep = 20;

    /**
     * Program makes a pause. Its delay depends on snake length.
     */
    public void sleep() {
        try {
            int level = snake.getSections().size();
            int delay = level < 15 ? (initialDelay - delayStep * level) : 200;
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }
}
