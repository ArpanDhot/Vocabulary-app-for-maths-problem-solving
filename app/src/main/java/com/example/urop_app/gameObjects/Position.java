package com.example.urop_app.gameObjects;

public abstract class Position {

    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;

    Position() {

    }

    /**
     * @return
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * @param xPos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * @return
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * @return
     */
    public int getxVel() {
        return xVel;
    }

    /**
     * @param xVel
     */
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    /**
     * @return
     */
    public int getyVel() {
        return yVel;
    }

    /**
     * @param yVel
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }



}
