package com.shpp.p2p.cs.dpron.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

/* TODO: Game Breakout
 */
public class BreakoutExt extends WindowProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 5;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Animation delay or pause time between ball moves
     */
    private static final double DELAY = 4;

    /**
     * Ball velocity per y coordinate
     */
    private static final double START_VY = 0.5;

    /**
     * adding individual paddle object
     **/
    private GRect paddle;
    /**
     * adding individual ball object
     **/
    private Ball ball;
    /**
     * bricks counter
     **/
    private int bricksCounter = NBRICK_ROWS * NBRICKS_PER_ROW;

    public void run() {
        for (int i = 0; i < NTURNS; i++) {
            setUpGame();
            playGame();
            if (bricksCounter == 0) {
                ball.setVisible(false);
                printText("Winner!!");
                break;
            }
            if (bricksCounter > 0) {
                removeAll();
            }
        }
        if (bricksCounter > 0) {
            printText("Game Over");
        }
    }

    /**
     * Draw all the necessary elements.
     */
    private void setUpGame() {
        drawBricks(getWidth() / 2, BRICK_Y_OFFSET);
        drawPaddle();
        drawBall();
    }

    /**
     * Draw all the bricks necessary for the game.
     *
     * @param bx Brick x
     * @param by Brick y
     */
    private void drawBricks(double bx, double by) {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int column = 0; column < NBRICKS_PER_ROW; column++) {
                double x = bx - (NBRICKS_PER_ROW * BRICK_WIDTH) / 2 - ((NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2 + column * BRICK_WIDTH + column * BRICK_SEP;

                double y = by + row * BRICK_HEIGHT + row * BRICK_SEP;

                GRect brick = makeRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);

                //Setting colors depending on which row the bricks are in
                if (row < 2) {
                    brick.setColor(Color.RED);
                }
                if (row == 2 || row == 3) {
                    brick.setColor(Color.ORANGE);
                }
                if (row == 4 || row == 5) {
                    brick.setColor(Color.YELLOW);
                }
                if (row == 6 || row == 7) {
                    brick.setColor(Color.GREEN);
                }
                if (row == 8 || row == 9) {
                    brick.setColor(Color.CYAN);
                }
            }
        }
    }

    /**
     * Draw paddle.
     */
    private void drawPaddle() {
        //starting the paddle in the middle of the screen
        double x = getWidth() / 2 - PADDLE_WIDTH / 2;

        double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = makeRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        addMouseListeners();
    }

    /**
     * Making the paddle track the mouse.
     *
     * @param mouse events that occur due to the user interacting with a pointing device
     */
    public void mouseMoved(MouseEvent mouse) {
        /* If the middle point of the paddle is between half paddle width of the screen
         * and half a paddle width before the end of the screen,
         * the x location of the paddle is set at where the mouse is minus half a paddle's width,
         * and the height remains the same
         */
        if ((mouse.getX() < getWidth() - PADDLE_WIDTH / 2) && (mouse.getX() > PADDLE_WIDTH / 2)) {
            paddle.setLocation(mouse.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }

    /**
     * Draw ball.
     */
    private void drawBall() {
        double x = getWidth() / 2 - BALL_RADIUS;
        double y = getHeight() / 2 - BALL_RADIUS;
        ball = new Ball(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.vy = START_VY;
        add(ball);
    }

    /**
     * Start game.
     */
    private void playGame() {
        waitForClick();
        getBallVelocity();
        while (true) {
            moveBall();
            if (ball.getY() >= getHeight()) {
                break;
            }
            if (bricksCounter == 0) {
                break;
            }
        }
    }

    /**
     * Get random ball velocity.
     */
    private void getBallVelocity() {
        //Random number generator for vx
        RandomGenerator generator = RandomGenerator.getInstance();

        ball.vx = generator.nextDouble(1.0, 3.0);
        if (generator.nextBoolean(0.5)) {
            ball.vx = -ball.vx;
        }
    }

    /**
     * Move Ball.
     */
    private void moveBall() {
        ball.move(ball.vx, ball.vy);

        //check for walls
        if ((ball.getX() - ball.vx <= 0 && ball.vx < 0)
                || (ball.getX() + ball.vx >= (getWidth() - BALL_RADIUS * 2)
                && ball.vx > 0)) {
            ball.vx = -ball.vx;
        }
        if ((ball.getY() - ball.vy <= 0 && ball.vy < 0)) {
            ball.vy = -ball.vy;
        }

        //check for other objects
        GObject collider = getCollidingObject();
        if (collider == paddle) {
            if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 && ball.getY() <=
                    getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 + 4) {
                ball.vy = -ball.vy;
            }
        } else if (collider != null) {
            remove(collider);
            bricksCounter--;
            if (bricksCounter % (NBRICKS_PER_ROW / 2) == 0) {
                ball.vy += 0.5;
            }
            ball.vy = -ball.vy;
        }
        pause(DELAY);
    }

    /**
     * Get colliding object.
     *
     * @return colliding object.
     */
    private GObject getCollidingObject() {
        if ((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        } else if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
        }
        //need to return null if there are no objects present
        else {
            return null;
        }
    }

    /**
     * Print text.
     *
     * @param text text need to show.
     */
    private void printText(String text) {
        GLabel gameOver = new GLabel(text, getWidth() / 2, getHeight() / 2);
        gameOver.move(-gameOver.getWidth() / 2, -gameOver.getHeight());
        gameOver.setColor(Color.RED);
        add(gameOver);
    }

    /**
     * Make new rectangle with input params.
     */
    private GRect makeRect(double x, double y, int w, int h) {
        GRect rect = new GRect(x, y, w, h);
        add(rect);
        rect.setFilled(true);
        return rect;
    }
}