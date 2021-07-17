package com.shpp.p2p.cs.dpron.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {
    public void run() throws Exception {
        putBeeper();
        if (frontIsClear()){
            move();
            if (frontIsClear()){
                findMidPoint();
                removeAllBeepers();
                midPoint();
            }
            else {
                turnAround();
            }
        }
    }
    //find middle point
    private void  findMidPoint() throws Exception{
        placeBeeperWall();
        while (noBeepersPresent()){
            isItMidPoint();
            findBeeper();
        }
    }
    //remove all beepers
    private void removeAllBeepers() throws Exception{
       pickBeeperAlg();
       turnAround();
       midPoint();
       pickBeeperAlg();
       turnAround();
    }
    //pick beeper while front is clear
    private void  pickBeeperAlg() throws Exception{
        while (frontIsClear()){
            move();
            pickBeeper();
        }
    }
    //go to mid point
    private void  midPoint() throws Exception{
        while (noBeepersPresent()){
            move();
        }
    }
    //place beeper wall
    private void  placeBeeperWall() throws Exception{
        while (frontIsClear()){
            move();
        }
        putBeeper();
        turnAround();
        move();
    }
    //if it is middle point put final beeper
    private void  isItMidPoint() throws Exception{
        move();
        if (beepersPresent()){
            turnAround();
            move();
            putBeeper();
        }
    }
    //find beeper
    private void  findBeeper() throws Exception{
        if (noBeepersPresent()){
            while (noBeepersPresent()){
                move();
            }
            turnAround();
            move();
            putBeeper();
            move();
        }
    }
    //turn around
    private void  turnAround() throws Exception{
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }
}
