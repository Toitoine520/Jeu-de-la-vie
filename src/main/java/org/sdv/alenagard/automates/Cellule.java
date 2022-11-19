package org.sdv.alenagard.automates;

public class Cellule {
    private int positionX;
    private int positionY;

    public Cellule() {
        this.positionX = 0;
        this.positionY = 0;
    }

    public Cellule(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "Cellule{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
