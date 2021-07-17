package com.shpp.p2p.cs.dpron.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot{
    public void run() throws Exception{
        putBeeper();
        checkWall();
        while (frontIsClear()) {
            beepersEast();
            beepersWest();
        }
    }
    //turn right
    private void turnRight() throws Exception{
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
    // put beepers while facing east
    private void beepersEast() throws Exception{
        while (facingEast()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
            upEast();
        }
    }
    //up to east if front is blocked
    private void upEast() throws Exception{
        if (frontIsBlocked()) {
            if (noBeepersPresent()) {
                turnLeft();
                if (frontIsClear()) {
                    move();
                    turnLeft();
                    putBeeper();
                }
            }
            else {
                turnLeft();
                if (frontIsClear()) {
                    move();
                    turnLeft();
                    move();
                    putBeeper();
                }
            }
        }
    }
    //while facing east move and if front is clear put beeper
    private void beepersWest() throws Exception{
        while (facingWest()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
            upWest();
        }
    }
    //up west if front blocked
    private void upWest() throws Exception{
        if (frontIsBlocked()) {
            if (noBeepersPresent()) {
                turnRight();
                if (frontIsClear()) {
                    move();
                    turnRight();
                    putBeeper();
                }
            }
            else {
                turnRight();
                if (frontIsClear()) {
                    move();
                    turnRight();
                    move();
                    putBeeper();
                }
            }
        }
    }
    //check wall
    private void checkWall() throws Exception{
        if (frontIsBlocked()) {
            turnLeft();
            while (frontIsClear()) {
                move();
                if (frontIsClear()) {
                    move();
                    putBeeper();
                }
            }
        }
    }
}
