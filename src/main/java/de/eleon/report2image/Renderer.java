/*
* Copyright 2014 Dominik Foerderreuther <dominik@eleon.de>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package de.eleon.report2image;

import com.google.common.collect.ImmutableMap;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {

    private final static String TAG = "converted with report2image http://git.io/r2i";

    private String resource;
    private int width = 800;
    private boolean tag = true;

    public Renderer(String resource) {
        this.resource = resource;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public void render(String file) throws IOException {

        Java2DRenderer renderer = new Java2DRenderer(new File(resource), width);

        renderer.setRenderingHints(ImmutableMap.builder()
                .put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB)
                .build());

        BufferedImage bufferedImage = renderer.getImage();

        if (tag) {
            bufferedImage = applyTag(bufferedImage);
        }

        ImageIO.write(bufferedImage, "png", new File(file));
    }

    private BufferedImage applyTag(BufferedImage bufferedImage) {
        BufferedImage resizedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight() + 15, bufferedImage.getType());

        int height = resizedImage.getHeight();
        int width = resizedImage.getWidth();

        Graphics graphics = resizedImage.getGraphics();
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, width, height);

        graphics.drawImage(bufferedImage, 0, 0, null);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        graphics.drawString(TAG, 3, height - 3);

        return resizedImage;
    }


}
