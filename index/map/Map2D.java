package ru.map;

import ru.pixeldetector.PixelDetector;

import java.util.ArrayList;
import java.util.List;

public class Map2D {

    private List<Cell> cells = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Map2D(){
        for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
                cells.add(new Cell(x, y)); // заполняем игровое поле ячейками
            }
        }
        Cell cell = getCell(50, 28); // случайная игровая ячейка
        cell.setProperty("canThrow", "false"); // ставим клетке невозможность бросить из нее мяч
        System.out.println(cell.getProperty("canThrow")); // проверяем, можем ли бросать мяч из ячейки

        players.add(new Player("Vova Putin", null));
        Player player = getPlayer("Vova Putin"); // получили игрока
        player.setCell(cell); // задали ему положение
        player.setHasBall(true); //игрок владеет мячом
    }

    public Player getPlayer(String name) {
        return players.stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }


    public Player getPlayer(Cell cell) {
        return players.stream().filter(player -> player.getCell() == cell).findFirst().orElse(null);
    }

    public Cell getCell(int x, int y) {
        return cells.stream().filter(cell -> cell.getX() == x && cell.getY() == y).findFirst().orElse(null);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public static void main(String[] args) {
        new Map2D();
    }
}
