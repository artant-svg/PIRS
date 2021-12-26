package ru.pixeldetector;

import ru.map.Player;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class PixelDetector {

    public static BufferedImage makeScreenshot() throws Exception {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage bufferedImage = new Robot().createScreenCapture(new Rectangle(dimension));
        return bufferedImage;
    }

    public static BufferedImage loadImage(String path) throws Exception {
        return ImageIO.read(Paths.get(path).toFile());
    }

    public static int getPixelColor(BufferedImage bufferedImage, int x, int y) {
        return bufferedImage.getRGB(x, y);
    }

    public static boolean isColorOnImage(BufferedImage bufferedImage, Color color) {
        List<ColorData> colorDataList = new ArrayList<>();
        colorDataList.add(new ColorData(Color.RED));
        colorDataList.add(new ColorData(Color.GREEN));
        colorDataList.add(new ColorData(Color.BLUE));
        colorDataList.add(new ColorData(Color.WHITE));
        colorDataList.add(new ColorData(Color.BLACK));
        colorDataList.add(new ColorData(Color.YELLOW));
        colorDataList.add(new ColorData(Color.ORANGE));
        colorDataList.add(new ColorData(Color.CYAN));
        colorDataList.add(new ColorData(Color.GRAY));
        colorDataList.add(new ColorData(Color.MAGENTA));
        colorDataList.add(new ColorData(Color.PINK));
        for(int x = 0; x < bufferedImage.getWidth(); x++) {
            for(int y = 0; y < bufferedImage.getHeight(); y++) {
                Color pixelColor = new Color(getPixelColor(bufferedImage, x, y));
                ColorData result = colorDataList.stream().reduce((c1, c2) ->
                        distance(c1.getColor(), pixelColor).compareTo(distance(c2.getColor(), pixelColor)) < 0 ? c1 : c2
                ).get();
                result.incrementCount();
            }
        }
        colorDataList.sort(Comparator.comparingInt(ColorData::getCount));
        Collections.reverse(colorDataList);
        return colorDataList.get(0).getColor().getRGB() == color.getRGB();
    }

    public static Color getPopularColor(){
        Color res = Color.BLACK;
        List<ColorData> colorDataList = new ArrayList<>();
        colorDataList.add(new ColorData(Color.RED));
        colorDataList.add(new ColorData(Color.GREEN));
        colorDataList.add(new ColorData(Color.BLUE));
        colorDataList.add(new ColorData(Color.WHITE));
        colorDataList.add(new ColorData(Color.BLACK));
        colorDataList.add(new ColorData(Color.YELLOW));
        colorDataList.add(new ColorData(Color.ORANGE));
        colorDataList.add(new ColorData(Color.CYAN));
        colorDataList.add(new ColorData(Color.GRAY));
        colorDataList.add(new ColorData(Color.MAGENTA));
        colorDataList.add(new ColorData(Color.PINK));
        try {
            BufferedImage bufferedImage = makeScreenshot();
            for(int x = 0; x < bufferedImage.getWidth(); x++) {
                for(int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color pixelColor = new Color(getPixelColor(bufferedImage, x, y));
                    ColorData result = colorDataList.stream().reduce((c1, c2) ->
                            distance(c1.getColor(), pixelColor).compareTo(distance(c2.getColor(), pixelColor)) < 0 ? c1 : c2
                    ).get();
                    result.incrementCount();
                }
            }
            colorDataList.sort(Comparator.comparingInt(ColorData::getCount));
            Collections.reverse(colorDataList);
            return colorDataList.get(0).getColor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static BigDecimal distance(Color from, Color to) {
        return BigDecimal.valueOf(Math.sqrt(
                Math.pow(from.getRed() - to.getRed(), 2)
                        + Math.pow(from.getGreen() - to.getGreen(), 2)
                        + Math.pow(from.getBlue() - to.getBlue(), 2)
        ));
    }

    public static class ColorData {

        private Color color;
        private int count;

        public ColorData(Color color) {
            this.color = color;
        }

        public void incrementCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        public Color getColor() {
            return color;
        }
    }

}
