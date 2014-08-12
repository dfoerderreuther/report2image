package de.eleon.report2image;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class RendererTest {

    @Test
    public void shouldWriteCobertura() throws Exception {
        new File("cobertura.png").delete();
        Renderer renderer = new Renderer("src/test/resources/cobertura/frame-summary.html");
        renderer.setWidth(750);
        renderer.render("cobertura.png");

        assertTrue(new File("cobertura.png").exists());
    }

    @Test
    public void shouldWriteJacoco() throws Exception {
        new File("jacoco.png").delete();
        Renderer renderer = new Renderer("src/test/resources/jacoco/index.html");
        renderer.setWidth(750);
        renderer.setTag(false);
        renderer.render("jacoco.png");

        assertTrue(new File("jacoco.png").exists());
    }

}