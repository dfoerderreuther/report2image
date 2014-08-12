package de.eleon.report2image;

import com.google.common.collect.ImmutableMap;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {

    private String resource;
    private int width = 800;

    public Renderer(String resource) {
        this.resource = resource;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void render(String file) throws IOException {

        Java2DRenderer renderer = new Java2DRenderer(new File(resource), width);

        renderer.setRenderingHints(ImmutableMap.builder()
                .put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB)
                .build());

        BufferedImage buffImg = renderer.getImage();

        ImageIO.write(buffImg, "png", new File(file));
    }


}
