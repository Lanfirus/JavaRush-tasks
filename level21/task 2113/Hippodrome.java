package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List list) {
        this.horses = list;
    }

    public List getHorses() {
        return horses;
    }

    /**
     * Executes run of the game using continuous calling move() and print().
     * Number of moves (and the length of the game) can be changed by modifying value of parameter "numberOfMoves".
     * Speed (and thus length) of the game can be changed by modifying value of parameter "delay".
     *
     * @throws InterruptedException If thread is interrupted during the sleep.
     */
    public void run() throws InterruptedException{
        int numberOfMoves = 100;
        int delay = 200;
        for (int i = 0; i < numberOfMoves; i++) {
            move();
            print();
            Thread.sleep(delay);
        }
    }

    /**
     * Calls move() from Horse class for each of Horse in "horses" array.
     *
     */
    public void move() {
        for (Horse x : horses) {
            x.move();
        }

    }

    /**
     * Calls print() from Horse class for each of Horse in "horses" array.
     * Number of empty lines to be drawn on the screen allows to "see" only last move of the game if chosen correctly.
     * Add more empty lines if you see previous moves on console.
     * Delete some lines if you see nothing.
     */
    public void print() {
        for (Horse x : horses) {
            x.print();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Returns the Winner of the race based on the maximum distance among all horses.
     *
     * @return Horse object that wins the race
     */
    public Horse getWinner() {
        Horse tmp = horses.get(0);
        for (Horse x : horses) {
            if (x.getDistance() > tmp.getDistance()) {
                tmp = x;
            }
        }
        return tmp;
    }

    /**
     * Prints the name of the Winner on console.
     */
    public void printWinner() {
        System.out.println(String.format("Winner is %s!", getWinner().getName()));
    }


    public static void main(String[] args) throws InterruptedException{

        game = new Hippodrome(new ArrayList<>());
        Horse ishak = new Horse("Ishak", 3, 0);
        Horse lighting = new Horse("Lighting", 3, 0);
        Horse ordinaryHorse = new Horse("Ordinary Horse", 3, 0);

        game.getHorses().add(ishak);
        game.getHorses().add(lighting);
        game.getHorses().add(ordinaryHorse);

        game.run();
        game.printWinner();
    }
}
