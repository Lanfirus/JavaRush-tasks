package com.javarush.task.task23.task2312;

/**
 * Class that describes mouse behaviour
 */
public class Mouse {
    private int x;  //Mouse coordinate x on the screen
    private int y;  //Mouse coordinate y on the screen

    public Mouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
