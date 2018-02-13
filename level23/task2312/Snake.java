package com.javarush.task.task23.task2312;

import java.util.ArrayList;

/**
 * Class that describes Snake and its behaviour
 */
public class Snake {
    //Move direction of the snake
    private SnakeDirection direction;
    //Snake condition - is it alive or not
    private boolean isAlive;
    //List of snake segments
    private ArrayList<SnakeSection> sections;

    public Snake(int x, int y) {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    /**
     * Move snake by one tile
     * Move direction is defined by "direction" parameter
     */
    public void move() {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    /**
     * Move snake to neighbour tile
     * Coordinates of target tile are defined relatively to the current position of snake's head by parameters "dx, dy"
     */
    private void move(int dx, int dy) {
        //Make new head - a new segment of snake
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        //Check - whether snake is inside the room or not
        checkBorders(head);
        if (!isAlive) return;

        //Check - whether snake bites itself or not
        checkBody(head);
        if (!isAlive) return;

        //Check - whether snake catches mouse or not
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) //ate
        {
            sections.add(0, head);                  //Added new head
            Room.game.eatMouse();                   //The tail is not removed, but we should add new mouse in the room
        } else //просто движется
        {
            sections.add(0, head);                  //Added new head
            sections.remove(sections.size() - 1);   //Removed last segment
        }
    }

    /**
     * Check - whether snake is inside the room or not
     */
    private void checkBorders(SnakeSection head) {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight()) {
            isAlive = false;
        }
    }

    /**
     * Check - whether snake bites itself or not
     */
    private void checkBody(SnakeSection head) {
        if (sections.contains(head)) {
            isAlive = false;
        }
    }
}
