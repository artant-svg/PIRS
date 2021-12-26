package ru.map;

public class Player {

    private String name;
    private Cell cell;
    private boolean hasBall;

    public Player(String name, Cell cell) {
        this.name = name;
        this.cell = cell;
        this.hasBall = false;
    }

    public String getName() {
        return name;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    public boolean isHasBall() {
        return hasBall;
    }
}
