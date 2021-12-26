package ru.map;

import java.util.Properties;

public class Cell {

    private int x;
    private int y;

    private Properties properties;

    public Cell(int xIn, int yIn){
        this.x = xIn;
        this.y = yIn;

        this.properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key, null);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
