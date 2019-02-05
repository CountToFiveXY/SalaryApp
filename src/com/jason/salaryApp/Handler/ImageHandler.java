package com.jason.salaryApp.Handler;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {
    private static final int IMG_WIDTH = 280;
    private static final int IMG_HEIGHT = 220;
    private static final String IMAGE_FORMAT = "jpg";
    private static final String IMAGE_PATH = "resources/background.jpg";

    public ImageHandler(String imagePath) {
        printNewImage(imagePath);
    }

    public JLabel createBackGroundImageLabel() {
        ImageIcon background = new ImageIcon(IMAGE_PATH);
        JLabel label = new JLabel(background);
        label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        return label;
    }

    private void printNewImage (String imagePath) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, IMAGE_FORMAT, new File(IMAGE_PATH));
        } catch (IOException e) {
            System.out.println("Can't open image: " + imagePath);
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        graphics.dispose();
        return resizedImage;
    }
}
