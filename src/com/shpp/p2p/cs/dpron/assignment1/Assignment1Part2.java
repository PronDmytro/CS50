package com.shpp.p2p.cs.dpron.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {
    public void run() throws Exception {
        repairColumn();
        while (frontIsClear()){
            nextColumn();
            repairColumn();
        }
    }
    //repair column
    private void repairColumn() throws Exception{
        turnLeft();
        while (frontIsClear()){
            putStone();
            move();
        }
        if (frontIsBlocked()){
            putStone();
        }
        originalColumn();
    }
    //go to next column
    private void nextColumn() throws Exception{
        for(int i = 0; i < 4; i ++){
            move();
        }
    }
    // go to original column
    private void originalColumn() throws Exception{
        turnAround();
        while (frontIsClear()){
            move();
        }
        turnLeft();
    }
    //put beeper if no beeper present
    private void putStone() throws Exception{
        if (noBeepersPresent()){
            putBeeper();
        }
    }
    //turn around
    private void  turnAround() throws Exception{
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }

}
