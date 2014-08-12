package de.eleon.report2image;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.util.Map;

@Mojo(name = "report2image", defaultPhase = LifecyclePhase.POST_SITE)
public class Report2Image extends AbstractMojo {

    public static final String HTML = "html";
    public static final String IMAGE = "image";
    public static final String WIDTH = "width";
    public static final String DEFAULT_WIDTH = "800";

    @Parameter(required = true)
    protected Map<String, String>[] reports;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        renderReports();
    }

    private void renderReports() {
        for (Map<String, String> report : reports) {
            String html = report.get(HTML);
            String image = report.get(IMAGE);
            int width = Integer.parseInt(report.getOrDefault(WIDTH, DEFAULT_WIDTH));
            renderReport(html, image, width);
        }
    }

    private void renderReport(String html, String image, int width) {
        getLog().info(String.format("render html: %s, image: %s, width%d", html, image, width));
        try {
            Renderer renderer = new Renderer(html);
            renderer.setWidth(width);
            renderer.render(image);
        } catch (IOException e) {
            getLog().error("can't create image", e);
        }
    }
}
