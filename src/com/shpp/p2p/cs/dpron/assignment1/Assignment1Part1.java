package com.shpp.p2p.cs.dpron.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        // go to beeper and take him
        moveWhileFrontIsClear();
        turnRight();
        move();
        turnLeft();
        moveWhileFrontIsClear();
        //go start position
        turnAround();
        moveWhileFrontIsClear();
        turnRight();
        move();
    }
    //turn right
    private void turnRight() throws Exception{
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
    //turn around
    private void  turnAround() throws Exception{
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }
    // move if front is clear and pick beeper if he is
    private void moveWhileFrontIsClear() throws Exception{
        while (frontIsClear()){
            move();
            if (beepersPresent()) {
                pickBeeper();
            }
        }
    }
}
